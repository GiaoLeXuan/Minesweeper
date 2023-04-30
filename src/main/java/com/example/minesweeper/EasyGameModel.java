package com.example.minesweeper;

public class EasyGameModel {

    private final TileFieldHandler tileFieldHandler;


    public EasyGameModel() {
        tileFieldHandler = new TileFieldHandler(10, 10, 10);
    }

    public TileFieldHandler getTileFieldHandler() {
        return tileFieldHandler;
    }
}
