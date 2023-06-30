package com.example.minesweeper.scene;

import com.example.minesweeper.game.RecordHandler;

public class HardHighScoreController extends RecordTableController {
    public HardHighScoreController() {
        super(RecordHandler.pathOfRecordFolder + "HardHighScore.txt");
    }
}
