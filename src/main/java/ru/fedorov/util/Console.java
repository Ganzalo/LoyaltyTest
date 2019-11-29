package ru.fedorov.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Класс для работы с консолью (по сути Utility класс)
 */
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

    /**
     * Запрос id жанра
     *
     * @return int id жанра
     */
    public static int askIdGenre() {
        Integer id = null;
        while (id == null) {
            writeMessage("Выберите id жанра:");
            try {
                id = Integer.parseInt(readString());
            } catch (Exception e) {
                writeMessage("Задайте id жанра числом.");
            }
        }
        return id;
    }

    /**
     * Печатает список жанров по заданному формату
     */
    public static void printGenres(Map<Integer, String> genres) {
        for (Map.Entry<Integer, String> entry : genres.entrySet())
            writeMessage(String.format("%d = %s", entry.getKey(), entry.getValue()));
    }
}
