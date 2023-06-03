package com.example.minesweeper;

import javafx.scene.image.ImageView;

public abstract class GameModel {

    private final int rows;

    private final int columns;
    private final int numberOfMines;
    private final GameController gameController;
    private BoardHandler boardHandler;

    public GameModel(GameController gameController, int rows, int columns,
                     int numberOfMines) {
        this.rows = rows;
        this.columns = columns;
        this.numberOfMines = numberOfMines;
        this.gameController = gameController;
    }

    public void start() {
        initialize();
    }

    protected void initialize() {
        setGameState(GameState.RUNNING);
        setBoardHandler(new BoardHandler(rows, columns, numberOfMines, this));
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameState(GameState gameState) {
        setFaceImageAccordingTo(gameState);
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
}
