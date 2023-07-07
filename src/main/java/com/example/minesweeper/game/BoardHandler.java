package com.example.minesweeper.game;

import com.example.minesweeper.media.Audio;
import com.example.minesweeper.media.AudioManager;
import com.example.minesweeper.scene.GameModel;
import javafx.scene.input.MouseButton;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;


public class BoardHandler {

    private final GameModel gameModel;
    private final int rows;
    private final int columns;
    private final int numberOfMines;
    private final Random random = new SecureRandom();
    private int numberOfRemainingTiles;
    private int countTilesExposedInAGuess = 0;
    private int remainingMines;
    private Tile[][] board;
    private boolean isWaitingFirstTileClicked = true;
    private boolean wereBothButtonsPressed = false;

    public BoardHandler(int rows, int columns, int numberOfMines, GameModel gameModel) {
        this.gameModel = gameModel;
        this.rows = rows;
        this.columns = columns;
        this.numberOfMines = numberOfMines;
        remainingMines = numberOfMines;
        numberOfRemainingTiles = rows * columns;
        initializeTileField();
    }

    private void initializeTileField() {
        board = new Tile[rows][columns];
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
                Tile currentTile = new Tile(rowIndex, columnIndex);
                board[rowIndex][columnIndex] = currentTile;
                currentTile.getImageView().setOnMousePressed(
                        mouseEvent -> wereBothButtonsPressed = mouseEvent.isPrimaryButtonDown() && mouseEvent.isSecondaryButtonDown());
                currentTile.getImageView().setOnMouseReleased(mouseEvent -> {
                    if (!mouseEvent.isPrimaryButtonDown() && !mouseEvent.isSecondaryButtonDown()) {
                        if (wereBothButtonsPressed) {
                            processUserClickOn(currentTile);
                        } else if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                            if (isWaitingFirstTileClicked) {
                                generateMinesFromFirstClickOn(currentTile);
                            }
                            processUserClickOn(currentTile);
                        } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                            handleFlag(currentTile);
                            gameModel.getGameController().getRemainingMinesText().setText(
                                    String.valueOf(remainingMines));
                        }
                    }
                });
            }
        }
    }

    private void generateMinesFromFirstClickOn(Tile currentTile) {
        isWaitingFirstTileClicked = false;
        generateMinesInTileField(currentTile);
        gameModel.getTimeCounter().start();
    }

    private void generateMinesInTileField(Tile firstClickedTile) {
        int minesCreatedCounter = 0;
        while (minesCreatedCounter < numberOfMines) {
            Tile tileToBeInsertedMine = getRandomTile();
            if (!tileToBeInsertedMine.isMine() && !tileToBeInsertedMine.isNeighbourOf(
                    firstClickedTile)) {
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

    public void processUserClickOn(Tile tile) {
        countTilesExposedInAGuess = 0;
        if (wereBothButtonsPressed) {
            if (!tile.isNotExposed()) {
                doMultiGuess(tile);
            }
        } else if (tile.isNotFlagged() && tile.isNotExposed()) {
            doSingleGuess(tile);
        }
        if (countTilesExposedInAGuess != 0) {
            if (countTilesExposedInAGuess == 1) {
                AudioManager.playAudioClip(Audio.ONE_TILE);
            } else if (countTilesExposedInAGuess <= 5) {
                AudioManager.playAudioClip(Audio.MANY_TILES);
            } else {
                AudioManager.playAudioClip(Audio.LARGE_TILE_GROUP);
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
            playExplodeSound();
            exposeAllMines();
            gameModel.setGameState(GameState.LOST);
        } else if (tile.isNotExposed()) {
            exposeTile(tile);
            checkIfWon();
            if (tile.isNoMineAround()) {
                OpenAdjacentTilesRecursively(tile);
            }
        }
    }

    private void playExplodeSound() {
        AudioManager.pauseMediaPlayer();
        AudioManager.playAudioClip(Audio.EXPLODE_SOUND);
    }

    private void checkIfWon() {
        countTilesExposedInAGuess++;
        numberOfRemainingTiles--;
        if (numberOfRemainingTiles == numberOfMines) {
            gameModel.setGameState(GameState.WON);
            AudioManager.playAudioClip(Audio.WINNING_SHORT_INFORM);
        }
    }

    private void OpenAdjacentTilesRecursively(Tile tile) {
        for (Tile currentTile : getAdjacentTilesOf(tile)) {
            if (currentTile.isNotExposed()) {
                doSingleGuess(currentTile);
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
            AudioManager.playAudioClip(Audio.UNFLAG);
        } else if (tile.getTileState() == TileState.BLANK) {
            tile.setTileState(TileState.FLAG);
            for (Tile currentTile : getAdjacentTilesOf(tile)) {
                currentTile.increaseNeighbourFlagsCount();
            }
            remainingMines--;
            AudioManager.playAudioClip(Audio.FLAG);
        }
    }

    public int getRemainingMines() {
        return remainingMines;
    }
}
