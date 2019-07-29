package com.example.pruebaservice;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import java.util.Date;

public class NotificationService extends Service {
    private static final String TAG = "BroadcastService";
    private final Handler handler = new Handler();
    private static final String CHANNEL_ID="Veramed";
    NotificationChannel channel;

    Intent intent;
    int counter = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        Log.d(TAG, "Creando Sercicio");
        //handler.postDelayed(sendUpdatesToAct, 1000); // 1 second

    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.d(TAG, "Iniciando Servicio");
        handler.removeCallbacks(sendUpdatesToAct);
        handler.postDelayed(sendUpdatesToAct, 1000); // 1 second

    }

    private Runnable sendUpdatesToAct = new Runnable() {
        public void run() {
            DisplayLoggingNot();
            handler.postDelayed(this, 10000); // 10 seconds
        }
    };

    private void DisplayLoggingNot() {
        //Log.d(TAG, "Enviando notifiacion");
        counter ++;
            Log.d(TAG, "Enviando notifiacion");
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.notification_icon)
                    .setContentTitle("Veramed - Alarma")
                    .setContentText("Apertura de Tienda")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

       // notificationId is a unique int for each notification that you must define
        notificationManager.notify(counter, builder.build());

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(sendUpdatesToAct);
        super.onDestroy();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}