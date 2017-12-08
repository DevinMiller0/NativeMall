package com.awake.dreaming.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wangdesheng on 2017/11/28 0028.
 */

public class DBHelper extends SQLiteOpenHelper{

    private final String RECORD_NAME = "voice.db";
    private final int VERSION = 2;
    private final String CREATE_TABLE = "create table "
            + "(id string, name string, gender int, age int)";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
