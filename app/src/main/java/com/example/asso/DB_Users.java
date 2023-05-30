package com.example.asso;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB_Users extends SQLiteOpenHelper {
    public DB_Users( Context context) {
        super(context, "DB_Users", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase users) {
        users.execSQL("CREATE TABLE IF NOT EXISTS users (\n" +
                "\t id INTEGER  PRIMARY KEY AUTOINCREMENT,\n" +
                "\t name TEXT  NOT NULL,\n" +
                "\t dob TEXT NOT NULL,\n" +
                "\t email TEXT UNIQUE NOT NULL,\n" +
                "\t pwd TEXT  NOT NULL,\n" +
                "\t address TEXT  NOT NULL,\n" +
                "\t tel TEXT  NOT NULL\n" +
                ");");




    }

    @Override
    public void onUpgrade(SQLiteDatabase users, int i, int i1) {
        users.execSQL("DROP TABLE IF EXISTS users");

    }

    public Boolean insertuserdata(String name, String email, String pwd,
                                  String dob, String address, String tel) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("pwd", pwd);
        contentValues.put("dob", dob);
        contentValues.put("address", address);
        contentValues.put("tel", tel);
        long result = DB.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getUser(String email) {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("select * from users where email = ?", new String[]{email});
        return cursor;
    }


}
