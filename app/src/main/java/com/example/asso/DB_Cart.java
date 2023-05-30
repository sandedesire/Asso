package com.example.asso;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Cart extends SQLiteOpenHelper {

    public DB_Cart( Context context) {
        super(context, "DB_Cart", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase cart) {
        cart.execSQL("CREATE TABLE IF NOT EXISTS cart (\n" +
                "\t id_line_item INTEGER  PRIMARY KEY ,\n" +
                "\t id_user INTEGER NOT NULL ,\n" +
                "\t total_price TEXT  NOT NULL\n" +
                ");");




    }

    @Override
    public void onUpgrade(SQLiteDatabase cart, int i, int i1) {
        cart.execSQL("DROP TABLE IF EXISTS cart");

    }

    public Boolean insertCart_data(int id_line_item, int id_user, String total_price) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_line_item", id_line_item);
        contentValues.put("id_user", id_user);
        contentValues.put("total_price", total_price);

        long result = DB.insert("cart", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getCart_data() {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("select * from cart ", null);
        return cursor;
    }

}
