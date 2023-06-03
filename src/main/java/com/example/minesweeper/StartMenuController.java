package com.example.minesweeper;

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
}
