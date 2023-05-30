package com.example.asso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    DB_Users DB;
    EditText name,email,pwd,dob,address,tel;
    Button register, cancel;
    TextView error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        pwd = findViewById(R.id.pwd);
        dob = findViewById(R.id.dob);
        address = findViewById(R.id.address);
        tel = findViewById(R.id.tel);
        error = findViewById(R.id.error);
        DB = new DB_Users(this);



    }

   // public void toLogin(View view){
     //   Intent intent = new Intent(Register.this, MainActivity.class);
      //  startActivity(intent);

    //}

    public void registerUser(View view){
        String nameStr = name.getText().toString().trim();
        String emailStr = email.getText().toString().trim();
        String pwdStr = pwd.getText().toString().trim();
        String dobStr = dob.getText().toString().trim();
        String addresstr = address.getText().toString().trim();
        String telStr = tel.getText().toString().trim();

        if(nameStr.isEmpty() || emailStr.isEmpty() || pwdStr.isEmpty() ||
                dobStr.isEmpty() || addresstr.isEmpty()|| telStr.isEmpty()){
            error.setText("Please insert values in all fields");
        }else{

            if(DB.getUser(emailStr).getCount() >= 1){


                error.setText("Email Address already Taken");
            }
            else{
                DB.insertuserdata(nameStr,emailStr,pwdStr,dobStr,addresstr,telStr);
                Toast.makeText(Register.this,"You've Successfully Registered",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this,WelcomeScreen.class);
                intent.putExtra("name",nameStr);
                intent.putExtra("email",emailStr);
                intent.putExtra("address",addresstr);
                intent.putExtra("tel",telStr);
                startActivity(intent);
            }
        }



    }
}