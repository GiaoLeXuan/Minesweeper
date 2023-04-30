package com.example.minesweeper;

import javafx.application.Application;
import javafx.stage.Stage;

public class MinesweeperApplication extends Application {

    @Override
    public void start(Stage stage) {
        SceneManager.setPrimaryStage(stage);
        SceneManager.switchScene("start-menu.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}