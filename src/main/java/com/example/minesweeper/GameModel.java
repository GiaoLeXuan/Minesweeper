package com.example.minesweeper;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.util.Duration;

public abstract class GameModel {

    private final int rows;
    private final int columns;
    private final int numberOfMines;
    private final GameController gameController;
    private final TimeCounter timeCounter;
    private final RecordHandler recordHandler = new RecordHandler();
    private BoardHandler boardHandler;
    private MediaPlayer mediaPlayer;

    public GameModel(GameController gameController, int rows, int columns,
                     int numberOfMines) {
        this.gameController = gameController;
        this.rows = rows;
        this.columns = columns;
        this.numberOfMines = numberOfMines;
        timeCounter = new TimeCounter(this);
        initializeMedia();
    }

    public void start() {
        initialize();
    }

    protected void initialize() {
        setGameState(GameState.RUNNING);
        setBoardHandler(new BoardHandler(rows, columns, numberOfMines, this));
        timeCounter.initialize();
    }

    private void initializeMedia() {
        Media mainTheme = new Media(MediaHandler.getMediaPath("mixkit-feeling-happy-5.mp3"));
        mediaPlayer = new MediaPlayer(mainTheme);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        });
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
            }
        }
    }

    private void handleWonState() {      
        recordHandler.updateRecords(this, timeCounter.getElapsedTime());
        notifyOnWinning(timeCounter.getElapsedTime());
    }

    private void notifyOnWinning(int elapsedTime) {
        String message = "Congratulations! You won the game in " + elapsedTime + " seconds.";
        Alert alert = new Alert(AlertType.INFORMATION);
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
    	
    	switch (boardHandler.getColumns()) {
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

    public BoardHandler getBoardHandler() {
        return boardHandler;
    }

    public void setBoardHandler(BoardHandler boardHandler) {
        this.boardHandler = boardHandler;
        gameController.addTileFieldToTilePane();
    }

    public TimeCounter getTimeCounter() {
        return timeCounter;
    }
}