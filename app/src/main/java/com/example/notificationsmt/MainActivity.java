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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btn_m = findViewById(R.id.message);

        createNC();

        btn_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showN();
            }
        });

    }

    private void showN() {
        String textTitle = "Glekowiadomienia";
        String textContent = "Glekontent";
        Log.d("s", "1");
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.pobrane);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.uwedom)
                .setLargeIcon(largeIcon)
                .setContentTitle(textTitle)
                .setContentText(textContent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1001);
            return;
        }
        Log.d("s", "3");
        notificationManager.notify(1, builder.build());
    }

    private void createNC(){
        Log.d("c", "1");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d("c", "2");
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