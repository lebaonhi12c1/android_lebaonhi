package com.example.lebaonhi_dh51904155.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.lebaonhi_dh51904155.datahelper.datahelper;
import com.example.lebaonhi_dh51904155.model.classes;
import com.example.lebaonhi_dh51904155.model.student;

import java.util.ArrayList;
import java.util.List;

public class sinhviendao {
    private SQLiteDatabase db;

    public sinhviendao(Context context) {
        datahelper helper = new datahelper(context);
        this.db = helper.getWritableDatabase();
    }
    public long insert(student sinhvien){
        ContentValues values = new ContentValues();
        values.put("name",sinhvien.getName());
        values.put("id",sinhvien.getId());
        values.put("email",sinhvien.getEmail());
        values.put("sodienthoai",sinhvien.getPhone());
        values.put("image",sinhvien.getImage());
        values.put("khoa",sinhvien.getClasses());
        return  db.insert("sinhvien",null,values);
    }
    @SuppressLint("Range")
    public List<student> get(String sql, String ... selectArgs){
        List<student> list = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, selectArgs);

        while (cursor.moveToNext()){
            student sinhvien= new student();
            sinhvien.setId(cursor.getInt(cursor.getColumnIndex("id")));
            sinhvien.setName(cursor.getString(cursor.getColumnIndex("name")));
            sinhvien.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            sinhvien.setPhone(cursor.getString(cursor.getColumnIndex("sodienthoai")));
            sinhvien.setClasses(cursor.getInt(cursor.getColumnIndex("khoa")));
            sinhvien.setImage(cursor.getBlob(cursor.getColumnIndex("image")));
            list.add(sinhvien);
        }
        return list;
    }
    public  List<student> getAll(){
        String sql = "SELECT * FROM sinhvien";
        return get(sql);
    }
    public  student getSinhvien(int idSinhvien){
        Cursor cursor = db.rawQuery("select * from sinhvien where id = ?", new String[]{idSinhvien +""});
        cursor.moveToFirst();
        student sinhvien = new student(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(4),cursor.getBlob(5),cursor.getInt(3));
        return sinhvien;
    }
    public student getClassfromSinhvien(int idSinhvien){
        Cursor cursor = db.rawQuery("select * from sinhvien where khoa = ?", new String[]{idSinhvien +""});
        cursor.moveToFirst();
        student sinhvien = new student(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(4),cursor.getBlob(5),cursor.getInt(3));
        if(sinhvien != null){
            return sinhvien;
        }else{
            return sinhvien;
        }
    }
    public void getupdate(student cls){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",cls.getName());
        contentValues.put("email",cls.getEmail());
        contentValues.put("sodienthoai",cls.getPhone());
        contentValues.put("khoa",cls.getClasses());
        contentValues.put("image",cls.getImage());
        db.update("sinhvien",contentValues,"id=?",new String[]{cls.getId()+ ""});
    }
    public  int delete(int id){
        return db.delete("sinhvien","id=?", new String[]{id +""});
    }
}