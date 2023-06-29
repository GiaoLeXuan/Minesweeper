package com.example.minesweeper;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HardHighScoreController implements Initializable{
	
	@FXML
	private Button returnButton;

	@FXML
	private TableView<Record> table;
	
	@FXML
	private TableColumn<Integer, Record> rankColumn;
	
	@FXML
	private TableColumn<String, Record> timeCompleted;
	
	@FXML
	private ObservableList<Record> recordList;
	
	public void returnToMenu(){
		StartMenuController start = new StartMenuController();
		start.modeOnClicked("start-menu.fxml");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		File file = new File("src\\main\\resources\\com\\example\\minesweeper\\records\\HardHighScore.txt");
		BufferedReader reader = null;
		try {
			reader = Files.newBufferedReader(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] highScore = {"","","","","",""};
		
		for (int i=0;i<5;i++) {
			try {
				highScore[i] = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		recordList = FXCollections.observableArrayList(
				new Record(1,highScore[0]),
				new Record(2,highScore[1]),
				new Record(3,highScore[2]),
				new Record(4,highScore[3]),
				new Record(5,highScore[4])
				);
		rankColumn.setCellValueFactory(new PropertyValueFactory<Integer, Record>("rank"));
		timeCompleted.setCellValueFactory(new PropertyValueFactory<String, Record>("time"));
		table.setItems(recordList);
		
	}
}
