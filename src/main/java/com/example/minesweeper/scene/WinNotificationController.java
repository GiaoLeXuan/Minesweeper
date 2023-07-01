package com.example.minesweeper.scene;

import com.example.minesweeper.media.Audio;
import com.example.minesweeper.media.AudioManager;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class WinNotificationController {

    @FXML
    public Text yourTimeText;
    @FXML
    public Text bestScoreText;
    private GameModel gameModel;

    @FXML
    public void initialize() {
        AudioManager.playAudioClip(Audio.WIN_MUSIC);
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

    public void viewRecord() {
        if (gameModel != null) {
            switch (gameModel.getColumns()) {
                case EasyGameModel.COLUMNS -> SceneManager.switchScene("easy-high-score.fxml");
                case MediumGameModel.COLUMNS -> SceneManager.switchScene("medium-high-score.fxml");
                case HardGameModel.COLUMNS -> SceneManager.switchScene("hard-high-score.fxml");
            }
        }
    }

    public Text getYourTimeText() {
        return yourTimeText;
    }

    public Text getBestScoreText() {
        return bestScoreText;
    }
}

