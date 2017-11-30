package com.awake.dreaming.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.awake.dreaming.R;
import com.awake.dreaming.widget.MemoEditText;
import com.awake.dreaming.widget.PicDialog;
import com.awake.dreaming.widget.RecordDialog;
import com.awake.dreaming.widget.TopBar;

import java.io.File;

import static android.os.Build.VERSION_CODES.M;

/**
 *
 * Created by wangdesheng on 2017/11/2.
 */

public class AddMemoActivity extends BaseActivity {

    private MemoEditText content;
    private final int REQUEST_CAMERA_CODE = 0;
    private final int TAKE_PHOTO_CODE = 1;
    private final int CHOOSE_PHOTO_CODE = 2;
    private Uri uri;
    private File file;
    private final String TAG = "AddMemoActivity";

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

        final LinearLayout search = (LinearLayout) findViewById(R.id.ll_association);
        findViewById(R.id.img_memo_camera).setOnClickListener(this);
        findViewById(R.id.img_memo_voice).setOnClickListener(this);
        content = (MemoEditText) findViewById(R.id.et_memo_content);
        content.setSingleLine(false);
//        content.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
//                search.setVisibility(View.GONE);
//            }
//        });
        //content.insertPic(R.mipmap.timg);

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
            public void startClick(View view) {
                dialog.dismiss();
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

        if (requestCode != RESULT_CANCELED) {
            switch (requestCode) {
                case TAKE_PHOTO_CODE: {
                    content.setText("\n");
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    content.insert(modifyBitmap(bitmap));
                    break;
                }
                case CHOOSE_PHOTO_CODE: {
//                    Bitmap bitmap = data.getExtras().getParcelable("data");
//                    content.setText("\n");
//                    content.insert(modifyBitmap(bitmap));
                    break;
                }

            }
        }

//        if (requestCode == TAKE_PHOTO_CODE && resultCode == RESULT_OK) {
//
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    content.setText("\n");
//                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
//                    content.insert(modifyBitmap(bitmap));
//                    //content.setSelection(content.getText().length());
//                }
//            }, 500);
//        }
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