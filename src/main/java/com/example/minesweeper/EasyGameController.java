package com.example.minesweeper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;

public class EasyGameController {

    private final EasyGameModel easyGameModel = new EasyGameModel(this);

    @FXML
    private TilePane tilePane;

    @FXML
    private Text remainingMinesText;

    @FXML
    private Button restartButton;

    @FXML
    private Text timeCounterText;

    @FXML
    public void restartButtonOnClicked() {
        easyGameModel.start();
    }

    public TilePane getTilePane() {
        return tilePane;
    }

    public Text getRemainingMinesText() {
        return remainingMinesText;
    }

    public Button getRestartButton() {
        return restartButton;
    }

    public Text getTimeCounterText() {
        return timeCounterText;
    }
}

