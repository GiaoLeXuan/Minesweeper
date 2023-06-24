package com.example.minesweeper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;

public abstract class GameController {

    private GameModel gameModel;

    @FXML
    private TilePane tilePane;

    @FXML
    private Text remainingMinesText;

    @FXML
    private Button restartButton;

    @FXML
    private Text timeCounterText;

    public GameController() {
        //TODO improve initialization of game model
        initializeGameModel();
    }

    public abstract void initializeGameModel();

    public void addTileFieldToTilePane() {
        tilePane.getChildren().clear();
        BoardHandler boardHandler = gameModel.getBoardHandler();
        remainingMinesText.setText(String.valueOf(boardHandler.getRemainingMines()));
        Tile[][] tileField = boardHandler.getBoard();
        for (int rowIndex = 0; rowIndex < boardHandler.getRows(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < boardHandler.getColumns(); columnIndex++) {
                tilePane.getChildren()
                        .add(tileField[rowIndex][columnIndex].getImageView());
            }
        }
    }

    public Text getRemainingMinesText() {
        return remainingMinesText;
    }

    public Button getRestartButton() {
        return restartButton;
    }

    @FXML
    public void restartButtonOnClicked() {
        gameModel.start();
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public Text getTimeCounterText() {
        return timeCounterText;
    }
}
