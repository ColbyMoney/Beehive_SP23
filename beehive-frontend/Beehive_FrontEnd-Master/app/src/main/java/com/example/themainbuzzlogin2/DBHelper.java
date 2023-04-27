package com.example.themainbuzzlogin2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DMNAME = "Login.db";
    public DBHelper(Context context) {
        super(context, "Login.db", null, 2); // Update the version number to 2
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(username TEXT primary key, password TEXT, name TEXT, lastName TEXT, email TEXT, age TEXT, token TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users"); // Change "Login.db" to "users"
        onCreate(db);
    }

    public boolean insertData(String username, String name, String lastName, String email, String password,  String age){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password); // Fix the mistake by setting the password value
        values.put("name", name);
        values.put("age", age);
        values.put("lastName", lastName); // Add the lastName value
        values.put("email", email); // Add the email value

        long result = db.insert("users", null, values);
        if (result ==-1) return false;
        else
            return true;

    }

    public boolean updateToken(String username, String token) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("token", token);
        int updatedRowCount = db.update("users", contentValues, "username = ?", new String[]{username});
        return updatedRowCount > 0;
    }

    public Boolean checkUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=?", new String[] {username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?", new String[] {username, password});
        if (cursor.getCount()> 0)
            return true;
        else
            return false;
    }
}