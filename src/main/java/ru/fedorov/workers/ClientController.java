package ru.fedorov.workers;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import static ru.fedorov.workers.GenresHolder.GENRES;

public class ClientController {

    private Scanner scanner;

    private static final String STOP_WORDS = "stop";
    private static final String PROGRESS_WORDS = "progress";

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
        scanner = new Scanner(System.in);
        int id;
        while (true) {
            try {
                id = scanner.nextInt();
                if (checkId(id)) {
                    break;
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println("Id не существует.");
                scanner.nextLine();
            }
        }
        clientHandler(id);
    }

    private void clientHandler(int id) {
        CalculatorThread calculatorThread = new CalculatorThread(new AverageVoteCalculator(id));
        calculatorThread.start();
        System.out.println("Напишите " + STOP_WORDS + " - чтобы завершить обработку данных и получить результат или");
        System.out.println("напишите " + PROGRESS_WORDS + " - чтобы получить текущий прогресс обработанных данных!");

        String clientRequest;
        while (!calculatorThread.isInterrupted() && !calculatorThread.isStopCalculate()) {
            clientRequest = scanner.nextLine();

            if (clientRequest.equalsIgnoreCase(PROGRESS_WORDS)) {
                System.out.println("Обработано данных " + calculatorThread.getCurrProgress() + "%");
            }

            if (clientRequest.equalsIgnoreCase(STOP_WORDS)) {
                calculatorThread.interrupt();
            }
        }

        try {
            calculatorThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Обработано данных " + calculatorThread.getCurrProgress() + "%");

        System.out.println("Обработка завершена! Значение средней оценки за жанр " + GENRES.get(id) +
                " = " + calculatorThread.getResult());
        System.out.println("Конец");
    }

    private boolean checkId(int id) {
        return GENRES.getOrDefault(id, null) != null;
    }
}
