package com.example.minesweeper.scene;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.example.minesweeper.game.TimeCounter;
import com.example.minesweeper.media.*;

public class WinNotiController {

	private GameModel gameModel;
	
	@FXML
	public Text yourTime;
	
	@FXML
	public Text best;
	
//	public void showBest() {
//		String pathOfRecordFolder = "src\\main\\resources\\com\\example\\minesweeper\\records\\";
//		String fileName = "";
//		
//		switch (gameModel.getColumns()) {
//        	case EasyGameModel.COLUMNS -> fileName = "EasyHighScore.txt";
//        	case MediumGameModel.COLUMNS -> fileName = "MediumHighScore.txt";
//        	case HardGameModel.COLUMNS -> fileName = "HardHighScore.txt";
//		}
//		
//		String filePath = pathOfRecordFolder + fileName;
//		
//        BufferedReader reader;
//		try {
//			reader = new BufferedReader(new FileReader(filePath));
//			String line = reader.readLine();
//			best.setText(line);
//			System.out.println(best);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	public void setGameModel(GameModel gameModel) {
		this.gameModel = gameModel;
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
}

