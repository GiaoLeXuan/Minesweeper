package com.example.minesweeper;

public class HardGameController extends GameController{
    @Override
    public void initializeGameModel() {
        setGameModel(new HardGameModel(this));
    }
}
