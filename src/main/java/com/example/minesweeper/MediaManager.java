package com.example.minesweeper;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MediaManager {

    private static final String pathToMedia = "media/";
    private static final MediaManager instance = new MediaManager();
    private static MediaPlayer mediaPlayer;

    private MediaManager() {
    }

    public static void playMedia(MinesweeperMedia minesweeperMedia) {
        if (mediaPlayer != null) {
            mediaPlayer.dispose();
        }
        Media media = new Media(getMediaPath(minesweeperMedia.getFileName()));
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        });
    }

    public static String getMediaPath(String fileName) {
        return String.valueOf(MinesweeperApplication.class.getResource(pathToMedia + fileName));
    }

    public static void pauseMediaPlayer() {
        mediaPlayer.pause();
    }

    public static void continueMediaPlayer() {
        mediaPlayer.play();
    }

}
