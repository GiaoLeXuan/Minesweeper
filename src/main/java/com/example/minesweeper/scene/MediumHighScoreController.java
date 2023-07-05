package com.example.minesweeper.scene;

import com.example.minesweeper.game.RecordHandler;

public class MediumHighScoreController extends RecordTableController {
    public MediumHighScoreController() {
        super(RecordHandler.getFilePath("MediumHighScore.txt"));
    }
}
