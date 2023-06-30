package com.example.minesweeper;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class WinNotiController {

	private static MediaPlayer mediaPlayer;	
	private GameModel gameModel;
	private GameController gameController;

	public void setGameModel(GameModel gameModel) {
		this.gameModel = gameModel;
	}

	public static void playWinMusic() {
		Media music = new Media(MediaHandler.getMediaPath("win_sound.mp3"));
		mediaPlayer = new MediaPlayer(music);
		mediaPlayer.play();
	}
	
	public void backToMenu() {
		SceneManager.switchScene("start-menu.fxml");
	}
	
	public void restartGame() {
		gameController = gameModel.getGameController();
		
		if(gameController instanceof EasyGameController) {
			SceneManager.switchScene("easy-mode.fxml");
			gameModel.start();
		}
		
		if(gameController instanceof MediumGameController) {
			SceneManager.switchScene("medium-mode.fxml");
			gameModel.start();
		}
		
		if(gameController instanceof HardGameController) {
			SceneManager.switchScene("hard-mode.fxml");
			gameModel.start();
		}
		
	}

	public void viewRecord() {
	    if (gameModel != null) {
	        switch (gameModel.getBoardHandler().getColumns()) {
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


