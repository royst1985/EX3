package com.example.roymoria.movies;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BackgroundServiceActivity extends AppCompatActivity implements ServiceConnection {

    private BackgroundProgressReceiver mBackgroundProgressReceiver;
    MyService mService;
    boolean mIsConnected;
    TextView progressText;
    Intent mIntentService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_service);
        progressText = findViewById(R.id.progress_percent);
        Button startServiceBtn = findViewById(R.id.start_service);
        Button startInterServiceBtn = findViewById(R.id.start_interservice);
        mIntentService = null;

        startServiceBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mIntentService ==null) {
                    mIntentService = new Intent(v.getContext(), MyService.class);
                }
                else
                {
                    if (mService!=null){
                        mService.isDestroyed = true;

                        stopService(mIntentService);
                        //mService.stopService(mIntentService);
                        mIntentService = new Intent(v.getContext(), MyService.class);
                    }
                }
                if(mIsConnected) {
                    startService(mIntentService);
                }
            }
        });

        startInterServiceBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mIntentService ==null) {
                    mIntentService = new Intent(v.getContext(), MyIntentService.class);
                }
                else
                {
                    if (mService!=null){
                        stopService(mIntentService);
                        mIntentService = new Intent(v.getContext(), MyIntentService.class);
                    }
                }
                if(mIsConnected) {
                    startService(mIntentService);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        subscribeForProgressUpdates();
    }

    private void subscribeForProgressUpdates() {
        if (mBackgroundProgressReceiver == null) {
            mBackgroundProgressReceiver = new BackgroundProgressReceiver();
        }
        IntentFilter progressUpdateActionFilter = new IntentFilter(BackgroundProgressReceiver.PROGRESS_UPDATE_ACTION);
        registerReceiver(mBackgroundProgressReceiver, progressUpdateActionFilter);
    }

    @Override
    protected void onPause() {
        if(mBackgroundProgressReceiver != null) {
            unregisterReceiver(mBackgroundProgressReceiver);
        }
        super.onPause();
    }


    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
        mIsConnected = true;
        mService = ((MyService.MyBinder) binder).getService();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, this, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(this /* ServiceConnection */);
        mService = null;
        mIsConnected = false;

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        mIsConnected = false;
        mService = null;
    }

    class BackgroundProgressReceiver extends BroadcastReceiver {

        public static final String PROGRESS_UPDATE_ACTION = "PROGRESS_UPDATE_ACTION";
        public static final String PROGRESS_VALUE_KEY = "PROGRESS_VALUE_KEY";
        public static final String SERVICE_STATUS = "SERVICE_STATUS";

        @Override
        public void onReceive(Context context, Intent intent) {

            int progress = intent.getIntExtra(PROGRESS_VALUE_KEY, -1);
            progressText.setText(String.valueOf(progress));
        }
    }
}
