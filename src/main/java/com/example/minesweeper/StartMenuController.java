package com.example.minesweeper;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class StartMenuController {

    public void easyModeOnClicked() {
        modeOnClicked("easy-mode.fxml");
    }

    public void mediumModeOnClicked() {
        modeOnClicked("medium-mode.fxml");
    }

    public void hardModeOnClicked() {
        modeOnClicked("hard-mode.fxml");
    }

    private void modeOnClicked(String fxmlFileName) {
        SceneManager.switchScene(fxmlFileName);
        GameController mediumGameController =
                SceneManager.getFxmlLoader().getController();
        mediumGameController.getGameModel().start();
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
}
