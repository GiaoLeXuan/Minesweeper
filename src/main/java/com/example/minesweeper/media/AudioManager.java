package com.example.minesweeper.media;

import com.example.minesweeper.MinesweeperApplication;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class AudioManager {

    private static final String pathToMedia = "media/";
    private static final AudioManager instance = new AudioManager();
    private static MediaPlayer mediaPlayer;

    private AudioManager() {
    }

    public static void playMedia(Audio audio) {
        if (mediaPlayer != null) {
            mediaPlayer.dispose();
        }
        Media media = new Media(getMediaPath(audio.getFileName()));
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
