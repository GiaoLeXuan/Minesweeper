package com.example.minesweeper.media;

import javafx.animation.Timeline;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class AudioManager {
    private static volatile MediaPlayer mediaPlayer;
    private static boolean mute = false;

    private AudioManager(){}

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
        Media media = new Media(audio.getFileResource());
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
        mediaPlayer.pause();
        AudioClip audioClip = new AudioClip(audio.getFileResource());
        if (!mute) {
            audioClip.play();
        }
        mediaPlayer.play();
    }

    public static void pauseMediaPlayer() {
        mediaPlayer.pause();
    }

    public static void continueMediaPlayer() {
        mediaPlayer.play();
    }
}
