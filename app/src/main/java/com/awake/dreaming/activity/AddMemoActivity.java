package com.awake.dreaming.activity;

import android.Manifest;
import android.animation.Animator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Toast;

import com.awake.dreaming.R;
import com.awake.dreaming.widget.MemoEditText;
import com.awake.dreaming.widget.PicDialog;
import com.awake.dreaming.widget.RecordDialog;
import com.awake.dreaming.widget.TopBar;

import java.io.File;
import java.io.IOException;

import static android.os.Build.VERSION_CODES.M;

/**
 *
 * Created by wangdesheng on 2017/11/2.
 */

public class AddMemoActivity extends BaseActivity {

    private ScrollView scrollContainer;
    private MemoEditText content;
    private EditText etTitle;
    private final int REQUEST_CAMERA_CODE = 0;
    private final int TAKE_PHOTO_CODE = 1;
    private final int CHOOSE_PHOTO_CODE = 2;
    private Uri uri;
    private File file;
    private final String TAG = "AddMemoActivity";

    private String flag = "start";
    private String START = "start";
    private String RECORDING = "recording";
    private String RECORD_COMPLETE = "complete";
    private MediaRecorder mediaRecorder;

    @Override
    protected int getLayout() {
        return R.layout.activity_add_memo;
    }

    @Override
    protected void setLayout() {
        initView();
    }

    private void initView() {
        TopBar topBar = (TopBar) findViewById(R.id.top_bar);
        topBar.setImgMore(R.mipmap.memo_keep);
        topBar.setTitle(getString(R.string.memo_top_bar_title));
        topBar.setOnBackClickListener(new TopBar.OnBackClickListener() {
            @Override
            public void backClick(View view) {
                finish();
            }
        });
        topBar.setOnMoreClickListener(new TopBar.OnMoreClickListener() {
            @Override
            public void moreClick(View view) {
                Toast.makeText(AddMemoActivity.this, "keep complete", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.img_memo_camera).setOnClickListener(this);
        findViewById(R.id.img_memo_voice).setOnClickListener(this);
        etTitle = (EditText) findViewById(R.id.et_memo_title);
        etTitle.clearFocus();
        scrollContainer = (ScrollView) findViewById(R.id.scroll_container);
        content = (MemoEditText) findViewById(R.id.et_memo_content);
        content.setSingleLine(false);

        scrollContainer.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                Log.e(TAG, "onScrollChange i: " + i );
                Log.e(TAG, "onScrollChange i1: " + i1 );
                Log.e(TAG, "onScrollChange i2: " + i2 );
                Log.e(TAG, "onScrollChange i3: " + i3 );
            }
        });

//        content.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
//                search.setVisibility(View.GONE);
//            }
//        });

    }

    @Override
    protected void onViewClick(View view) {
        switch (view.getId()){
            case R.id.img_memo_camera:{
                dialog();
                break;
            }
            case R.id.img_memo_voice:{
                recordDialog();
                break;
            }
        }
    }

    private void dialog() {
        final PicDialog picDialog = new PicDialog(this);
        picDialog.setOnDefineClickListener(new PicDialog.OnDefineClickListener() {
            @Override
            public void onDefineClick(View view) {
                openCamera();
                picDialog.dismiss();
            }
        });

        picDialog.setOnCancelClickListener(new PicDialog.OnCancelClickListener() {
            @Override
            public void onCancelListener(View view) {
//                startActivity(new Intent(AddMemoActivity.this, ImageSelectorActivity.class));
                openAlbum();
                picDialog.dismiss();
            }
        });
        picDialog.show();
    }

    private void recordDialog() {
        final RecordDialog dialog = new RecordDialog(this);
        dialog.setOnDialogEvent(new RecordDialog.DialogClickListener() {
            @Override
            public void reRecordClick(View view) {

            }

            @Override
            public void startClick(View view, ImageButton imgBtn) {
                int centerX = (view.getLeft() + view.getRight()) / 2;
                int centerY = (view.getTop() + view.getBottom()) / 2;

                //float finalRadius = (float) Math.hypot((double) centerX, (double) centerY);
                int w = imgBtn.getWidth();
                int h = imgBtn.getHeight();
                Animator mCircularReveal = ViewAnimationUtils.createCircularReveal(
                        imgBtn, w / 2, h / 2 - 20, 0, 100);
                mCircularReveal.setDuration(300).start();
                switch (flag) {
                    case "start": {
                        imgBtn.setImageDrawable(getDrawable(R.mipmap.btn_record_pressed));
                        if (Build.VERSION.SDK_INT >= M) {
                            int micPermission = ContextCompat.checkSelfPermission
                                    (getApplication(), Manifest.permission.RECORD_AUDIO);
                            if (micPermission == PackageManager.PERMISSION_GRANTED) {
                                recordVoice();
                            }else {
                                ActivityCompat.requestPermissions(AddMemoActivity.this,
                                        new String[]{Manifest.permission.RECORD_AUDIO},
                                        REQUEST_CAMERA_CODE);
                            }
                        }
                        flag = "recording";
                        break;
                    }
                    case "recording": {
                        imgBtn.setImageDrawable(getDrawable(R.mipmap.btn_record_finish));


                        mediaRecorder.stop();
                        flag = "complete";
                        break;
                    }
                    case "complete": {
                        imgBtn.setImageDrawable(getDrawable(R.mipmap.btn_record_normal));
                        flag = "start";
                        dialog.dismiss();
                        break;
                    }
                }
            }

            @Override
            public void closeClick(View view) {

            }
        });
        dialog.show();
    }

    private void openCamera() {
        if (Build.VERSION.SDK_INT >= M) {
            int cameraPermission = ContextCompat.checkSelfPermission
                    (getApplication(), Manifest.permission.CAMERA);
            if (cameraPermission == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+
                        "/temp.jpg");
                file.getParentFile().mkdirs();
                Uri uri = FileProvider.getUriForFile(AddMemoActivity.this,
                        "com.awake.dreaming.fileProvider", file);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, TAKE_PHOTO_CODE);
            }else {
                ActivityCompat.requestPermissions(AddMemoActivity.this,
                        new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_CODE);
            }
        }
    }

    private void openAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO_CODE);
    }

    private void recordVoice() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFile(String.valueOf(MediaRecorder.OutputFormat.DEFAULT));
        //mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);
        try {
            File file = File.createTempFile("record_", ".amr");
            mediaRecorder.setOutputFile(file.getAbsolutePath());
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CAMERA_CODE: {
                if (grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File temp = new File(String.valueOf(Environment.getExternalStorageDirectory()));
                    uri = FileProvider.getUriForFile
                            (AddMemoActivity.this, "com.awake.dreaming.fileProvider", temp);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivityForResult(intent, TAKE_PHOTO_CODE);
                }
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e(TAG, "requestCode: " + requestCode );
        Log.e(TAG, "resultCode: " + resultCode );

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TAKE_PHOTO_CODE: {
                    //content.setText("\n");
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    content.insert(modifyBitmap(bitmap));
                    //content.setSelection(content.getText().length());
                    break;
                }
                case CHOOSE_PHOTO_CODE: {
                    Uri imgUri = data.getData();
                    String[] filePathColumns = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(imgUri, filePathColumns, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumns[0]);
                    String path = cursor.getString(columnIndex);
                    cursor.close();

                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    content.insert(bitmap);
                    break;
                }
            }
        }
    }

    private Bitmap modifyBitmap(Bitmap bitmap) {
        WindowManager manager = (WindowManager) this.getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        int displayW = display.getWidth();
        //int displayH = display.getHeight();

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

//        float scaleWidth;
//        float scaleHeight;
//        scaleWidth = ((float) displayW / width);
//        scaleHeight = ((float) displayH / height);

        Matrix matrix = new Matrix();
        switch (displayW) {
            case 1080: {
                if (width > height) {
                    matrix.postScale(0.73f, 0.61f);
                }else {
                    matrix.postScale(0.9f, 0.9f);
                }
                break;
            }
            case 720: {
                if (width > height) {
                    matrix.postScale(0.73f, 0.61f);
                }else {
                    matrix.postScale(0.55f, 0.56f);
                }
                break;
            }
        }

        Bitmap newBitmap;

        newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return newBitmap;
    }
}