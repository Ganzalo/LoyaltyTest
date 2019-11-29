package ru.fedorov.ui;

import ru.fedorov.util.Console;
import ru.fedorov.model.AverageVoteCalculator;

import java.util.Map;

/**
 * UI это view и controller в одном, решил не заниматься декомпозицией этого класса
 * так как это консольное приложение (по сути это упрощенный MVC)
 */
public class UI {

    private static final String STOP_WORDS = "stop";
    private static final String PROGRESS_WORDS = "progress";

    private Map<Integer, String> genres;
    private AverageVoteCalculator calculator;

    public UI(AverageVoteCalculator calculator) {
        this.calculator = calculator;
        start();
    }

    /**
     * Старт работы приложения (вывод список жанров на экран)
     */
    public void start() {
        Console.writeMessage("Привет! Это программа для получения средней оценки за жанр!");
        genres = calculator.getGenres();
        Console.printGenres(genres);
        if (genres.size() != 0) {
            clientController();
        } else {
            Console.writeMessage("Список пуст.");
        }
    }

    /**
     * Метод для запроса id жанр для подсчета и взаимодействия с клиентом через
     * команды STOP_WORDS и STOP_WORDS вплоть до получения результата
     */
    private void clientController() {
        int id = getIdFromClient();
        calculator.calculate(id);
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


    /**
     * Метод запрашивает у клиента id жанр до тех пор пока он не будет валидным
     *
     * @return int id жанра
     */
    private int getIdFromClient() {
        int id;
        while (true) {
            id = Console.askIdGenre();
            if (genres.getOrDefault(id, null) != null)
                break;
            else
                Console.writeMessage("Id не существует.");
        }
        return id;
    }
}
