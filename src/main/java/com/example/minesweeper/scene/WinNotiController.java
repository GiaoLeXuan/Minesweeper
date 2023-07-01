package com.example.minesweeper.scene;

import com.example.minesweeper.media.AudioManager;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

public class WinNotiController {

	private GameModel gameModel;

	@FXML
	private Text yourTime;

	@FXML
	private Text BestScoreText;
	
	public Text getyourTimeText() {
		return yourTime;
	}
	
	public Text getBestScoreText() {
		return BestScoreText;
	}

	public void setGameModel(GameModel gameModel) {
		this.gameModel = gameModel;
	}

	
	public GameModel getGameModel() {
		return gameModel;
	}
	
	public static void playWinMusic() {
		Media music = new Media(AudioManager.getMediaPath("win_sound.mp3"));
		MediaPlayer mediaPlayer = new MediaPlayer(music);
		mediaPlayer.play();
	}
	
	public void backToMenu() {
		SceneManager.switchScene("start-menu.fxml");
	}
	
	public void restartGame() {
		GameController gameController = gameModel.getGameController();
		if(gameController instanceof EasyGameController) {
			SceneManager.switchScene("easy-mode.fxml");
		}

		if(gameController instanceof MediumGameController) {
			SceneManager.switchScene("medium-mode.fxml");
		}

		if(gameController instanceof HardGameController) {
			SceneManager.switchScene("hard-mode.fxml");
		}

	}

	public void viewRecord() {
	    if (gameModel != null) {
	        switch (gameModel.getColumns()) {
	            case EasyGameModel.COLUMNS:
	                SceneManager.switchScene("easy-high-score.fxml");
	                break;
	            case MediumGameModel.COLUMNS:
	                SceneManager.switchScene("medium-high-score.fxml");
	                break;
	            case HardGameModel.COLUMNS:
	                SceneManager.switchScene("hard-high-score.fxml");
	                break;
	        }
	    }
	}
	
	public void showRecordBreakAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Kỷ lục mới!");
        alert.setHeaderText(null);
        alert.setContentText("Bạn đã phá kỷ lục với thời gian " + getGameModel().getTimeCounter().getElapsedTime() + "!");
        alert.showAndWait();
    }
}

