package com.example.minesweeper;

public class EasyHighScoreController extends RecordTableController {

    public EasyHighScoreController() {
        super(RecordHandler.pathOfRecordFolder + "EasyHighScore.txt");
    }
}
