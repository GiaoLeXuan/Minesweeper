package com.example.minesweeper;

public class MediumGameController extends GameController{
    @Override
    public void initializeGameModel() {
        setGameModel(new MediumGameModel(this));
    }
}
