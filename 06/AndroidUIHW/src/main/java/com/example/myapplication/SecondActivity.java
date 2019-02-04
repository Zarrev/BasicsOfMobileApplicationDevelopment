package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView title, artist, description;
    //private ImageView cover;
    private Button backButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        Intent intent = getIntent();
        title = (TextView) findViewById(R.id.textView);
        title.setText(intent.getStringExtra("Title"));
        artist = (TextView) findViewById(R.id.textView2);
        artist.setText(intent.getStringExtra("Artist"));
        description = (TextView) findViewById(R.id.textView3);
        description.setText(intent.getStringExtra("Description"));

        backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //if(!intent.getStringExtra("Cover").equals("No Cover")) {
            //set cover photo
        //}
    }
}
