package com.example.minesweeper.scene;

import com.example.minesweeper.media.Audio;
import com.example.minesweeper.media.AudioManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioMenuItem;
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
    private RadioMenuItem soundOnMenuItem;

    @FXML
    private RadioMenuItem soundOffMenuItem;

    @FXML
    public void initialize() {
        initializeGameModel();
        initializeSoundMenu();
    }

    private void initializeSoundMenu() {
        if(AudioManager.isMute()) {
            soundOffMenuItem.setSelected(true);
        } else {
            soundOnMenuItem.setSelected(true);
        }
    }

    public abstract void initializeGameModel();

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

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public Text getTimeCounterText() {
        return timeCounterText;
    }

    @FXML
    public void backToStartMenu() {
        SceneManager.switchScene("start-menu.fxml");
    }

    public void helpOnClicked() {
        String rules = """
                Minesweeper là một trò chơi giải đố, mục tiêu của bạn là phải tìm ra tất cả các ô không có bom mà không mở bất kỳ ô nào có bom.
                Cách chơi:
                - Nhấn chuột trái vào một ô để mở. Nếu ô đó có bom, trò chơi kết thúc.
                - Nếu ô không có bom, số hiển thị trong ô là số lượng bom tại các ô xung quanh ô đó.
                - Nếu bạn nghi ngờ một ô có bom, nhấn chuột phải để đặt một lá cờ.
                - Để giành chiến thắng, bạn cần mở tất cả các ô không có bom.
                Chúc may mắn!
                """;

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Minesweeper - Luật chơi");
        alert.setHeaderText(null);
        alert.setHeight(500);
        alert.setContentText(rules);
        alert.showAndWait();
    }


    public void turnAudioOff() {
        AudioManager.setMute(true);
    }

    public void turnAudioOn() {
        AudioManager.setMute(false);
    }

    public TilePane getTilePane() {
        return tilePane;
    }
}

