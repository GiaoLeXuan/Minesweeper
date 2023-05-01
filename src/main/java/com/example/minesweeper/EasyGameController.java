package com.example.minesweeper;

import javafx.fxml.FXML;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.TilePane;

public class EasyGameController {

    @FXML
    private TilePane tilePane;

    @FXML
    public void initialize() {
        EasyGameModel easyGameModel = new EasyGameModel();
        TileFieldHandler tileFieldHandler = easyGameModel.getTileFieldHandler();
        Tile[][] tileField = tileFieldHandler.getTileField();
        for (int rowIndex = 0; rowIndex < tileFieldHandler.getRows(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < tileFieldHandler.getColumns(); columnIndex++) {
                Tile currentTile = tileField[rowIndex][columnIndex];
                currentTile.getImageView().setOnMouseClicked(mouseEvent -> {
                    if(mouseEvent.getButton() == MouseButton.PRIMARY) {
                        tileFieldHandler.handleUserGuessOn(currentTile);
                    }
                    if(mouseEvent.getButton() == MouseButton.SECONDARY) {
                        tileFieldHandler.handleFlag(currentTile);
                    }
                });
                tilePane.getChildren().add(currentTile.getImageView());
            }
        }
    }

}
