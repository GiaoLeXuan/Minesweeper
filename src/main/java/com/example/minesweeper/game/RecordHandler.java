package com.example.minesweeper.game;

import com.example.minesweeper.scene.EasyGameModel;
import com.example.minesweeper.scene.GameModel;
import com.example.minesweeper.scene.HardGameModel;
import com.example.minesweeper.scene.MediumGameModel;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecordHandler {
    public static final String pathOfRecordFolder = "src\\main\\resources\\com\\example\\minesweeper\\records\\";

    public void updateRecords(GameModel gameModel, int elapsedTime) {
        String fileName = "";
        switch (gameModel.getBoardHandler().getColumns()) {
            case EasyGameModel.COLUMNS -> fileName = "EasyHighScore.txt";
            case MediumGameModel.COLUMNS -> fileName = "MediumHighScore.txt";
            case HardGameModel.COLUMNS -> fileName = "HardHighScore.txt";
        }
        String filePath = pathOfRecordFolder + fileName;
        progressPlayerPlayedTime(elapsedTime, filePath);
    }

    private void progressPlayerPlayedTime(int playedTime, String filePath) {
        try {
            List<Integer> records = loadRecordsFromFile(filePath);
            records.add(playedTime);
            saveRecordsToFile(getBestOfFiveFrom(records), filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Integer> getBestOfFiveFrom(List<Integer> records) {
        Collections.sort(records);
        if (records.size() > 5) {
            return records.subList(0, 5);
        } else {
            return records;
        }
    }

    public List<Integer> loadRecordsFromFile(String filePath) throws IOException {
        List<Integer> highScores = new ArrayList<>();
        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    int score = Integer.parseInt(line.trim());
                    highScores.add(score);
                }
            }
        }
        return highScores;
    }

    private void saveRecordsToFile(List<Integer> highScores, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int score : highScores) {
                writer.write(String.valueOf(score));
                writer.newLine();
            }
        }
    }
}
