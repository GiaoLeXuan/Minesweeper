package com.example.minesweeper.scene;

public class EasyGameController extends GameController {
    @Override
    public void initializeGameModel() {
        setGameModel(new EasyGameModel(this));
    }
}

