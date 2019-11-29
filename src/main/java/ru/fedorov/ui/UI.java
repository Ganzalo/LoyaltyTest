package ru.fedorov.ui;

import ru.fedorov.Console;
import ru.fedorov.model.AverageVoteCalculator;
import ru.fedorov.model.LPCalculatorImpl;

import java.util.Map;

public class UI {

    private static final String STOP_WORDS = "stop";
    private static final String PROGRESS_WORDS = "progress";

    private Map<Integer, String> genres;
    private AverageVoteCalculator calculator = new LPCalculatorImpl();

    public void start() {
        Console.writeMessage("Привет! Это программа для получения средней оценки за жанр!");
        genres = calculator.getGenres();
        Console.printGenres(genres);
        if (genres.size() != 0) {
            clientHandler();
        } else {
            Console.writeMessage("Список пуст.");
        }
    }

    private void clientHandler() {
        int id = getClientResponse();
        calculator.start(id);
        Console.writeMessage("Напишите " + STOP_WORDS + " - чтобы завершить обработку данных и получить результат!");
        Console.writeMessage("Напишите " + PROGRESS_WORDS + " - чтобы получить текущий прогресс обработанных данных!");

        while (calculator.isStop()) {
            String clientRequest = Console.readString();

            if (clientRequest.equalsIgnoreCase(PROGRESS_WORDS))
                Console.writeMessage("Обработано данных " + calculator.getProgress() + "%");

            if (clientRequest.equalsIgnoreCase(STOP_WORDS))
                calculator.stop();
        }

        float result = 0.0f;
        try {
            result = calculator.getResult();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Console.writeMessage("Обработано данных " + calculator.getProgress() + "%");
        Console.writeMessage("Обработка завершена! Значение средней оценки за жанр " + genres.get(id) +
                " = " + result);
        Console.writeMessage("Конец");
    }

    private int getClientResponse() {
        int id;
        while (true) {
            id = Console.askIdGenre();
            if (genres.getOrDefault(id, null) != null)
                break;
        }
        return id;
    }
}
