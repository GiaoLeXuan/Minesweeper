package com.example.minesweeper;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.ResourceBundle;

import com.example.minesweeper.StartMenuController;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MediumHighScoreController implements Initializable{
	
	@FXML
	private TextField highScore;
	
	@FXML
	private Button returnButton;

	@FXML
	private TableView<HighScore> table;
	
	@FXML
	private TableColumn<Integer,HighScore> rankColumn;
	
	@FXML
	private TableColumn<String,HighScore> timeCompleted;
	
	@FXML
	private ObservableList<HighScore> highScoreList;
	
	public void returnToMenu(){
		StartMenuController start = new StartMenuController();
		start.modeOnClicked("start-menu-test.fxml");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		File file = new File("src\\main\\resources\\com\\example\\minesweeper\\records\\MediumHighScore.txt");
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
		highScoreList = FXCollections.observableArrayList(
				new HighScore(1,highScore[0]),
				new HighScore(2,highScore[1]),
				new HighScore(3,highScore[2]),
				new HighScore(4,highScore[3]),
				new HighScore(5,highScore[4])
				);
		rankColumn.setCellValueFactory(new PropertyValueFactory<Integer,HighScore>("rank"));
		timeCompleted.setCellValueFactory(new PropertyValueFactory<String,HighScore>("time"));
		table.setItems(highScoreList);
		
	}
}
