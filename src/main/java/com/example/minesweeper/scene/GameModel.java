package com.example.minesweeper.scene;

import java.io.IOException;
import java.util.List;

import com.example.minesweeper.game.BoardHandler;
import com.example.minesweeper.game.GameState;
import com.example.minesweeper.game.RecordHandler;
import com.example.minesweeper.game.Tile;
import com.example.minesweeper.game.TimeCounter;
import com.example.minesweeper.media.AudioManager;

import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
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
            winNotiController.getyourTimeText().setText(String.valueOf(timeCounter.getElapsedTime()));
            // Get Best Score 
            try {
				int bestscore = getTopHighscoresFromFile().get(0);
	            winNotiController.getBestScoreText().setText(String.valueOf(bestscore));
	            if (timeCounter.getElapsedTime() == bestscore)
	            	winNotiController.showRecordBreakAlert();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }

	private List<Integer> getTopHighscoresFromFile() throws IOException {
        String fileName = "";
        String pathOfRecordFolder = "src\\main\\resources\\com\\example\\minesweeper\\records\\";

        switch (columns) {
            case EasyGameModel.COLUMNS -> fileName = "EasyHighScore.txt";
            case MediumGameModel.COLUMNS -> fileName = "MediumHighScore.txt";
            case HardGameModel.COLUMNS -> fileName = "HardHighScore.txt";
        }

        String filePath = pathOfRecordFolder + fileName;
        return recordHandler.loadRecordsFromFile(filePath);
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