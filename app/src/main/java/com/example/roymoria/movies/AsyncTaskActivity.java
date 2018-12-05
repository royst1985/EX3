package com.example.roymoria.movies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class AsyncTaskActivity extends AppCompatActivity {

    MovieScreenCountDown mTask = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_counter);
        Button createBtn = findViewById(R.id.btn_create);
        createBtn.setOnClickListener(v -> OnCreateThread());
        Button startBtn = findViewById(R.id.btn_start);
        startBtn.setOnClickListener(v -> OnStartThread());
        Button cancelBtn = findViewById(R.id.btn_cancel);
        cancelBtn.setOnClickListener(v -> OnCancelThread());
    }

    public void OnCreateThread(){
        TextView counterStatusText = findViewById(R.id.txt_status);
        if (mTask == null) {
            mTask = new MovieScreenCountDown(counterStatusText);
            mTask.onPreExecute();
        }
    }

    public void OnStartThread(){
        if (mTask != null && !mTask.IsRunning()) {
            mTask.execute(9, 8, 7, 6, 5, 4, 3, 2, 1);
        }
    }

    public void OnContinueThread(Integer startFrom){
        if (mTask != null && !mTask.IsRunning()) {
            Integer[] numbers = new Integer[startFrom];
            int index = 0;
            for (int i = startFrom; i > 0; i--) {
                numbers[index] = i;
                index++;
            }
            mTask.execute(numbers);
        }
    }

    public void OnCancelThread(){
        if (mTask != null) {
            mTask.cancel(true);
            mTask = null;
        }
    }

    protected void onStop() {
        super.onStop();
        OnCancelThread();
    }

    protected void onDestroy() {
        super.onDestroy();
        OnCancelThread();
    }

    public class MovieScreenCountDown extends AsyncTask<Integer, Integer, String> {
        private TextView mCountDownTextView;
        private Boolean mIsRunning;
        public MovieScreenCountDown(TextView countDownTV) {
            mCountDownTextView = countDownTV;
            mIsRunning = false;
        }

        public boolean IsRunning(){
            return mIsRunning;
        }
        protected void onPreExecute() {
            mCountDownTextView.setText("");
        }

        @Override
        protected String doInBackground(Integer... numbers) {
            mIsRunning = true;
            for (Integer number : numbers) {
                if (!isCancelled()) {
                    publishProgress(number);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }
            return "Enjoy the movie!";
        }
        protected void onProgressUpdate(Integer ... number) {
            mCountDownTextView.setText(String.valueOf(number[0]));
        }

        protected void onPostExecute(String result) {
            mCountDownTextView.setText(result);
            mIsRunning = true;
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        TextView txt = findViewById(R.id.txt_status);
        savedInstanceState.putCharSequence("counter",txt.getText());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            TextView txt = findViewById(R.id.txt_status);
            CharSequence status = savedInstanceState.getCharSequence("counter");
            boolean onRun = true;
            txt.setText(status);
            Integer counter = 0;
            try {
                counter = Integer.parseInt(txt.getText().toString());
            }
            catch (NumberFormatException e)
            {
                onRun = false;
            }
            if (onRun) {
                OnCreateThread();
                OnContinueThread(counter);
            }

        }
        super.onRestoreInstanceState(savedInstanceState);
    }

}

