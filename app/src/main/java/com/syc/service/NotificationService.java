package com.syc.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by suyichen on 2019/1/9.
 */

public class NotificationService extends Service {

    public static final String TAG = "NotificationService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate()");
        Notification notification = new Notification.Builder(this)
                .setContentTitle("This is Content Title")
                .setContentText("This is Content Text")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.suyichen))
                .build();
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(this.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
