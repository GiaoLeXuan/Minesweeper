package com.example.minesweeper;

import com.example.minesweeper.game.Configuration;
import com.example.minesweeper.game.RecordFile;
import com.example.minesweeper.media.Audio;
import com.example.minesweeper.media.AudioManager;
import com.example.minesweeper.scene.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;

public class MinesweeperApplication extends Application {

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) {
        Configuration.loadConfiguration();
        SceneManager.setPrimaryStage(stage);
        SceneManager.switchScene("start-menu.fxml");
    }

    @Override
    public void stop() {
        Configuration.saveConfiguration();
    }
}