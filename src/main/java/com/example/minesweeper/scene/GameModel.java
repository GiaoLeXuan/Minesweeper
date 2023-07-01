package com.example.minesweeper.scene;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import com.example.minesweeper.game.*;
import com.example.minesweeper.media.AudioManager;

public abstract class GameModel {

    private final int rows;
    private final int columns;
    private final int numberOfMines;
    private final GameController gameController;
    private final TimeCounter timeCounter;
    private final RecordHandler recordHandler = new RecordHandler();
    private MediaPlayer mediaPlayer;

    
	public GameModel(GameController gameController, int rows, int columns,
                     int numberOfMines) {
        this.gameController = gameController;
        this.rows = rows;
        this.columns = columns;
        this.numberOfMines = numberOfMines;
        timeCounter = new TimeCounter(this);
        initializeMedia();
        start();
    }

	public void start() {
        initialize();
    }

    protected void initialize() {
        setGameState(GameState.RUNNING);
        BoardHandler boardHandler = new BoardHandler(rows, columns, numberOfMines, this);
        TilePane tilePane = gameController.getTilePane();
        tilePane.getChildren().clear();
        gameController.getRemainingMinesText().setText(String.valueOf(boardHandler.getRemainingMines()));
        Tile[][] tileField = boardHandler.getBoard();
        for (int rowIndex = 0; rowIndex < boardHandler.getRows(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < boardHandler.getColumns(); columnIndex++) {
                tilePane.getChildren()
                        .add(tileField[rowIndex][columnIndex].getImageView());
            }
        }
        timeCounter.initialize();
    }

    private void initializeMedia() {
        Media mainTheme = new Media(AudioManager.getMediaPath("mixkit-feeling-happy-5.mp3"));
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
            else {
                handleLoseState();
            }
        }
    }
   
    private void handleLoseState() {
    	stopMedia();
//    	try {
////            String winScene = "lose.fxml";
////            Parent root = FXMLLoader.load(getClass().getResource(winScene));
////            SceneManager.switchScene(winScene);
//    		SceneManager.switchScene("lose.fxml");
//            LoseNotiController loseNotiController = SceneManager.getFxmlLoader().getController();
//            if (loseNotiController != null) {
//            	loseNotiController.setGameModel(this);
//            	loseNotiController.playLoseMusic();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        SceneManager.switchScene("lose.fxml");
        LoseNotiController loseNotiController = SceneManager.getFxmlLoader().getController();
        if (loseNotiController != null) {
            loseNotiController.setGameModel(this);
            LoseNotiController.playLoseMusic();
        }
	}

	private void handleWonState() {
        recordHandler.updateRecords(this, timeCounter.getElapsedTime());
        stopMedia();
        SceneManager.switchScene("win.fxml");
        WinNotiController winNotiController = SceneManager.getFxmlLoader().getController();
        if (winNotiController != null) {
            winNotiController.setGameModel(this);
            WinNotiController.playWinMusic();
        }
    }

    private void setFaceImageCorrespondingTo(GameState gameState) {
        getGameController().getRestartButton()
                .setGraphic(new ImageView(RestartButton.getInstance()
                        .getFaceImageMap().get(gameState)));
    }

    public TimeCounter getTimeCounter() {
        return timeCounter;
    }

    public int getColumns() {
        return columns;
    }
}