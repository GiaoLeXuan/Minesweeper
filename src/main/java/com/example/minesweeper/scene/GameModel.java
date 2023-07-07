package com.example.minesweeper.scene;

import com.example.minesweeper.game.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.io.IOException;

public abstract class GameModel {

    private final int rows;
    private final int columns;
    private final int numberOfMines;
    private final GameController gameController;
    private final TimeCounter timeCounter;
    private final RecordHandler recordHandler;

    private GameState gameState = GameState.RUNNING;

    public GameModel(GameController gameController, int rows, int columns, int numberOfMines, String recordFileName) {
        this.gameController = gameController;
        this.rows = rows;
        this.columns = columns;
        this.numberOfMines = numberOfMines;
        timeCounter = new TimeCounter(this);
        recordHandler = new RecordHandler(recordFileName);
        start();
    }

    public void start() {
        initialize();
    }

    protected void initialize() {
        setGameState(GameState.RUNNING);
        BoardHandler boardHandler = new BoardHandler(rows, columns,
                numberOfMines, this);
        TilePane tilePane = gameController.getTilePane();
        tilePane.getChildren().clear();
        gameController.getRemainingMinesText().setText(
                String.valueOf(boardHandler.getRemainingMines()));
        Tile[][] tileField = boardHandler.getBoard();
        for (int rowIndex = 0; rowIndex < boardHandler.getRows(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < boardHandler.getColumns(); columnIndex++) {
                tilePane.getChildren().add(
                        tileField[rowIndex][columnIndex].getImageView());
            }
        }
        timeCounter.initialize();
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        setFaceImageCorrespondingTo(gameState);
        if (gameState != GameState.RUNNING) {
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, e -> disableEventToGameplay()),
                    new KeyFrame(Duration.seconds(2.0), e -> {
                        if (gameState == GameState.WON) {
                            updateRecords();
                        }
                        displayNotification();
                    }));
            timeline.playFromStart();
        }
    }

    private void setFaceImageCorrespondingTo(GameState gameState) {
        getGameController().getRestartButton().setGraphic(new ImageView(
                RestartButton.getInstance().getFaceImageMap().get(gameState)));
    }

    private void disableEventToGameplay() {
        timeCounter.stop();
        gameController.getTilePane().setDisable(true);
        gameController.getRestartButton().setDisable(true);
    }

    private void updateRecords() {
        recordHandler.updateRecords(timeCounter.getElapsedTime());
    }

    private void displayNotification() {
        SceneManager.switchScene("notify_result.fxml");
        WinNotificationController notificationController = SceneManager.getFxmlLoader().getController();
        notificationController.setGameModel(this);
        String elapsedTime = switch (gameState) {
            case WON -> String.valueOf(timeCounter.getElapsedTime());
            case LOST -> "__";
            default -> "0";
        };
        String bestRecord = switch (gameState) {
            case WON -> String.valueOf(getBestRecord());
            case LOST -> "__";
            default -> "0";
        };
        notificationController.getYourTimeText().setText((elapsedTime));
        notificationController.getBestScoreText().setText(bestRecord);
    }

    public int getBestRecord() {
        try {
            return recordHandler.loadRecordsFromFile(
                    recordHandler.getRecordFileName()).get(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TimeCounter getTimeCounter() {
        return timeCounter;
    }

    public int getColumns() {
        return columns;
    }
}