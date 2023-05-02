package com.example.minesweeper;

public enum GameState {
    RUNNING("smiling-face.png"),
    WON("cool-face.png"),
    LOST("dead-face.png");

    private final String faceImageFile;

    GameState(String faceImageFile) {
        this.faceImageFile = faceImageFile;
    }

    public String getImagePath() {
        return ImageHandler.getImagePath(faceImageFile);
    }
}
