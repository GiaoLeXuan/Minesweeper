package com.example.minesweeper;

import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public abstract class GameModel {

    private final int rows;

    private final int columns;
    private final int numberOfMines;
    private final GameController gameController;
    private BoardHandler boardHandler;

    private final TimeCounter timeCounter;

    private MediaPlayer mediaPlayer;
    
	public GameModel(GameController gameController, int rows, int columns,
                     int numberOfMines) {
        this.gameController = gameController;
        this.rows = rows;
        this.columns = columns;
        this.numberOfMines = numberOfMines;
        timeCounter = new TimeCounter(this);
    }

    public void start() {
        initialize();
    }

    protected void initialize() {
        setGameState(GameState.RUNNING);
        setBoardHandler(new BoardHandler(rows, columns, numberOfMines, this));
        initializeMedia();
    }

    private void initializeMedia() {
        Media mainTheme = new Media(MediaHandler.getMediaPath("mixkit-feeling-happy-5.mp3"));
        mediaPlayer = new MediaPlayer(mainTheme);
        mediaPlayer.setAutoPlay(true);
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameState(GameState gameState) {
        setFaceImageAccordingTo(gameState);
        if(gameState != GameState.RUNNING) {
            timeCounter.stop();
            if(gameState == GameState.WON) {
                showCompletionTime(timeCounter.getElapsedTime());
            }
        }
    }

    private void showCompletionTime(int elapsedTime) {
        String message = "Congratulations! You won the game in " + elapsedTime + " seconds.";

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void setFaceImageAccordingTo(GameState gameState) {
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
}
