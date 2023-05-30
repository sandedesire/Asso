package com.example.asso;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Random;

public class DB_Products extends SQLiteOpenHelper {

    public DB_Products( Context context) {
        super(context, "DB_Products", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase products) {
        products.execSQL("CREATE TABLE IF NOT EXISTS products (\n" +
                "\t id INTEGER  PRIMARY KEY AUTOINCREMENT,\n" +
                "\t name TEXT  NOT NULL,\n" +
                "\t image INTEGER NOT NULL,\n" +
                "\t description TEXT  NOT NULL,\n" +
                "\t qty_in_stock INT  NOT NULL,\n" +
                "\t price TEXT  NOT NULL\n" +
                ");");




    }

    @Override
    public void onUpgrade(SQLiteDatabase products, int i, int i1) {
        products.execSQL("DROP TABLE IF EXISTS products");

    }

    public Boolean insertproductdata() {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String []productNames = {"Ball","Bed","Brush","Car","Cooker","Jersay","Microwave",
                                "Mug","Phones","Piano","Plates","Refrigirator","Shoes",
                                "Sofa","Suit","Table","Television","Vacuum Cleaner",
                                "Washing Machine","Watch"};

        int []productImages = {R.drawable.ball,R.drawable.bed,R.drawable.brush,
                R.drawable.car,R.drawable.cooker,R.drawable.jersay,R.drawable.microwave,
                R.drawable.mug,R.drawable.phones,R.drawable.piano,R.drawable.plates,
                R.drawable.refrigirator,R.drawable.shoes, R.drawable.sofa,R.drawable.suit,
                R.drawable.table,R.drawable.tv,R.drawable.vacuumcleaner,
                R.drawable.washingmachine,R.drawable.watch};

        for (int i = 0; i<20; i++){
            contentValues.put("name", productNames[i]);
            contentValues.put("image", productImages[i]);
            contentValues.put("description", "This is a  "+ productNames[i]);
            contentValues.put("qty_in_stock", (new Random()).nextInt(1000));
            contentValues.put("price", (new Random()).nextInt(1000));
            long result = DB.insert("products", null, contentValues);

        }


       return true;

    }

    public Cursor getProductsdata() {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("select * from products ", null);
        return cursor;
    }





}
