package com.example.minesweeper;

public class EasyGameModel extends GameModel {

    public static final int ROWS = 9;
    public static final int COLUMNS = 9;
    public static final int NUMBER_OF_MINES = 10;
    public EasyGameModel(EasyGameController easyGameController) {
        super(easyGameController, ROWS, COLUMNS, NUMBER_OF_MINES);
    }
}
