package com.example.minesweeper.scene;

import com.example.minesweeper.game.RecordFile;

public class HardGameModel extends GameModel {

    public static final int ROWS = 16;
    public static final int COLUMNS = 30;
    public static final int NUMBER_OF_MINES = 99;

    public HardGameModel(GameController gameController) {
        super(gameController, ROWS, COLUMNS, NUMBER_OF_MINES, RecordFile.HARD_RECORD.getFilePath());
    }
}
