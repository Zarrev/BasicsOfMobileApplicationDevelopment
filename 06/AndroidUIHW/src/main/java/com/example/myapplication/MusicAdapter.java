package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyViewHolder> {

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView artist;
        public Button details;

        public MyViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            artist = (TextView) view.findViewById(R.id.artist);
            details = (Button) view.findViewById(R.id.details);
        }
    }

    private Context mContext;
    private ArrayList<Music> songs = new ArrayList<>();

    public MusicAdapter(ArrayList<Music> mysongs, Context context) {
        songs.addAll(mysongs);
        mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_row, viewGroup, false);
        return new MyViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final Music song = songs.get(i);

        TextView textView = myViewHolder.title;
        textView.setText(song.getmTitle());
        textView = myViewHolder.artist;
        textView.setText(song.getmArtist());

        Button button = myViewHolder.details;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SecondActivity.class);
                intent.putExtra("Cover", song.getmCoverphoto());
                intent.putExtra("Title", song.getmTitle());
                intent.putExtra("Artist", song.getmArtist());
                intent.putExtra("Description", song.getmDescription());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return songs.size();
    }
}
