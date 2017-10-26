package com.pamo.nativemall.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.pamo.nativemall.datas.PersonalInfo;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by wangdesheng on 2017/10/26 0026.
 */

public class SharedPreferenceUtils {

    private Context context;
    public SharedPreferenceUtils(Context context){
        this.context = context;
    }

    /**
     * 保存名片信息
     */
    public void save(PersonalInfo info){
        SharedPreferences preferences = context.getSharedPreferences("BUSINESS_CARD_INFO",
                Context.MODE_PRIVATE);

        //create byte output stream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            //创建对象输出流，并封装字节流
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            //将对象写入字节流
            oos.writeObject(info);

            //将字节流编码成base64的字符串
            String infoString = new String(Base64.encodeBase64(outputStream.toByteArray()));

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("BUSINESS_CARD_INFO", infoString);
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取本地的信息
     */
    public PersonalInfo read(){
        SharedPreferences preferences = context.getSharedPreferences
                ("BUSINESS_CARD_INFO", context.MODE_PRIVATE);
        String infoString = preferences.getString("BUSINESS_CARD_INFO_key", null);
        if (TextUtils.isEmpty(infoString)){
            return null;
        }

        PersonalInfo info = null;
        //read byte.
        byte[] base64 = Base64.decodeBase64(infoString.getBytes());
        //package to byte stream.
        ByteArrayInputStream inputStream = new ByteArrayInputStream(base64);
        try {
            //package again.
            ObjectInputStream bis = new ObjectInputStream(inputStream);
            try {
                //read object.
                info = (PersonalInfo) bis.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return info;
    }

    /**
     * 清空本地存储的信息
     */
    public void clear(){
        SharedPreferences preferences = context.getSharedPreferences
                ("BUSINESS_CARD_INFO", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("BUSINESS_CARD_INFO_key");
        editor.commit();
    }
}
