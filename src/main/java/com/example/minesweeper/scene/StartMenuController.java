package com.example.minesweeper.scene;

import com.example.minesweeper.media.Audio;
import com.example.minesweeper.media.AudioManager;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class StartMenuController {

    public void initialize() {
        AudioManager.playMedia(Audio.MAIN_THEME);
    }

    public void easyModeOnClicked() {
        SceneManager.switchScene("easy-mode.fxml");
    }

    public void mediumModeOnClicked() {
        SceneManager.switchScene("medium-mode.fxml");
    }

    public void hardModeOnClicked() {
        SceneManager.switchScene("hard-mode.fxml");
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

    public void aboutOnClicked() {
        String about = """
                MINESWEEPER - Bài tập lớn học phần Lập trình hướng đối tượng IT3100
                			
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
}
