package com.example.minesweeper.scene;

import com.example.minesweeper.media.Audio;
import com.example.minesweeper.media.AudioManager;
import javafx.fxml.FXML;

public class LoseNotificationController {
    private GameModel gameModel;

    @FXML
    public void initialize() {
        AudioManager.playAudioClip(Audio.LOSE_MUSIC);
        AudioManager.continueMediaPlayer();
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