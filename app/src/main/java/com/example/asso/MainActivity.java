package com.example.asso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    DB_Users DB_User;
    DB_Line_item DB_line_item;
    DB_Cart DB_cart;
    DB_Products DB_Product;
    EditText name, email, pwd, dob, address, tel;
    Button register, cancel;
    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);

        pwd = findViewById(R.id.pwd);
        error = findViewById(R.id.error);
        DB_User = new DB_Users(this);
        DB_Product = new DB_Products(this);
        DB_Product.insertproductdata();
        DB_line_item = new DB_Line_item(this);
        DB_cart = new DB_Cart(this);



    }

    public void toRegister(View view) {
        Intent intent = new Intent(MainActivity.this, Register.class);
        startActivity(intent);
    }

    public void toWelcomeScreen(View view) {
        String emailStr = email.getText().toString().trim();
        String pwdStr = pwd.getText().toString().trim();
        if (emailStr.isEmpty() || pwdStr.isEmpty()) {
            error.setText("Please insert values in all fields");
        } else {

            if (DB_User.getUser(emailStr).getCount() >= 1) {
                String nameStr,addressStr,telStr;
                Cursor cursor = DB_User.getUser(emailStr);
                cursor.moveToFirst();
                nameStr = cursor.getString(1);
                addressStr = cursor.getString(5);
                telStr = cursor.getString(6);
                Intent intent = new Intent(MainActivity.this, WelcomeScreen.class);
                intent.putExtra("name",nameStr);
                intent.putExtra("email",emailStr);
                intent.putExtra("address",addressStr);
                intent.putExtra("tel",telStr);
                startActivity(intent);



            }else{
                error.setText("No user found with Email /or Password");
            }
        }
    }

}

