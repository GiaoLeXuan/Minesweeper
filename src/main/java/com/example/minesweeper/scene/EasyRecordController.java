package com.example.minesweeper.scene;

import com.example.minesweeper.game.RecordFile;

public class EasyRecordController extends RecordTableController {

    public EasyRecordController() {
        super(RecordFile.EASY_RECORD.getFilePath());
    }
}
