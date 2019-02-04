package com.example.myapplication;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    ArrayList<Music> songs;
    LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
    private DividerItemDecoration horizontalDivider;
    LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this);
    private DividerItemDecoration verticalDivider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        songs = Music.createMusicList(20);
        MusicAdapter adapter = new MusicAdapter(songs, MainActivity.this);
        recyclerView.setAdapter(adapter);

        horizontalDivider = new DividerItemDecoration(recyclerView.getContext(), 1);
        verticalDivider = new DividerItemDecoration(recyclerView.getContext(), 0);

        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(verticalLayoutManager);
            recyclerView.addItemDecoration(horizontalDivider);
        } else {
            recyclerView.setLayoutManager(horizontalLayoutManager);
            recyclerView.addItemDecoration(verticalDivider);
        }
    }
}
