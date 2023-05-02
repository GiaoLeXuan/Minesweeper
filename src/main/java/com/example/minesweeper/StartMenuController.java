package com.example.minesweeper;

public class StartMenuController {

    public void easyModeOnClicked() {
        SceneManager.switchScene("easy-mode.fxml");
        EasyGameController easyGameController =
                SceneManager.getFxmlLoader().getController();
        easyGameController.getEasyGameModel().start();
    }
}
