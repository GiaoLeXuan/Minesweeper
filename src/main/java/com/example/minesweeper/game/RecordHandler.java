package com.example.minesweeper.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecordHandler {


    public String getRecordFileName() {
        return recordFileName;
    }

    private final String recordFileName;
    public RecordHandler(String recordFileName) {
        this.recordFileName = recordFileName;
    }

    public void updateRecords(int elapsedTime) {
        processPlayerPlayedTime(elapsedTime, recordFileName);
    }

    private void processPlayerPlayedTime(int playedTime, String filePath) {
        try {
            List<Integer> records = loadRecordsFromFile(filePath);
            records.add(playedTime);
            saveRecordsToFile(getBestOfFiveFrom(records), filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> loadRecordsFromFile(String filePath) throws IOException {
        List<Integer> highScores = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(filePath));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            int score = Integer.parseInt(line.trim());
            highScores.add(score);
        }
        return highScores;
    }

    private List<Integer> getBestOfFiveFrom(List<Integer> records) {
        Collections.sort(records);
        if (records.size() > 5) {
            return records.subList(0, 5);
        } else {
            return records;
        }
    }

    private void saveRecordsToFile(List<Integer> highScores, String filePath) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            for (int score : highScores) {
                fileWriter.write(score + "\n");
            }
        }
    }
}