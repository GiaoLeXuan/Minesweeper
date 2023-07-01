package com.example.minesweeper.scene;

import com.example.minesweeper.media.AudioManager;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class LoseNotiController {
	private static MediaPlayer mediaPlayer;	
	private GameModel gameModel;
	private GameController gameController;
	
	public static void playLoseMusic() {
		Media music = new Media(AudioManager.getMediaPath("lose_sound.mp3"));
		mediaPlayer = new MediaPlayer(music);
		mediaPlayer.play();
	}
	
	public void setGameModel(GameModel gameModel) {
		this.gameModel = gameModel;
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

}