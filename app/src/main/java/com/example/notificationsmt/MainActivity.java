package com.example.notificationsmt;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private final String CHANNEL_ID = "CHANNEL_ID";
    private Button checkDesc;
    private Button AODList;
    private Button remind;
    private TextView bookTitle;
    private TextView invText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        createNC();


        checkDesc = findViewById(R.id.btn1);
        checkDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDesc();
            }
        });

        invText = findViewById(R.id.invText);
        AODList = findViewById(R.id.btn2);
        AODList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = AODList.getText().toString();
                if(temp.equals("DODAJ DO CHCĘ PRZECZYTAĆ")){
                    invText.setVisibility(View.VISIBLE);
                    AODList.setText("USUŃ Z CHCĘ PRZECZYTAĆ");
                } else if(temp.equals("USUŃ Z CHCĘ PRZECZYTAĆ")){
                    invText.setVisibility(View.GONE);
                    AODList.setText("DODAJ DO CHCĘ PRZECZYTAĆ");
                }
            }
        });

        remind = findViewById(R.id.btn3);
        remind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remindBook();
            }
        });
    }

    private void showDesc() {
        bookTitle = findViewById(R.id.bookTitle);
        String textTitle = bookTitle.getText().toString();
        String textContent = "Krótki opis: Ekscytująca historia pełna zwrotów akcji";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.uwedom)
                .setContentTitle(textTitle)
                .setContentText(textContent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1001);
            return;
        }
        notificationManager.notify(1, builder.build());
    }

    private void remindBook() {
        bookTitle = findViewById(R.id.bookTitle);
        String textTitle = bookTitle.getText().toString();
        String textContent = "Pamiętaj, aby znaleźć czas na lekturę!";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.uwedom)
                .setContentTitle(textTitle)
                .setContentText(textContent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1001);
            return;
        }
        notificationManager.notify(1, builder.build());
    }

    private void createNC(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "channelName",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("channelDescription");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}