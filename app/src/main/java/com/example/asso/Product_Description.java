package com.example.asso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Product_Description extends AppCompatActivity {
    int qtyadded = 0;
    int currentPrice = 0;
    ImageView productImage;
    TextView description,price,qtyInStock,totalItems,totalPrice,qtyAdded;
    Button addBtn,subtractBtn,AddtoCartBtn;
    ImageView cart;
    int productPrice;

    int totalPriceInt;
    String imageID;
    String qty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);

        Intent intent = getIntent();

        addBtn = findViewById(R.id.addQty);
        subtractBtn = findViewById(R.id.substractQty);
        AddtoCartBtn = findViewById(R.id.addToCart);


        qtyAdded = findViewById(R.id.qtyAdded);
        productImage = findViewById(R.id.productImage);
        description = findViewById(R.id.productDescription);
        price = findViewById(R.id.productPrice);
        qtyInStock = findViewById(R.id.qtyinstockValueProduct);
        totalItems = findViewById(R.id.totalCartItems);
        totalPrice = findViewById(R.id.totalCartPrice);

        cart = findViewById(R.id.cart);
        productImage.setImageResource(Integer.valueOf(intent.getStringExtra("imgScr")));
        imageID = intent.getStringExtra("imgScr");
        assert(imageID != null);
        description.setText(intent.getStringExtra("description").toString());
        price.setText(intent.getStringExtra("price") + " $".toString());
        productPrice = Integer.valueOf(intent.getStringExtra("price"));
        qtyInStock.setText(intent.getStringExtra("qtyInStock").toString());

        //totalItems.setText(intent.getStringExtra("totalItems").toString());
        //totalPrice.setText(intent.getStringExtra("totalPrice").toString());




    }

    public void add_subtract(View view){
        if(view.getId() == R.id.addQty){
            qtyadded+= 1;
            qtyAdded.setText(String.valueOf(qtyadded));
        }

        if(view.getId() == R.id.substractQty){
            qtyadded-= 1;
            qtyAdded.setText(String.valueOf(qtyadded));
        }
    }

    public void toCart(View view){


        if(qtyadded < 0){
            Toast.makeText(this,"Number of items cannot be negative",Toast.LENGTH_LONG).show();


        }else{

            if(totalItems.getText().toString().equals("")){
                totalItems.setText(String.valueOf(qtyadded));
                totalPrice.setText(String.valueOf(qtyadded * productPrice + " $"));
                currentPrice+=qtyadded * productPrice;
                totalPriceInt = currentPrice;

            }else{
                totalItems.setText(String.valueOf(qtyadded + Integer.valueOf(totalItems.getText().toString())));
                totalPrice.setText(String.valueOf(qtyadded * productPrice + currentPrice +" $"));

                currentPrice+=qtyadded * productPrice;
                totalPriceInt = currentPrice;
            }


        }


    }

    public void emptyCart(View view){
        totalItems.setText("");
        totalPrice.setText("" +" $");

    }

    public void toOrderSummary(View view){

            Intent intent = new Intent(this, Order_Summary.class);
            qty = totalItems.getText().toString().trim();
            assert(qty != null);
            intent.putExtra("imageScr", imageID);
            intent.putExtra("qty", qty);

            intent.putExtra("price", String.valueOf(totalPriceInt));
            intent.putExtra("itemPrice", price.getText().toString());

            startActivity(intent);


    }
}