package com.example.pruebaservice;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "BroadcastTest";
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this,NotificationService.class);
        startService(intent);
        //intent = new Intent(this, BroadcastService.class);
    }

    public void onReceive(Context context, Intent intent) {
        //updateUI(intent);
    }

    /*private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateUI(intent);
        }
    };*/


    @Override
    public void onResume() {
        super.onResume();
        startService(intent);
        //registerReceiver(broadcastReceiver, new IntentFilter(BroadcastService.BROADCAST_ACTION));
        }

    @Override
    public void onPause() {
        super.onPause();
        //unregisterReceiver(broadcastReceiver);
        stopService(intent);
        }

    private void updateUI(Intent intent) {
        String counter = intent.getStringExtra("counter");
        String time = intent.getStringExtra("time");
        Log.d(TAG, counter);
        Log.d(TAG, time);

        TextView txtDateTime = (TextView) findViewById(R.id.txtDateTime);
        TextView txtCounter = (TextView) findViewById(R.id.txtCounter);
        txtDateTime.setText(time);
        txtCounter.setText(counter);
        }


}
