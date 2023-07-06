package com.example.minesweeper.game;

import com.example.minesweeper.media.AudioManager;

import java.io.*;

public class Configuration {
    public static void loadConfiguration() {
        File file = new File("config.txt");
        try {
            if (file.createNewFile()) {
                System.out.println("File created");
            }
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
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter("config" + ".txt"))) {
            bufferedWriter.write(String.valueOf(AudioManager.isMute()));
            bufferedWriter.newLine();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
