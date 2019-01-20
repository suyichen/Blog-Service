package com.syc.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "BindServiceDemo";

    private BindService.MyBinder myBinder;

    private MyAIDL myAIDL;

    //connect service
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e(TAG, "onServiceConnected()开始绑定");
            myBinder = (BindService.MyBinder) iBinder;
            myBinder.download();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e(TAG, "onServiceDisconnected()解除绑定");
        }
    };

    //connect remote service
    private ServiceConnection remoteConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAIDL = MyAIDL.Stub.asInterface(service);
            try {
                String str = myAIDL.getString();
                Log.e(TAG,"return :" + str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myAIDL = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bindService(View view) {
        Log.e(TAG, "bindService()");
        Intent bindIntent = new Intent(MainActivity.this, BindService.class);
        bindService(bindIntent, conn, BIND_AUTO_CREATE);
    }

    public void unbindService(View view) {
        Log.e(TAG, "unbindService()");
        unbindService(conn);
    }

    public void startNotificationService(View view) {
        Log.e(TAG, "startNotificationService()");
        Intent startNotificationService = new Intent(MainActivity.this,NotificationService.class);
        startService(startNotificationService);
    }

    public void stopNotificationService(View view) {
        Log.e(TAG, "stopNotificationService()");
        Intent stopNotificationService = new Intent(MainActivity.this,NotificationService.class);
        stopService(stopNotificationService);
    }

    public void startRemoteService(View view) {
        Log.e(TAG, "startRemoteService()");
        Intent remoteServiceIntent = new Intent();
        remoteServiceIntent.setAction("com.example.service.MyService");
        remoteServiceIntent.setPackage("com.syc.service");
        bindService(remoteServiceIntent,remoteConn,BIND_AUTO_CREATE);
    }

    public void stopRemoteService(View view) {
        Log.e(TAG, "stopRemoteService()");
        unbindService(remoteConn);
    }
}
