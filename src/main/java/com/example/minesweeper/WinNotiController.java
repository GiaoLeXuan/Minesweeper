package com.example.minesweeper;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class WinNotiController {

	private GameModel gameModel;

	public void setGameModel(GameModel gameModel) {
		this.gameModel = gameModel;
	}

	public static void playWinMusic() {
		Media music = new Media(MediaHandler.getMediaPath("win_sound.mp3"));
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
}


