package com.example.minesweeper;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
                ImageView tileImageView = new ImageView(new Image(TileState.BLANK.getImagePath()));
                Tile currentTile = tileField[rowIndex][columnIndex];
                tileImageView.setOnMouseClicked(mouseEvent -> {
                    if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                        tileImageView.setImage(new Image(currentTile.getTileState().getImagePath()));
                        tileFieldHandler.exposeTilesRecursively(currentTile);
                    } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                        tileImageView.setImage(new Image(TileState.FLAG.getImagePath()));
                    }
                });
                tilePane.getChildren().add(tileImageView);
            }
        }
    }
}
