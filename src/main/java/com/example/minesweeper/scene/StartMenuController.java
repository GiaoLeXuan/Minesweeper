package com.example.minesweeper.scene;

import com.example.minesweeper.media.Audio;
import com.example.minesweeper.media.AudioManager;
import com.example.minesweeper.media.ImageHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class StartMenuController {

    private boolean isMuted = false;

    @FXML
    Button soundButton;
    @FXML
    public void initialize() {
        AudioManager.playAudio(Audio.MAIN_THEME);
    }

    public void easyModeOnClicked() {
        modeOnClicked("easy-mode.fxml");
    }

    public void mediumModeOnClicked() {
        modeOnClicked("medium-mode.fxml");
    }

    public void hardModeOnClicked() {
        modeOnClicked("hard-mode.fxml");
    }
    
    public void easyHighScoreOnClicked() {
    	SceneManager.switchScene("easy-high-score.fxml");
    }
    
    public void mediumHighScoreOnClicked() {
        SceneManager.switchScene("medium-high-score.fxml");
    }
    
    public void hardHighScoreOnClicked() {
        SceneManager.switchScene("hard-high-score.fxml");
    }
    
    public void modeOnClicked(String fxmlFileName) {
        SceneManager.switchScene(fxmlFileName);
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
        alert.setContentText(rules);
        alert.setHeight(500);
        alert.showAndWait();
    }
    
    public void aboutOnClicked() {
    	String about = """
                MINESWEEPER
                Bài tập lớn học phần Lập trình hướng đối tượng IT3100
    			
                2023 - Kì 2022.2
    			
                Được thực hiện bởi:
                Lê Xuân Giao - 20210290
                Nguyễn Hữu Đức - 20215353
                Lương Thanh Tùng - 20215508
                Ngô Văn Tân - 20210769
                """;
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Minesweeper");
        alert.setHeaderText(null);
        alert.setContentText(about);
        alert.showAndWait();
    }

    public void speakerOnClicked() {
        if (!isMuted) {
            isMuted = true;
            soundButton.setGraphic(new ImageView(ImageHandler.getImagePath("muted_speaker.png")));
            AudioManager.pauseMediaPlayer();
        } else {
            isMuted = false;
            soundButton.setGraphic(new ImageView(ImageHandler.getImagePath("speaker.png")));
            AudioManager.continueMediaPlayer();
        }
    }


}