package com.example.minesweeper.scene;

import com.example.minesweeper.game.RecordFile;

public class MediumRecordController extends RecordTableController {
    public MediumRecordController() {
        super(RecordFile.MEDIUM_RECORD.getFilePath());
    }
}
