package com.example.minesweeper.scene;


public class HardGameController extends GameController {
    @Override
    public void initializeGameModel() {
        setGameModel(new HardGameModel(this));
    }   
}
