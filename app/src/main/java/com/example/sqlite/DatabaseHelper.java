package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context){
        //Наименование БД Version >= 1
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Запрос на создание таблицы БД
        sqLiteDatabase.execSQL("create Table UserInfo(name Text primary key, " +
                "phone Text, date_of_birth Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Условие на удаление таблицы UserInfo
        sqLiteDatabase.execSQL("drop Table if exists UserInfo");
    }

    public Boolean insert(String name, String phone, String date_of_birth){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("date_of_birth", date_of_birth);
        long result = DB.insert("UserInfo", null, contentValues);
        return result != 1;
    }

    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
        return  DB.rawQuery("Select * from UserInfo", null);
    }


    public Boolean Update(String name, String phone, String date_of_birth)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("date_of_birth", date_of_birth);
        int result = db.update("UserInfo", contentValues, "name = " + "'" + name + "'", null);
        db.close();
        return result > 0;
    }

    public Boolean Delete(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("UserInfo", "name = " + "'" + name + "'", null);
        db.close();
        return result > 0;
    }
}
