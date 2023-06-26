package com.example.minesweeper;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class TimeCounter {

    GameModel gameModel;
    private static final int INITIAL_TIME = 0;
    private final Timeline timeline = new Timeline();
    private int elapsedTime = INITIAL_TIME;
    
    public int getElapsedTime() {
		return elapsedTime;
	}

    public TimeCounter(GameModel gameModel) {
        this.gameModel = gameModel;
        initialize();
    }

    public void initialize() {
        elapsedTime = INITIAL_TIME; // Reset the time counter
        gameModel.getGameController().getTimeCounterText().setText(String.valueOf(elapsedTime));
        timeline.stop(); // Stop the timeline before starting a new game
        timeline.getKeyFrames().clear(); // Clear existing key frames
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
            elapsedTime++;
            gameModel.getGameController().getTimeCounterText().setText(String.valueOf(elapsedTime));
        });
        timeline.getKeyFrames().add(keyFrame);
    }

    public void start() {
        timeline.play();
    }

    public void stop() {
        timeline.stop();
    }
}
