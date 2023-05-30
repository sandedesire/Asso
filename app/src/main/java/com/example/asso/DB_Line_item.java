package com.example.asso;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Line_item extends SQLiteOpenHelper {

    public DB_Line_item( Context context) {
        super(context, "DB_Line_item", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase line_item) {
        line_item.execSQL("CREATE TABLE IF NOT EXISTS line_item (\n" +
                "\t id INTEGER  PRIMARY KEY ,\n" +
                "\t qty INTEGER  NOT NULL\n" +
                ");");




    }

    @Override
    public void onUpgrade(SQLiteDatabase users, int i, int i1) {
        users.execSQL("DROP TABLE IF EXISTS line_item");

    }

    public Boolean insertLine_item_data(int id, int qty) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("qty", qty);

        long result = DB.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor get_Line_item_data() {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("select * from line_item ", null);
        return cursor;
    }


}
