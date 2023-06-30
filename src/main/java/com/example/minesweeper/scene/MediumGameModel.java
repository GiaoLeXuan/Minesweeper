package com.example.minesweeper.scene;

public class MediumGameModel extends GameModel {
    public static final int ROWS = 16;
    public static final int COLUMNS = 16;
    public static final int NUMBER_OF_MINES = 40;

    public MediumGameModel(GameController gameController) {
        super(gameController, ROWS, COLUMNS, NUMBER_OF_MINES);
    }
}
