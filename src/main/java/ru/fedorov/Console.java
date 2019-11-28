package ru.fedorov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class Console {

    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        String input = "";
        try {
            input = bis.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    public static int askIdGenre() {
        Integer id = null;
        while (id == null) {
            writeMessage("Выберите id жанра:");
            try {
                id = Integer.parseInt(readString());
            } catch (Exception e) {
                writeMessage("Id не существует.");
            }
        }
        return id;
    }

    public static void printGenres(Map<Integer, String> genres) {
        for (Map.Entry<Integer, String> entry : genres.entrySet())
            writeMessage(String.format("%d = %s", entry.getKey(), entry.getValue()));
    }
}
