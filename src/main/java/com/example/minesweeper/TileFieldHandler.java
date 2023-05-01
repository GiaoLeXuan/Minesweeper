package com.example.minesweeper;

import java.util.ArrayList;
import java.util.Random;


public class TileFieldHandler {
    private Tile[][] tileField;
    private final int rows;
    private final int columns;
    private final int numberOfMines;

    private int remainingTiles;

    public TileFieldHandler(int rows, int columns, int numberOfMines) {
        this.rows = rows;
        this.columns = columns;
        this.numberOfMines = numberOfMines;
        remainingTiles = rows * columns;
        initializeTileField();
    }

    private void initializeTileField() {
        tileField = new Tile[rows][columns];
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
                tileField[rowIndex][columnIndex] = new Tile(rowIndex, columnIndex);
            }
        }
        generateMinesInTileField();
    }

    private void generateMinesInTileField() {
        Random random = new Random();
        int countCreatedMines = 0;
        while (countCreatedMines < numberOfMines) {
            int row = random.nextInt(rows);
            int column = random.nextInt(columns);
            Tile tile = tileField[row][column];
            if (!tile.isMine()) {
                tile.setMine(true);
                updateAdjacentTilesOfMine(tile);
                countCreatedMines++;
            }
        }
    }

    private void updateAdjacentTilesOfMine(Tile mineTile) {
        for (Tile adjacentTile : getAdjacentTilesOf(mineTile)) {
            if (!adjacentTile.isMine()) {
                adjacentTile.increaseNeighbourMinesCount();
            }
        }
    }

    public ArrayList<Tile> getAdjacentTilesOf(Tile tile) {
        ArrayList<Tile> tilesAround = new ArrayList<>();
        for (int rowIndex = tile.getRowIndex() - 1; rowIndex <= tile.getRowIndex() + 1; rowIndex++) {
            for (int columnIndex = tile.getColumnIndex() - 1; columnIndex <= tile.getColumnIndex() + 1; columnIndex++) {
                if (rowIndex >= 0 && columnIndex >= 0 && rowIndex < rows && columnIndex < columns) {
                    tilesAround.add(tileField[rowIndex][columnIndex]);
                }
            }
        }
        return tilesAround;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }


    public Tile[][] getTileField() {
        return tileField;
    }

    public void exposeTile(Tile tile) {
        tile.setTileState(TileState.tileStates[tile.getNeighbourMinesCount()]);
    }

    public void handleUserGuessOn(Tile tile) {
        if (tile.isNotFlagged()) {
            if (tile.isNotExposed()) {
                doSingleGuess(tile);
            } else {
                doMultiGuess(tile);
            }
        }
    }

    private void doMultiGuess(Tile tile) {
        if (tile.isNumberOfFlagsEqualMines()) {
            for (Tile currentTile : getAdjacentTilesOf(tile)) {
                if (currentTile.isNotFlagged()) {
                    doSingleGuess(currentTile);
                }
            }
        }
    }

    private void doSingleGuess(Tile tile) {
        if (tile.isMine()) {
            exposeAllMines();
        } else {
            exposeTile(tile);
            remainingTiles--;
            if(remainingTiles == numberOfMines) {
                System.out.println("Victory!");
            }
            if (tile.isNoMineAround()) {
                for (Tile currentTile : getAdjacentTilesOf(tile)) {
                    if (currentTile.isNotExposed()) {
                        doSingleGuess(currentTile);
                    }
                }
            }
        }
    }

    private void exposeAllMines() {
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
                Tile tile = tileField[rowIndex][columnIndex];
                if (tile.isMine()) {
                    tile.setTileState(TileState.HIT_MINE);
                }
            }
        }
    }

    public void handleFlag(Tile tile) {
        if (tile.getTileState() == TileState.FLAG) {
            tile.setTileState(TileState.BLANK);
            for (Tile currentTile : getAdjacentTilesOf(tile)) {
                currentTile.decreaseNeighbourFlagsCount();
            }
        } else if (tile.getTileState() == TileState.BLANK) {
            tile.setTileState(TileState.FLAG);
            for (Tile currentTile : getAdjacentTilesOf(tile)) {
                currentTile.increaseNeighbourFlagsCount();
            }
        }
    }
}
