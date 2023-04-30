package com.example.minesweeper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    private Stage primaryStage;

    private static SceneManager sceneManager;

    private SceneManager() {}

    public static void switchScene(String sceneName) {
        if (sceneManager == null) {
            throw new NullPointerException("Scene Manager class have no instance.");
        }
        Scene scene;
        try {
            scene = generateScene(sceneName);
            sceneManager.primaryStage.setScene(scene);
            sceneManager.primaryStage.setResizable(false);
            sceneManager.primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Scene generateScene(String sceneName) throws IOException {
        return new Scene(new FXMLLoader(MinesweeperApplication.class.getResource(sceneName)).load());
    }

    public static void setPrimaryStage(Stage primaryStage) {
        if (sceneManager == null) {
            sceneManager = new SceneManager();
        }
        sceneManager.primaryStage = primaryStage;
    }

}
