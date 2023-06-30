package com.example.minesweeper.scene;

public class MediumGameController extends GameController {
    @Override
    public void initializeGameModel() {
        setGameModel(new MediumGameModel(this));
    }
}
