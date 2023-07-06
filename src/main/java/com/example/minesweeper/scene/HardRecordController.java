package com.example.minesweeper.scene;

import com.example.minesweeper.game.RecordFile;

public class HardRecordController extends RecordTableController {
    public HardRecordController() {
        super(RecordFile.HARD_RECORD.getFilePath());
    }
}
