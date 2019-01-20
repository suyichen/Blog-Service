package com.syc.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by suyichen on 2019/1/18.
 */

public class RemoteService extends Service {

    private static final String TAG = "RemoteService";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBind();
    }

    class MyBind extends MyAIDL.Stub{

        @Override
        public String getString() throws RemoteException {

            String string = "This is the string returned by the server";

            return string;
        }
    }

}
