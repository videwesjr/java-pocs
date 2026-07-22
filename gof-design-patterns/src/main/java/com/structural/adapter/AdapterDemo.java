package com.structural.adapter;

public final class AdapterDemo {

    private AdapterDemo() {
    }

    public static void run() {
        System.out.println("=== Adapter ===");

        MediaPlayer player = new AudioPlayer();
        player.play("mp3", "song.mp3");
        player.play("vlc", "movie.vlc");
        player.play("mp4", "video.mp4");
    }
}
