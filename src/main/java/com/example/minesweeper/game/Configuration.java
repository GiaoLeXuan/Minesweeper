package com.example.minesweeper.game;

import com.example.minesweeper.media.AudioManager;

import java.io.*;

public class Configuration {

    private static final String configFileName = "config.txt";
    public static void loadConfiguration() {
        File file = new File(configFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(file.getName()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            AudioManager.setMute(
                    Boolean.parseBoolean(bufferedReader.readLine()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveConfiguration() {
        File file = new File(configFileName);
        file.setWritable(true);
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(configFileName))) {
            bufferedWriter.write(String.valueOf(AudioManager.isMute()));
            bufferedWriter.newLine();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        file.setReadOnly();
    }
}
