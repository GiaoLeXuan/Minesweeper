package com.example.minesweeper;

public class EasyGameController extends GameController {
    @Override
    public void initializeGameModel() {
        setGameModel(new EasyGameModel(this));
    }
}

