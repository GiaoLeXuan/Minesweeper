package com.example.minesweeper;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;

public abstract class GameController {

    private GameModel gameModel;

    @FXML
    private TilePane tilePane;

    @FXML
    private Text remainingMinesText;

    @FXML
    private Button restartButton;

    @FXML
    private Text timeCounterText;

    @FXML
    public void initialize() {
        initializeGameModel();
    }

    public abstract void initializeGameModel();

    public void addTileFieldToTilePane() {
        tilePane.getChildren().clear();
        BoardHandler boardHandler = gameModel.getBoardHandler();
        remainingMinesText.setText(String.valueOf(boardHandler.getRemainingMines()));
        Tile[][] tileField = boardHandler.getBoard();
        for (int rowIndex = 0; rowIndex < boardHandler.getRows(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < boardHandler.getColumns(); columnIndex++) {
                tilePane.getChildren()
                        .add(tileField[rowIndex][columnIndex].getImageView());
            }
        }
    }

    public Text getRemainingMinesText() {
        return remainingMinesText;
    }

    public Button getRestartButton() {
        return restartButton;
    }

    @FXML
    public void restartButtonOnClicked() {
        gameModel.start();
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public Text getTimeCounterText() {
        return timeCounterText;
    }
    
    @FXML
    public void backToMenu() {
    	SceneManager.switchScene("start-menu.fxml");
    }
    
    public void helpOnClicked() {
        String rules = """
                Minesweeper là một trò chơi giải đố, mục tiêu của bạn là phải tìm ra tất cả các ô không có bom mà không chạm vào bất kỳ ô nào có bom.

                Cách chơi:
                - Bấm vào một ô để mở nó. Nếu ô đó có bom, trò chơi kết thúc.
                - Nếu ô không có bom, số trên ô hiển thị số lượng bom xung quanh.
                - Nếu bạn nghi ngờ một ô có bom, bấm chuột phải để đặt một lá cờ.
                - Khi bạn đã mở tất cả các ô không có bom, bạn thắng.

                Chúc may mắn!""";
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Minesweeper - Luật chơi");
        alert.setHeaderText(null);
        alert.setContentText(rules);
        alert.showAndWait();
    }
    
    
    public void stopMedia() {
    	getGameModel().stopMedia();
    }
    
    public void continueMedia() {
    	getGameModel().continueMedia();
    }
}


