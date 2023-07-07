package com.example.minesweeper.scene;

import com.example.minesweeper.media.Audio;
import com.example.minesweeper.media.AudioManager;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ResultNotificationController {
    @FXML
    private Text informText;
    @FXML
    private Text yourTimeText;
    @FXML
    private Text bestScoreText;
    private GameModel gameModel;

    @FXML
    public void initialize() {
        AudioManager.playAudio(Audio.END_GAME_MUSIC);
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void backToMenu() {
        SceneManager.switchScene("start-menu.fxml");
        AudioManager.playAudio(Audio.MAIN_THEME);
    }

    public void restartGame() {
        if (gameModel instanceof EasyGameModel) {
            SceneManager.switchScene("easy-mode.fxml");
        }
        if (gameModel instanceof MediumGameModel) {
            SceneManager.switchScene("medium-mode.fxml");
        }
        if (gameModel instanceof HardGameModel) {
            SceneManager.switchScene("hard-mode.fxml");
        }
        AudioManager.playAudio(Audio.MAIN_THEME);
    }

    public void viewRecord() {
        if (gameModel != null) {
            switch (gameModel.getColumns()) {
                case EasyGameModel.COLUMNS ->
                        SceneManager.switchScene("easy-high-score.fxml");
                case MediumGameModel.COLUMNS ->
                        SceneManager.switchScene("medium-high-score.fxml");
                case HardGameModel.COLUMNS ->
                        SceneManager.switchScene("hard-high-score.fxml");
            }
        }
    }

    public Text getYourTimeText() {
        return yourTimeText;
    }

    public Text getBestScoreText() {
        return bestScoreText;
    }

    public Text getInformText() {
        return informText;
    }
}

