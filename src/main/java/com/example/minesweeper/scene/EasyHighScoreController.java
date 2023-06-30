package com.example.minesweeper.scene;

import com.example.minesweeper.game.RecordHandler;

public class EasyHighScoreController extends RecordTableController {

    public EasyHighScoreController() {
        super(RecordHandler.pathOfRecordFolder + "EasyHighScore.txt");
    }
}