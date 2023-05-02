package com.example.minesweeper;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class RestartButton {
    public Map<GameState, Image> faceImageMap = new HashMap<>();

    private static final RestartButton RESTART_BUTTON = new RestartButton();

    private RestartButton() {
        for (GameState gameState : GameState.values()) {
            faceImageMap.put(gameState, new Image(gameState.getImagePath()));
        }
    }

    public static RestartButton getInstance() {
        return RESTART_BUTTON;
    }

    public Map<GameState, Image> getFaceImageMap() {
        return faceImageMap;
    }
}
