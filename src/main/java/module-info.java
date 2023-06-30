module com.example.minesweeper {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.example.minesweeper to javafx.fxml;
    exports com.example.minesweeper;
    exports com.example.minesweeper.media;
    opens com.example.minesweeper.media to javafx.fxml;
    exports com.example.minesweeper.scene;
    opens com.example.minesweeper.scene to javafx.fxml;
    exports com.example.minesweeper.game;
    opens com.example.minesweeper.game to javafx.fxml;
}