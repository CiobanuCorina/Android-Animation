package com.example.lab4;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Button start;
    ImageView candyEater;
    AnimationDrawable eatingCandy;
    Button stop;
    ProgressBar progressBar;
    Timer timer = new Timer();
    int counter;
    CountDownTimer countDownTimer;
    TextView progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.button);
        candyEater = (ImageView) findViewById(R.id.imageView);
        candyEater.setImageResource(R.drawable.eating);
        eatingCandy = (AnimationDrawable) candyEater.getDrawable();
        stop = (Button) findViewById(R.id.button2);
        progressBar = (ProgressBar) findViewById(R.id.progressBar3);
        progress = (TextView) findViewById(R.id.textView);
        progress.setText("0%");

        progressBar.setProgress(0);


        start.setOnClickListener(view -> {
            eatingCandy.start();
            timer = new Timer();
            counter = 0;
            countDownTimer = new CountDownTimer(10000, 1000) {
                @Override
                public void onTick(long l) {
                    progressBar.setProgress(counter);
                    String progressString = counter + "%";
                    progress.setText(progressString);
                    counter = counter + 10;
                }

                @Override
                public void onFinish() {
                    progress.setText("100%");
                }
            }.start();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    eatingCandy.stop();
                    progressBar.setProgress(0);
                    countDownTimer.cancel();
                    timer.cancel();
                }
            }, 10000);
        });
        stop.setOnClickListener(view -> {
            eatingCandy.stop();
            countDownTimer.cancel();
            timer.cancel();
        });
    }

}