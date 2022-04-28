package com.example.myloginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME="login.db";
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "login.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users (id NUMBER primary key, username TEXT, opassword TEXT, npassword TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists users ");
    }

    public Boolean insertData(String username, String opassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", username);
        values.put("opassword", opassword);

        long result = db.insert("users", null, values);
        if (result == 1)
            return false;
        else
            return true;

    }
    public Boolean checkusername(String username){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from users where username=?", new String[] {username});

        if(cursor.getCount()>0)
            return false;
        else
            return true;
    }

    public Boolean checkpassword(String username,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor =db.rawQuery("select * from users where username=? and password=?",new String[] {username,password});

        if ((cursor.getCount() > 0))
            return true;
        else
            return false;
    }
}
