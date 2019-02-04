package com.example.myapplication;

import java.util.ArrayList;

public class Music {
    private String mTitle;
    private String mArtist;
    private String mDescription;
    private String mCoverphoto;

    public Music(String title, String artist, String description, String coverphoto) {
        mTitle = title;
        mArtist = artist;
        mDescription = description;
        mCoverphoto = coverphoto;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmArtist() {
        return mArtist;
    }

    public String getmDescription() { return mDescription; }

    public String getmCoverphoto() { return mCoverphoto; }

    public static ArrayList<Music> createMusicList(int numMusic) {
        ArrayList<Music> songs = new ArrayList<>();

        for (int i = 1; i <= numMusic; i++) {
            songs.add(new Music("Title " + i, "Artist " + i, createDescription(i), "No Cover"));
        }
        return songs;
    }

    private static String createDescription(int num) {
        String desc = "";
        for(int i = 0; i < 20; i++){
            desc += "Description" + num + " ";
        }
        return desc;
    }
}
