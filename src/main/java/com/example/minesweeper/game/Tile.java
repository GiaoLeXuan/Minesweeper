package com.example.minesweeper.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile {

    private final int rowIndex;
    private final int columnIndex;
    private final ImageView imageView =
            new ImageView(new Image(TileState.BLANK.getImagePath()));
    private boolean isMine = false;
    private int neighbourMinesCount = 0;
    private int neighbourFlagsCount = 0;
    private TileState tileState = TileState.BLANK;

    public Tile(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public TileState getTileState() {
        return tileState;
    }

    public void setTileState(TileState tileState) {
        this.tileState = tileState;
        imageView.setImage(TileImage.getInstance().getImageList()
                .get(tileState));
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void increaseNeighbourFlagsCount() {
        neighbourFlagsCount++;
    }

    public void increaseNeighbourMinesCount() {
        neighbourMinesCount++;
    }

    public void decreaseNeighbourFlagsCount() {
        neighbourFlagsCount--;
    }

    public int getNeighbourMinesCount() {
        return neighbourMinesCount;
    }

    public boolean isNumberOfFlagsEqualMines() {
        return neighbourFlagsCount == neighbourMinesCount;
    }

    public boolean isNoMineAround() {
        return neighbourMinesCount == 0;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean isMine) {
        this.isMine = isMine;
    }

    public boolean isNotExposed() {
        return tileState == TileState.BLANK || tileState == TileState.FLAG;
    }

    public boolean isNotFlagged() {
        return tileState != TileState.FLAG;
    }

    public boolean isNeighbourOf(Tile otherTile) {
        return otherTile.getRowIndex() - 1 <= rowIndex
                && otherTile.getRowIndex() + 1 >= rowIndex
                && otherTile.getColumnIndex() - 1 <= columnIndex
                && otherTile.getColumnIndex() + 1 >= columnIndex;
    }
}