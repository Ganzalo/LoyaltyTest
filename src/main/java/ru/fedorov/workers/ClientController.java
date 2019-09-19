package ru.fedorov.workers;

import java.util.Map;
import java.util.Scanner;

import static ru.fedorov.workers.GenresHolder.GENRES;

public class ClientController {

    public void start() {
        System.out.println("Привет! Это программа для получения средней оценки за жанр!");
        System.out.println("Выберите id жанра:");

        if (GENRES.size() != 0) {
            for (Map.Entry<Integer, String> entry : GENRES.entrySet())
                System.out.println(String.format("%d = %s", entry.getKey(), entry.getValue()));

            getClientResponse();
        } else {
            System.out.println("Список пуст.");
        }
    }

    private void getClientResponse() {
        Scanner scanner = new Scanner(System.in);
        int id = -1;
        while (true) {
            try {
                id = scanner.nextInt();
                if(checkId(id)) {
                    break;
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Id не существует.");
                scanner.nextLine();
            }
        }
        clientHandler(id);
    }

    private void clientHandler(int id) {
        VoteAverageCalculator counterAverageVote = new VoteAverageCalculator(id);
        System.out.println(counterAverageVote.voteAverage());
        System.out.println("Конец");
    }

    private boolean checkId(int id) {
        return GENRES.getOrDefault(id, null) != null;
    }
}
