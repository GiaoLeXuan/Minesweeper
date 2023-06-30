package com.example.minesweeper;

import com.example.minesweeper.scene.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class MinesweeperApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        SceneManager.setPrimaryStage(stage);
        SceneManager.switchScene("start-menu.fxml");
    }
}