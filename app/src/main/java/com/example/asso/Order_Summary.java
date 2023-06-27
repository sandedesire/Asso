package com.example.asso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Order_Summary extends AppCompatActivity {
    TextView itemPrice, totalPrice, qty;
    ImageView imageID;
    int imageInNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);
        itemPrice = findViewById(R.id.price);
        totalPrice = findViewById(R.id.totalPriceInt);
        qty = findViewById(R.id.quantityInt);
        imageID = findViewById(R.id.imageView);

        Intent intent = getIntent();
        imageID.setImageResource(Integer.valueOf(intent.getStringExtra("imageScr")));
        imageInNotification = Integer.valueOf(intent.getStringExtra("imageScr"));
        qty.setText(intent.getStringExtra("qty").toString());
        totalPrice.setText(intent.getStringExtra("price").toString() + " $");
        itemPrice.setText(intent.getStringExtra("itemPrice").toString());

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("Channel ID", " My Notification",
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    public void orderPlaced(View view) {
        startService(new Intent(this, AlarmService.class));
        Toast.makeText(this, "Order Placed", Toast.LENGTH_LONG).show();
       // Intent intent = new Intent(this,AlarmService.class);
        //startService(intent);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, "Channel ID");
        notification.setSmallIcon(imageInNotification);
        notification.setContentTitle("Asso");
        notification.setContentText("Order Placed Succesfully!");



        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(123, notification.build());

    }

    public void toWelcome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }
}

