package ru.fedorov;

import ru.fedorov.model.Genre;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ClientController {

    private final String STOP = "stop";

    private Scanner scanner;
    private Map<Integer, String> genres = null;

    private AverageVoteCalculator counterAverageVote;

    ClientController() { }

    public void start() {
        ConnectionHolder connectionHolder = new ConnectionHolder();
        System.out.println("Привет! Это программа для получение средний оценки за жанр!");
        System.out.println("Выберите id жанра:");
        try {
            genres = connectionHolder.getListGenre().stream()
                    .collect(Collectors.toMap(Genre::getId, Genre::getName));
        } catch (IOException e) {
            System.out.println("Ошибка получения списка жанров.");
            e.printStackTrace();
        }
       if(genres != null) {
           for (Map.Entry<Integer, String> entry : genres.entrySet())
               System.out.println(String.format("%d = %s", entry.getKey(), entry.getValue()));
           waitResponse();
       } else {
           System.out.println("Список пуст.");
       }
    }

    private void waitResponse() {
        scanner = new Scanner(System.in);
        int id;
        while (true) {
            try {
                id = scanner.nextInt();
                if(checkId(id))
                    break;
                else
                    throw new IllegalArgumentException();
            } catch (InputMismatchException e) {
                System.out.println("Нужно ввести id!");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Id не существует");
                scanner.nextLine();
            }
        }
        countingAverageVoteHolder(id);
    }

    private void countingAverageVoteHolder(int id) {
        counterAverageVote = new AverageVoteCalculator(id);
        counterAverageVote.getAverageVote();

        System.out.println("конец");
    }

    private boolean checkId(int id) {
         return genres.getOrDefault(id, null) != null;
    }
}
