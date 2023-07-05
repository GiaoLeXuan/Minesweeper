package com.example.minesweeper.scene;

import com.example.minesweeper.game.Record;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class RecordTableController {
    private final File recordFile;
    @FXML
    private TableView<Record> table;
    @FXML
    private TableColumn<Integer, Record> rankColumn;
    @FXML
    private TableColumn<String, Record> timeCompleted;
    @FXML
    private ObservableList<Record> recordList;

    protected RecordTableController(String recordFilePath) {
        recordFile = new File(recordFilePath);
    }

    public void returnToMenu() {
        SceneManager.switchScene("start-menu.fxml");
    }

    public void initialize() {
        BufferedReader reader = null;
        try {
            reader = Files.newBufferedReader(recordFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] highScore = {"", "", "", "", "", ""};

        for (int i = 0; i < 5; i++) {
            try {
                assert reader != null;
                highScore[i] = reader.readLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        recordList = FXCollections.observableArrayList(
                new Record(1, highScore[0]),
                new Record(2, highScore[1]),
                new Record(3, highScore[2]),
                new Record(4, highScore[3]),
                new Record(5, highScore[4])
        );
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        timeCompleted.setCellValueFactory(new PropertyValueFactory<>("time"));
        table.setItems(recordList);
    }
}
