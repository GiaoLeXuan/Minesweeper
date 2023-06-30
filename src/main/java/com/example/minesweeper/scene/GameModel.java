package com.example.minesweeper.scene;

import com.example.minesweeper.game.Record;
import com.example.minesweeper.game.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;

import java.io.IOException;
import java.util.List;

public abstract class GameModel {

    private final int rows;
    private final int columns;
    private final int numberOfMines;
    private final GameController gameController;
    private final TimeCounter timeCounter;
    private final RecordHandler recordHandler = new RecordHandler();

    public GameModel(GameController gameController, int rows, int columns,
                     int numberOfMines) {
        this.gameController = gameController;
        this.rows = rows;
        this.columns = columns;
        this.numberOfMines = numberOfMines;
        timeCounter = new TimeCounter(this);
        startGame();
    }

    public void startGame() {
        initialize();
    }

    protected void initialize() {
        setGameState(GameState.RUNNING);
        initializeBoard();
        timeCounter.initialize();
    }

    private void initializeBoard() {
        BoardHandler boardHandler = new BoardHandler(rows, columns, numberOfMines, this);
        TilePane tilePane = gameController.getTilePane();
        tilePane.getChildren().clear();
        gameController.getRemainingMinesText().setText(String.valueOf(boardHandler.getRemainingMines()));
        Tile[][] tileField = boardHandler.getBoard();
        for (int rowIndex = 0; rowIndex < boardHandler.getRows(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < boardHandler.getColumns(); columnIndex++) {
                tilePane.getChildren().add(tileField[rowIndex][columnIndex].getImageView());
            }
        }
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameState(GameState gameState) {
        setFaceImageCorrespondingTo(gameState);
        if (gameState != GameState.RUNNING) {
            timeCounter.stop();
            if (gameState == GameState.WON) {
                handleWonState();
            } else
                notifyOnLosing();
        }
    }

    private void handleWonState() {
        recordHandler.updateRecords(this, timeCounter.getElapsedTime());
        notifyOnWinning(timeCounter.getElapsedTime());
    }

    private void notifyOnWinning(int elapsedTime) {
        String message = "Congratulations! You won the game in " + elapsedTime + " seconds.";
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setResizable(false);
        alert.setTitle("Congratulations!");
        alert.setHeaderText(null);
        alert.initModality(Modality.NONE);
        String trophyImagePath = getClass().getResource("/com/example/minesweeper/images/trophy.png").toExternalForm();
        Image trophyImage = new Image(trophyImagePath);
        ImageView trophyImageView = new ImageView(trophyImage);
        trophyImageView.setFitWidth(48);
        trophyImageView.setFitHeight(48);
        alert.setGraphic(trophyImageView);
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        Label messageLabel = new Label(message);
        vbox.getChildren().add(messageLabel);
        Label highscoreLabel = new Label("Top 5 Highscores:");
        vbox.getChildren().add(highscoreLabel);
        TableView<Record> highscoreTableView = new TableView<>();
        TableColumn<Record, Integer> rankColumn = new TableColumn<>("Rank");
        TableColumn<Record, String> timeColumn = new TableColumn<>("Time");
        highscoreTableView.getColumns().addAll(rankColumn, timeColumn);

        // Create a list of HighScore objects
        ObservableList<Record> highscoreList = FXCollections.observableArrayList();
        List<Integer> highscores;
        try {
            highscores = getTopHighscoresFromFile();
            for (int i = 0; i < highscores.size(); i++) {
                Record entry = new Record(i + 1, String.valueOf(highscores.get(i)));
                highscoreList.add(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set cell value factories for rank and time columns
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        highscoreTableView.setItems(highscoreList);
        highscoreTableView.setPrefHeight(150);
        highscoreTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        vbox.getChildren().add(highscoreTableView);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setContent(vbox);
        Button restartButton = new Button("Restart");
        restartButton.setOnAction(event -> {
            restartGame();
            alert.close();
        });
        dialogPane.getButtonTypes().clear();
        dialogPane.getButtonTypes().add(ButtonType.CLOSE);
        dialogPane.setExpandableContent(null);
        dialogPane.setPrefWidth(400);
        Node closeButton = dialogPane.lookupButton(ButtonType.CLOSE);
        closeButton.setVisible(false);
        dialogPane.setExpandableContent(restartButton);
        alert.showAndWait();
    }

    private void notifyOnLosing() {
        String message = "Oh no! You hit the mine.";
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setResizable(false);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.initModality(Modality.NONE);
        String trophyImagePath = getClass().getResource("/com/example/minesweeper/images/trophy.png").toExternalForm();
        Image trophyImage = new Image(trophyImagePath);
        ImageView trophyImageView = new ImageView(trophyImage);
        trophyImageView.setFitWidth(48);
        trophyImageView.setFitHeight(48);
        alert.setGraphic(trophyImageView);
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        Label messageLabel = new Label(message);
        vbox.getChildren().add(messageLabel);
        Label highscoreLabel = new Label("Top 5 Highscores:");
        vbox.getChildren().add(highscoreLabel);
        TableView<Record> highscoreTableView = new TableView<>();
        TableColumn<Record, Integer> rankColumn = new TableColumn<>("Rank");
        TableColumn<Record, String> timeColumn = new TableColumn<>("Time");
        highscoreTableView.getColumns().addAll(rankColumn, timeColumn);

        // Create a list of HighScore objects
        ObservableList<Record> highscoreList = FXCollections.observableArrayList();
        List<Integer> highscores;
        try {
            highscores = getTopHighscoresFromFile();
            for (int i = 0; i < highscores.size(); i++) {
                Record entry = new Record(i + 1, String.valueOf(highscores.get(i)));
                highscoreList.add(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set cell value factories for rank and time columns
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        highscoreTableView.setItems(highscoreList);
        highscoreTableView.setPrefHeight(150);
        highscoreTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        vbox.getChildren().add(highscoreTableView);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setContent(vbox);
        Button restartButton = new Button("Restart");
        restartButton.setOnAction(event -> {
            restartGame();
            alert.close();
        });
        dialogPane.getButtonTypes().clear();
        dialogPane.getButtonTypes().add(ButtonType.CLOSE);
        dialogPane.setExpandableContent(null);
        dialogPane.setPrefWidth(400);
        Node closeButton = dialogPane.lookupButton(ButtonType.CLOSE);
        closeButton.setVisible(false);
        dialogPane.setExpandableContent(restartButton);
        alert.showAndWait();
    }

    private List<Integer> getTopHighscoresFromFile() throws IOException {
        String fileName = "";
        String pathOfRecordFolder = "src\\main\\resources\\com\\example\\minesweeper\\records\\";

        switch (columns) {
            case EasyGameModel.COLUMNS -> fileName = "EasyHighScore.txt";
            case MediumGameModel.COLUMNS -> fileName = "MediumHighScore.txt";
            case HardGameModel.COLUMNS -> fileName = "HardHighScore.txt";
        }

        String filePath = pathOfRecordFolder + fileName;
        return recordHandler.loadRecordsFromFile(filePath);
    }

    private void restartGame() {
        gameController.restartButtonOnClicked();
    }


    private void setFaceImageCorrespondingTo(GameState gameState) {
        getGameController().getRestartButton()
                .setGraphic(new ImageView(RestartButton.getInstance()
                        .getFaceImageMap().get(gameState)));
    }

    public TimeCounter getTimeCounter() {
        return timeCounter;
    }

    public int getColumns() {
        return columns;
    }
}