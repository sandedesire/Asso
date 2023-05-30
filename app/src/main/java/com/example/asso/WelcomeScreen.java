package com.example.asso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class WelcomeScreen extends AppCompatActivity {

    DB_Products products;
    TextView priceBed,nameBed,qtyinstockValueBed;
    TextView priceBall,nameBall,qtyinstockValueBall;
    TextView priceCar,nameCar,qtyinstockValueCar;
    TextView priceJersay,nameJersay,qtyinstockValueJersay;
    TextView pricePhone,namePhone,qtyinstockValuePhone;
    TextView pricePiano,namePiano,qtyinstockValuePiano;
    TextView priceShoe,nameShoe,qtyinstockValueShoe;
    TextView priceTV,nameTV,qtyinstockValueTV;
    TextView username,totalPrice,totalItems;
    int totalPriceInt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        Intent intent = getIntent();

        username = findViewById(R.id.username);
        username.setText( intent.getStringExtra("name").toString());
        products = new DB_Products(this);

        priceBed = findViewById(R.id.priceBed);
        nameBed = findViewById(R.id.nameBed);
        qtyinstockValueBed = findViewById(R.id.qtyinstockValueBed);

        priceBall = findViewById(R.id.priceBall);
        nameBall = findViewById(R.id.nameBall);
        qtyinstockValueBall = findViewById(R.id.qtyinstockValueBall);

        priceCar= findViewById(R.id.priceCar);
        nameCar = findViewById(R.id.nameCar);
        qtyinstockValueCar = findViewById(R.id.qtyinstockValueCar);

        priceJersay = findViewById(R.id.priceJersay);
        nameJersay= findViewById(R.id.nameJersay);
        qtyinstockValueJersay = findViewById(R.id.qtyinstockValueJersay);

        pricePhone = findViewById(R.id.pricePhones);
        namePhone= findViewById(R.id.namePhones);
        qtyinstockValuePhone = findViewById(R.id.qtyinstockValuePhones);

        pricePiano = findViewById(R.id.pricePiano);
        namePiano= findViewById(R.id.namePiano);
        qtyinstockValuePiano = findViewById(R.id.qtyinstockValuePiano);

        priceShoe = findViewById(R.id.priceShoes);
        nameShoe= findViewById(R.id.nameShoes);
        qtyinstockValueShoe = findViewById(R.id.qtyinstockValueShoes);

        priceTV = findViewById(R.id.priceTv);
        nameTV= findViewById(R.id.nameTv);
        qtyinstockValueTV = findViewById(R.id.qtyinstockValueTv);

        totalItems = findViewById(R.id.totalCartItems);
        totalPrice = findViewById(R.id.totalCartPrice);





        Cursor cursor = products.getProductsdata();


        while (cursor.moveToNext()){
            if(cursor.getString(1).toString().trim().toLowerCase().equals("bed")){
                priceBed.setText(cursor.getString(5) + " $");
                nameBed.setText(cursor.getString(1).toString());
                qtyinstockValueBed.setText(cursor.getString(4).toString());
            }
            if(cursor.getString(1).toString().trim().toLowerCase().equals("ball")){
                priceBall.setText(cursor.getString(5) + " $");
                nameBall.setText(cursor.getString(1).toString());
                qtyinstockValueBall.setText(cursor.getString(4).toString());
            }
            if(cursor.getString(1).toString().trim().toLowerCase().equals("car")){
                priceCar.setText(cursor.getString(5) + " $");
                nameCar.setText(cursor.getString(1).toString());
                qtyinstockValueCar.setText(cursor.getString(4).toString());
            }
            if(cursor.getString(1).toString().trim().toLowerCase().equals("jersay")){
                priceJersay.setText(cursor.getString(5) + " $");
                nameJersay.setText(cursor.getString(1).toString());
                qtyinstockValueJersay.setText(cursor.getString(4).toString());
            }
            if(cursor.getString(1).toString().trim().toLowerCase().equals("phones")){
                pricePhone.setText(cursor.getString(5) + " $");
                namePhone.setText(cursor.getString(1).toString());
                qtyinstockValuePhone.setText(cursor.getString(4).toString());
            }
            if(cursor.getString(1).toString().trim().toLowerCase().equals("piano")){
                pricePiano.setText(cursor.getString(5) + " $");
                namePiano.setText(cursor.getString(1).toString());
                qtyinstockValuePiano.setText(cursor.getString(4).toString());
            }
            if(cursor.getString(1).toString().trim().toLowerCase().equals("shoes")){
                priceShoe.setText(cursor.getString(5) + " $");
                nameShoe.setText(cursor.getString(1).toString());
                qtyinstockValueShoe.setText(cursor.getString(4).toString());
            }
            if(cursor.getString(1).toString().trim().toLowerCase().equals("television")){
                priceTV.setText(cursor.getString(5) + " $");
                nameTV.setText(cursor.getString(1).toString());
                qtyinstockValueTV.setText(cursor.getString(4).toString());
            }

        }



    }

    public void toProductDescription(View view){
        Intent intent = new Intent(this, Product_Description.class);
        ImageView iv = (ImageView)view;
        Cursor cursor = products.getProductsdata();
        while (cursor.moveToNext()) {
            if (cursor.getString(1).toString().trim().toLowerCase()
                    .equals(iv.getContentDescription().toString().trim().toLowerCase())) {

                intent.putExtra("imgScr",cursor.getString(2).toString());
                intent.putExtra("description",cursor.getString(3).toString());
                intent.putExtra("price",cursor.getString(5).toString());
                intent.putExtra("qtyInStock",cursor.getString(4).toString());
                if(totalItems.getText().toString().equals("")){

                }else{
                    intent.putExtra("totalPrice",totalPriceInt);
                    intent.putExtra("toatalItems",totalItems.getText().toString());
                }

            }
        }

        startActivity(intent);


    }
}