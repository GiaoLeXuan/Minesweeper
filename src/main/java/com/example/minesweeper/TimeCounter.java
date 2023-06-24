package com.example.minesweeper;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class TimeCounter {

    GameModel gameModel;
    private static final int INITIAL_TIME = 0;
    private int timeCounter;
    private final Timeline timeline = new Timeline();

    public TimeCounter(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void start() {
        // Start counting
        timeCounter = INITIAL_TIME; // Reset the time counter
        gameModel.getGameController().getTimeCounterText().setText(String.valueOf(timeCounter));
        timeline.stop(); // Stop the timeline before starting a new game
        timeline.getKeyFrames().clear(); // Clear existing key frames
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
            timeCounter++;
            gameModel.getGameController().getTimeCounterText().setText(String.valueOf(timeCounter));
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play(); // Start the timeline for the new game
    }

    public void stop() {
        timeline.stop();
    }
}
