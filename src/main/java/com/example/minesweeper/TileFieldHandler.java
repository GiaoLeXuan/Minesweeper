package com.example.minesweeper;

import java.util.ArrayList;
import java.util.Random;


public class TileFieldHandler {

    private static final IntegerCoordinate[] adjacentCoordinates = new IntegerCoordinate[]{
            new IntegerCoordinate(-1, -1), new IntegerCoordinate(-1, 0), new IntegerCoordinate(-1, 1),
            new IntegerCoordinate(0, -1), new IntegerCoordinate(0, 1), new IntegerCoordinate(1, -1),
            new IntegerCoordinate(1, 0), new IntegerCoordinate(1, 1)};
    private final Tile[][] tileField;
    private final int rows;
    private final int columns;

    private final int numberOfMines;

    public TileFieldHandler(int rows, int columns, int numberOfMines) {
        this.rows = rows;
        this.columns = columns;
        this.numberOfMines = numberOfMines;
        tileField = new Tile[rows][columns];
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
                tileField[rowIndex][columnIndex] = new Tile();
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
            if (tileField[row][column].getTileState() != TileState.MINE) {
                tileField[row][column].setTileState(TileState.MINE);
                updateAdjacentTilesOfMine(row, column);
                countCreatedMines++;
            }
        }
    }

    private void updateAdjacentTilesOfMine(int rowIndex, int columnIndex) {
        for (Tile tile : getAdjacentTiles(rowIndex, columnIndex)) {
            calculateTileNumber(tile);
        }
    }

    private ArrayList<Tile> getAdjacentTiles(int rowIndex, int columnIndex) {
        ArrayList<Tile> result = new ArrayList<>();
        for (IntegerCoordinate adjacentCoordinate : adjacentCoordinates) {
            Tile currentTile = getValidatedTile(rowIndex + adjacentCoordinate.x,
                    columnIndex + adjacentCoordinate.y);
            if (currentTile != null) {
                result.add(currentTile);
            }
        }
        return result;
    }

    private Tile getValidatedTile(int rowIndex, int columnsIndex) {
        if (isValidTileIndex(rowIndex, columnsIndex)) {
            return tileField[rowIndex][columnsIndex];
        } else {
            return null;
        }
    }

    private boolean isValidTileIndex(int rowIndex, int columnIndex) {
        return (rowIndex >= 0 && rowIndex < rows && columnIndex >= 0 && columnIndex < columns);
    }

    private static void calculateTileNumber(Tile currentTile) {
        currentTile.numberOfMinesAround++;
        currentTile.setTileState(switch (currentTile.numberOfMinesAround) {
            case 0 -> TileState.EXPOSED;
            case 1 -> TileState.NUMBER_ONE;
            case 2 -> TileState.NUMBER_TWO;
            case 3 -> TileState.NUMBER_THREE;
            case 4 -> TileState.NUMBER_FOUR;
            case 5 -> TileState.NUMBER_FIVE;
            case 6 -> TileState.NUMBER_SIX;
            case 7 -> TileState.NUMBER_SEVEN;
            case 8 -> TileState.NUMBER_EIGHT;
            default ->
                    throw new IllegalStateException("Unexpected value: " + currentTile.numberOfMinesAround);
        });
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

    public void exposeTilesRecursively(Tile currentTile) {

    }
}
