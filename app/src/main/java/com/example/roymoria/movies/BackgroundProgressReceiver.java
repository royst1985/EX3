package com.example.roymoria.movies;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class BackgroundProgressReceiver extends BroadcastReceiver {

    public static final String PROGRESS_UPDATE_ACTION = "PROGRESS_UPDATE_ACTION";
    public static final String PROGRESS_VALUE_KEY = "PROGRESS_VALUE_KEY";
    public static final String SERVICE_STATUS = "SERVICE_STATUS";

    @Override
    public void onReceive(Context context, Intent intent) {

        int progress = intent.getIntExtra(PROGRESS_VALUE_KEY, -1);

    }
}
