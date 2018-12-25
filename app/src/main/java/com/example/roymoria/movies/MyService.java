package com.example.roymoria.movies;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import android.os.Process;


public class MyService extends Service {
    public static final String TAG = "BackgroundHandler";

    private static final int FOREGROUND_ID = 1338;

    class MyBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

    MyBinder mBinder = new MyBinder();
    Looper mServiceLooper;
    ServiceHandler mServiceHandler;

    boolean isDestroyed;
    public MyService() {
    }

    @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        isDestroyed = false;
        // call a new service handler. The service ID can be used to identify the service
        Message message = mServiceHandler.obtainMessage();
        message.arg1 = startId;
        mServiceHandler.sendMessage(message);
        return START_STICKY;
    }

    private Notification createNotification() {

        return new NotificationCompat.Builder(this)
                .setOngoing(true)
                // ADDITIONAL PROPERTIES
                .build();
    }



    @Override
    public void onCreate() {
        super.onCreate();
        // To avoid cpu-blocking, we create a background handler to run our service
        HandlerThread thread = new HandlerThread(TAG, Process.THREAD_PRIORITY_BACKGROUND);
        // start the new handler thread
        thread.start();
        mServiceLooper = thread.getLooper();
        // start the service using the background handler
        mServiceHandler = new ServiceHandler(mServiceLooper);

    }


    @Override
    public void onDestroy() {
        isDestroyed = true;
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper){
            super(looper);}

            @Override public void handleMessage(Message msg) {
// Well calling mServiceHandler.sendMessage(message);  from onStartCommand this method will be called.
            // Add your cpu-blocking activity here

                isDestroyed = false;
                for (int i = 0; i <= 100 && !isDestroyed; i++) {
                SystemClock.sleep(1000);
                Intent intent = new Intent(BackgroundProgressReceiver.PROGRESS_UPDATE_ACTION);
                intent.putExtra(BackgroundProgressReceiver.PROGRESS_VALUE_KEY, i);
                sendBroadcast(intent);
            }
            // the msg.arg1 is the startId used in the onStartCommand,so we can track the running service here.
            stopSelf(msg.arg1);
        }
    }

}
