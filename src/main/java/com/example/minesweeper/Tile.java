package com.example.minesweeper;

public class Tile {
    private TileState tileState;

    public int numberOfMinesAround = 0;

    public Tile() {
        tileState = TileState.EXPOSED;
    }

    public TileState getTileState() {
        return tileState;
    }

    public void setTileState(TileState tileState) {
        this.tileState = tileState;
    }
}
