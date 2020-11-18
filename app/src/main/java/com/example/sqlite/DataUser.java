package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataUser extends SQLiteOpenHelper {
    public DataUser(@Nullable Context context,
                    @Nullable String name,
                    @Nullable SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = " Create table user ("  + "name TEXT NOT NULL ," + "id INTEGER PRIMARY KEY AUTOINCREMENT )";
        db.execSQL(sql);
    }

    public void addUser (User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name",user.getName());
        // neu dung Autoincrement thi ko ghi dong duoi
//        values.put("id",user.getId());

        db.insert("user",null,values);

    }

    public List<User> getAll(){
        List<User> userList = new ArrayList<>();
        String sql =" select * from user";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                User user= new User();
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));

                userList.add(user);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return  userList;
    }

    public int RemoveUser(int id){
        SQLiteDatabase db= this.getReadableDatabase();
        return  db.delete("user","Id=?", new String[] {String.valueOf(id)});
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
