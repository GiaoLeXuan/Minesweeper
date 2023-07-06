package com.example.minesweeper.scene;

import com.example.minesweeper.media.Audio;
import com.example.minesweeper.media.AudioManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.util.Duration;

public class LoseNotificationController {
    private GameModel gameModel;

    @FXML
    public void initialize() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            AudioManager.playAudioClip(Audio.LOSE_MUSIC);
        }), new KeyFrame(Duration.seconds(3.0),
                e -> AudioManager.continueMediaPlayer()));
        timeline.playFromStart();
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void backToMenu() {
        SceneManager.switchScene("start-menu.fxml");
    }

    public void restartGame() {
        GameController gameController = gameModel.getGameController();

        if (gameController instanceof EasyGameController) {
            SceneManager.switchScene("easy-mode.fxml");
        }

        if (gameController instanceof MediumGameController) {
            SceneManager.switchScene("medium-mode.fxml");
        }

        if (gameController instanceof HardGameController) {
            SceneManager.switchScene("hard-mode.fxml");
        }
    }
}