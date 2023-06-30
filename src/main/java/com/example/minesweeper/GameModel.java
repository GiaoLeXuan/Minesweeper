package com.example.minesweeper;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public abstract class GameModel {

    private final int rows;
    private final int columns;
    private final int numberOfMines;
    private final GameController gameController;
    private final TimeCounter timeCounter;
    private final RecordHandler recordHandler = new RecordHandler();
    private BoardHandler boardHandler;
    private MediaPlayer mediaPlayer;

    
	public GameModel(GameController gameController, int rows, int columns,
                     int numberOfMines) {
        this.gameController = gameController;
        this.rows = rows;
        this.columns = columns;
        this.numberOfMines = numberOfMines;
        timeCounter = new TimeCounter(this);
        initializeMedia();
    }

	public void start() {
        initialize();
    }

    protected void initialize() {
        setGameState(GameState.RUNNING);
        setBoardHandler(new BoardHandler(rows, columns, numberOfMines, this));
        timeCounter.initialize();
    }

    private void initializeMedia() {
        Media mainTheme = new Media(MediaHandler.getMediaPath("mixkit-feeling-happy-5.mp3"));
        mediaPlayer = new MediaPlayer(mainTheme);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        });
    }
    
    public void stopMedia() {
    	mediaPlayer.pause();
    }
    
    public void continueMedia() {
    	mediaPlayer.play();
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameState(GameState gameState) {
        setFaceImageCorrespondingTo(gameState);
        if (gameState != GameState.RUNNING) {
            timeCounter.stop();
            if (gameState == GameState.WON) {
                handleWonState();
            }
            else
                handleLoseState();
        }
    }
   
    private void handleLoseState() {
    	stopMedia();
    	try {
            String winScene = "lose.fxml";
            Parent root = FXMLLoader.load(getClass().getResource(winScene));
            SceneManager.switchScene(winScene);
            LoseNotiController loseNotiController = (LoseNotiController) SceneManager.getFxmlLoader().getController();
            if (loseNotiController != null) {
            	loseNotiController.setGameModel(this);
            	loseNotiController.playLoseMusic();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	private void handleWonState() {
        recordHandler.updateRecords(this, timeCounter.getElapsedTime());
        stopMedia();

        try {
            String winScene = "win.fxml";
            Parent root = FXMLLoader.load(getClass().getResource(winScene));
            SceneManager.switchScene(winScene);
            WinNotiController winNotiController = (WinNotiController) SceneManager.getFxmlLoader().getController();
            if (winNotiController != null) {
                winNotiController.setGameModel(this);
                WinNotiController.playWinMusic();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	private void restartGame() {
    	gameController.restartButtonOnClicked();
	}
    
    private void setFaceImageCorrespondingTo(GameState gameState) {
        getGameController().getRestartButton()
                .setGraphic(new ImageView(RestartButton.getInstance()
                        .getFaceImageMap().get(gameState)));
    }

    public BoardHandler getBoardHandler() {
        return boardHandler;
    }

    public void setBoardHandler(BoardHandler boardHandler) {
        this.boardHandler = boardHandler;
        gameController.addTileFieldToTilePane();
    }

    public TimeCounter getTimeCounter() {
        return timeCounter;
    }
    
    public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}
}