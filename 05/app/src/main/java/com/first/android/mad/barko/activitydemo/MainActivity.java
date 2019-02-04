package com.first.android.mad.barko.activitydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/*
I made two activity to test what happening with the first and the second activity while I am switching
between them.
I were getting to know the AndroidManifest.xml and the activities' xml.
I checked the overrideable functions.
I searched some hints on the android's developer side about the navigation between the activites.
I checked the possibilites of the xml's design and test editors.
I got to know the onClick attribute, Logcat and the structure of an android project.
Logging experiences:
    - The onCreate(), onStart() and onResume() run in line.
    - If the phone is sleeping then the activity goes into Paused state and after to the Stopped.
    - The activity call the onStart() and the onResume() in line after I awoke the phone.
    - If I change activity then the new one call the onStart() and the onResume() and after that the
        old one call the onPause() and onStop() in line.
    - After I wrote the sentences above, I have just realized... oh yeah the running is parallel :)
        So the function callings in line but two different type of activity can run its function while
        another activity is working.
    - When I press the back button what brings me back to the parent activity, the parent activity
        has been destroyed and recreated what was surprising for me.
    - The child activity called the onDestroy() function after I touched the back arrow on the screen.
    - If I click on the red square, so I use the force stop of the running program then it won't
        write out the destroy log what made me surprised. I don't what happening with my program.
        Maybe it is that like an exit function calling.
    - If I close the program on my phone then the onDestroy() won't run (or just not logging, but I
        think it cannot be)
Summary: I got the expected behaviour (what I saw in the Activity Life Cycle figure)
 */
public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "Extra message";
    public static int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MainActivity","Activity has created. The application is in the Created state.");

        if (counter > 0) {
            TextView textView = findViewById(R.id.textView);
            textView.setText("Back to the HelloWorld!");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("MainActivity", "The application has entered into the Started state.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("MainActivity", "The application has entered into the Stopped state.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("MainActivity", "The application has destroyed. :(");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("MainActivity", "The application has entered into the Paused state.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("MainActivity", "The application has entered into the Resumed state.");
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, SecActivity.class);
        String msg = "You pushed the mainActivity button.";
        intent.putExtra(EXTRA_MESSAGE, msg);
        startActivity(intent);
    }
}
