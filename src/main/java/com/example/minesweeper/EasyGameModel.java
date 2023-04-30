package com.example.minesweeper;

public class EasyGameModel {

    public static final int TILE_SIZE = 24;

    private final TileFieldHandler tileFieldHandler;


    public EasyGameModel() {
        tileFieldHandler = new TileFieldHandler(10, 10, 10);
    }

    public TileFieldHandler getTileFieldHandler() {
        return tileFieldHandler;
    }
}
