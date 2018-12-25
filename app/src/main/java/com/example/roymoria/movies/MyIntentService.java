package com.example.roymoria.movies;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

public class MyIntentService extends IntentService {

    boolean isDestroyed;


    public MyIntentService(String name) {
        super(name);
    }

    public MyIntentService() {
        super("null");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        isDestroyed = false;
        for (int i = 0; i <= 100 && !isDestroyed; i++) {
            SystemClock.sleep(100);
            Intent broadcastIntent = new Intent(BackgroundServiceActivity.
                    BackgroundProgressReceiver.PROGRESS_UPDATE_ACTION);
            broadcastIntent.putExtra(BackgroundServiceActivity.
                    BackgroundProgressReceiver.PROGRESS_VALUE_KEY, i);
            sendBroadcast(broadcastIntent);
        }
    }

    @Override
    public void onDestroy() {
        isDestroyed = true;
        super.onDestroy();
    }
}
