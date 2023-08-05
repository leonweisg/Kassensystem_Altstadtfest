package com.example.kassensystemaltstadtfest;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Date;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private TextView dateAndTimeTextView;
    private Timer timer;
    private Handler handler = new Handler();
    private final int UPDATE_INTERVAL = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout scrollLayout = findViewById(R.id.scrollLayoutLinear);

        for (int i = 0; i < 5; i++) {
            View itemView = LayoutInflater.from(this).inflate(R.layout.item_layout, null);

            TextView quantityTextView = itemView.findViewById(R.id.quantityTextView);
            TextView itemNameTextView = itemView.findViewById(R.id.itemNameTextView);
            TextView priceTextView = itemView.findViewById(R.id.priceTextView);

            quantityTextView.setText("1x");
            itemNameTextView.setText("Veltins 0,33L");
            priceTextView.setText("4,00 €");

            scrollLayout.addView(itemView);
        }


        dateAndTimeTextView = findViewById(R.id.dateAndTime);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> updateDateTime());
            }
        }, 0, UPDATE_INTERVAL);
    }

    private void updateDateTime() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        String formattedDate = dateFormat.format(currentDate);
        String formattedTime = timeFormat.format(currentDate);

        String dateTimeString = formattedDate + " | " + formattedTime;
        dateAndTimeTextView.setText(dateTimeString);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

}