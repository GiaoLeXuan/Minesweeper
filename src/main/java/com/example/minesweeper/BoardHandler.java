package com.example.minesweeper;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.input.MouseButton;


public class BoardHandler {

    private final GameModel gameModel;
    private final int rows;
    private final int columns;
    private final int numberOfMines;
    public int remainingTiles;
    public int remainingMines;
    private Tile[][] board;

    private final Random random = new SecureRandom();

    private boolean isWaitingFirstTileClicked = true;

    public BoardHandler(int rows, int columns, int numberOfMines,
                        GameModel gameModel) {
        this.gameModel = gameModel;
        this.rows = rows;
        this.columns = columns;
        this.numberOfMines = numberOfMines;
        remainingMines = numberOfMines;
        remainingTiles = rows * columns;
        initializeTileField();
    }

    private void initializeTileField() {
        board = new Tile[rows][columns];
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
                Tile currentTile = new Tile(rowIndex, columnIndex);
                board[rowIndex][columnIndex] = currentTile;
                currentTile.getImageView().setOnMouseClicked(mouseClick -> {
                    if (mouseClick.getButton() == MouseButton.PRIMARY) {
                        if (isWaitingFirstTileClicked) {
                            isWaitingFirstTileClicked = false;
                            generateMinesInTileField(currentTile);
                            gameModel.getTimeCounter().start();
                        }
                        handleUserGuessOn(currentTile);
                    }
                    if (mouseClick.getButton() == MouseButton.SECONDARY) {
                        handleFlag(currentTile);
                        gameModel.getGameController()
                                .getRemainingMinesText()
                                .setText(String.valueOf(remainingMines));
                    }
                });
            }
        }
    }

    private void generateMinesInTileField(Tile firstClickedTile) {
        int minesCreatedCounter = 0;
        while (minesCreatedCounter < numberOfMines) {
            Tile tileToBeInsertedMine = getRandomTile();
            if (!tileToBeInsertedMine.isMine() && !tileToBeInsertedMine.isNeighbourOf(firstClickedTile)) {
                tileToBeInsertedMine.setMine(true);
                updateAdjacentTilesOfMine(tileToBeInsertedMine);
                minesCreatedCounter++;
            }
        }
    }

    private Tile getRandomTile() {
        int rowIndex = random.nextInt(rows);
        int columnIndex = random.nextInt(columns);
        return board[rowIndex][columnIndex];
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
                    tilesAround.add(board[rowIndex][columnIndex]);
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


    public Tile[][] getBoard() {
        return board;
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
            gameModel.setGameState(GameState.LOST);
        } else if (tile.isNotExposed()) {
            exposeTile(tile);
            remainingTiles--;
            if (remainingTiles == numberOfMines) {
                gameModel.setGameState(GameState.WON);
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
                Tile tile = board[rowIndex][columnIndex];
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
            remainingMines++;
        } else if (tile.getTileState() == TileState.BLANK) {
            tile.setTileState(TileState.FLAG);
            for (Tile currentTile : getAdjacentTilesOf(tile)) {
                currentTile.increaseNeighbourFlagsCount();
            }
            remainingMines--;
        }
    }

    public int getRemainingMines() {
        return remainingMines;
    }
    

}
