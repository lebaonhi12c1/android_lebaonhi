package com.example.lebaonhi_dh51904155.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.lebaonhi_dh51904155.datahelper.datahelper;
import com.example.lebaonhi_dh51904155.model.classes;

import java.util.ArrayList;
import java.util.List;

public class khoadao {
    private SQLiteDatabase db;

    public khoadao(Context context) {
        datahelper helper = new datahelper(context);
        this.db = helper.getWritableDatabase();
    }
    public long insert(classes khoa){
        ContentValues values = new ContentValues();
        values.put("name",khoa.getName());
        return  db.insert("khoa",null,values);
    }
    @SuppressLint("Range")
    public List<classes> get(String sql, String ... selectArgs){
        List<classes> list = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, selectArgs);

        while (cursor.moveToNext()){
            classes khoa= new classes();
            khoa.setId(cursor.getInt(cursor.getColumnIndex("id")));
            khoa.setName(cursor.getString(cursor.getColumnIndex("name")));
            list.add(khoa);
        }
        return list;
    }
    public  List<classes> getAll(){
        String sql = "SELECT * FROM khoa";
        return get(sql);
    }
    public  classes getHhoa(int idkhoa){
        Cursor cursor = db.rawQuery("select * from khoa where id = ?", new String[]{idkhoa +""});
        cursor.moveToFirst();
        classes khoa = new classes(cursor.getInt(0),cursor.getString(1));
        return khoa;
    }
    public void getupdate(classes cls){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",cls.getId());
        contentValues.put("name",cls.getName());
        db.update("khoa",contentValues,"id=?",new String[]{cls.getId()+ ""});
    }
    public  int delete(int id){
        return db.delete("khoa","id=?", new String[]{id +""});
    }

}