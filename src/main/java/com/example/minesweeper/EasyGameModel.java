package com.example.minesweeper;

import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class EasyGameModel {

    private final EasyGameController easyGameController;
    private TileFieldHandler tileFieldHandler;

    public EasyGameModel(EasyGameController easyGameController) {
        this.easyGameController = easyGameController;
    }

    public void start() {
        initialize();
    }

    private void initialize() {
        setGameState(GameState.RUNNING);
        tileFieldHandler = new TileFieldHandler(9, 9, 10, this);
        addTileFieldToTilePane();
    }

    private void addTileFieldToTilePane() {
        TilePane tilePane = easyGameController.getTilePane();
        tilePane.getChildren().clear();
        easyGameController.getRemainingMinesText()
                .setText(String.valueOf(tileFieldHandler.getRemainingMines()));

        Tile[][] tileField = tileFieldHandler.getTileField();
        for (int rowIndex = 0; rowIndex < tileFieldHandler.getRows(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < tileFieldHandler.getColumns(); columnIndex++) {
                tilePane.getChildren()
                        .add(tileField[rowIndex][columnIndex].getImageView());
            }
        }
    }

    public void setGameState(GameState gameState) {
        easyGameController.getRestartButton()
                .setGraphic(new ImageView(RestartButton.getInstance()
                        .getFaceImageMap().get(gameState)));
    }

    public EasyGameController getEasyGameController() {
        return easyGameController;
    }
}
