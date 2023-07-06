package com.example.minesweeper.game;

public enum RecordFile {
    EASY_RECORD("easy_record.txt"),
    MEDIUM_RECORD("medium_record.txt"),
    HARD_RECORD("hard_record.txt");

    RecordFile(String fileName) {
        this.fileName = fileName;
    }

    private static final String folderPath = "records/";
    private final String fileName;
    public String getFilePath() {
        return folderPath + fileName;
    }
}
