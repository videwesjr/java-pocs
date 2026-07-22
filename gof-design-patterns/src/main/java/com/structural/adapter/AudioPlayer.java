package com.structural.adapter;

public class AudioPlayer implements MediaPlayer {

    @Override
    public void play(String audioType, String fileName) {
        if ("mp3".equals(audioType)) {
            System.out.println("Playing mp3 file: " + fileName);
        } else if ("vlc".equals(audioType) || "mp4".equals(audioType)) {
            new MediaAdapter(audioType).play(audioType, fileName);
        } else {
            throw new IllegalArgumentException("Unsupported audio type: " + audioType);
        }
    }
}
