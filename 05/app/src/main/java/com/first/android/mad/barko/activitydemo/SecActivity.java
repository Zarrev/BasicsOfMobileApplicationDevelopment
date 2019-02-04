package com.first.android.mad.barko.activitydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class SecActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        Log.e("SecActivity","Activity has created. The application is in the Created state.");

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
        MainActivity.counter++;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("SecActivity", "The application has entered into the Started state.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("SecActivity", "The application has entered into the Stopped state.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("SecActivity", "The application has destroyed. :(");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("SecActivity", "The application has entered into the Paused state.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("SecActivity", "The application has entered into the Resumed state.");
    }
}
