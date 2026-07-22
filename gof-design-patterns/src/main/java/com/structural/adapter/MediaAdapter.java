package com.structural.adapter;

public class MediaAdapter implements MediaPlayer {

    private final AdvancedMediaPlayer advancedMediaPlayer;

    public MediaAdapter(String audioType) {
        this.advancedMediaPlayer = switch (audioType) {
            case "vlc" -> new VlcPlayer();
            case "mp4" -> new Mp4Player();
            default -> throw new IllegalArgumentException("Unsupported audio type: " + audioType);
        };
    }

    @Override
    public void play(String audioType, String fileName) {
        switch (audioType) {
            case "vlc" -> advancedMediaPlayer.playVlc(fileName);
            case "mp4" -> advancedMediaPlayer.playMp4(fileName);
            default -> throw new IllegalArgumentException("Unsupported audio type: " + audioType);
        }
    }
}
