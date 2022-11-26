package com.example.lebaonhi_dh51904155.datahelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class datahelper extends SQLiteOpenHelper {
    private static  final String DB_NAME = "quanlyhocsinh";
    private static  final int DB_VERSION =1;
    public datahelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String khoa = "CREATE TABLE khoa(id INTEGER primary key autoincrement, name text not null)";
        String sinhvien = "CREATE TABLE sinhvien(id int primary key, name text not null, email text, khoa INTEGER, sodienthoai text, image blob, FOREIGN KEY (khoa) REFERENCES khoa(id))";
        sqLiteDatabase.execSQL(khoa);
        sqLiteDatabase.execSQL(sinhvien);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String khoa ="DROP TABLE IF EXISTS khoa";
        String sinhvien ="DROP TABLE IF EXISTS sinhvien";
        sqLiteDatabase.execSQL(khoa);
        sqLiteDatabase.execSQL(sinhvien);
        onCreate(sqLiteDatabase);
    }
}