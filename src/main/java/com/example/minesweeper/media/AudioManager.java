package com.example.minesweeper.media;

import com.example.minesweeper.MinesweeperApplication;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.Objects;

public class AudioManager {

    private static final String pathToMedia = "media/";
    private static final AudioManager instance = new AudioManager();
    private static volatile MediaPlayer mediaPlayer;
    private static boolean mute = false;

    private AudioManager() {}

    public static boolean isMute() {
        return mute;
    }

    public static void setMute(boolean mute) {
        AudioManager.mute = mute;
        if (mute) {
            mediaPlayer.setVolume(0);
        } else {
            mediaPlayer.setVolume(1);
        }
    }

    public static void playAudio(Audio audio) {
        if (mediaPlayer != null) {
            mediaPlayer.dispose();
        }
        Media media = new Media(getMediaPath(audio.getFileName()));
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(Duration.ZERO);
            if (!mute) {
                mediaPlayer.play();
            }
        });
    }

    public static void playAudioClip(Audio audio) {
        AudioClip audioClip = new AudioClip(getMediaPath(audio.getFileName()));
        if (!mute) {
            audioClip.play();
        }
    }

    public static String getMediaPath(String fileName) {
        return String.valueOf(MinesweeperApplication.class.getResource(
                pathToMedia + fileName));
    }

    public static void pauseMediaPlayer() {
        mediaPlayer.pause();
    }

    public static void continueMediaPlayer() {
        mediaPlayer.play();
    }

}
