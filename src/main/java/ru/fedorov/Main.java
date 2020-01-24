package ru.fedorov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * В основе архитектупы проекта лежит упрощенный MVC (https://hsto.org/files/0bd/003/42d/0bd00342d45c4515bd7d09e200a3cf02.png)
 * в пакете Model храниться вся бизнес логика программы,
 * в пакете UI класс который представляет собой совмещенный View и Controller.
 * Такая архитектура была выбрана потому что все взаимодействие с пользователем
 * идет через консоль и в связи с малой функциональностью программы.
 *
 * Также есть пакет util c классом Console который помогает в работе с консолью.
 *
 * Взаимодействие UI с Model идет через интерфейс AverageVoteCalculator.
 * AverageVoteCalculator нужен для возможности подмены текущего калькулятора на другие
 * то есть взаимодействия с model возможно только через класс реализующий AverageVoteCalculator.
 *
 * LPCalculatorImpl реализация AverageVoteCalculator для сайта LoyaltyPlant.
 * LPCalculatorImpl взаимодействует CalculatorThread.
 * CalculatorThread взаимодействует c Calculator.
 *
 * Calculator только подсчитывает Среднию оценку для полученных данных.
 * Задача: производить подсчет в фоновом режиме,
 * Решение: создать новый класс CalculatorThread, так как плохая практика смешивать бизнес логику и потоки
 * (легче проверять работу по отдельности).
 * LPCalculatorImpl через который происходит взаимодействия с классами Model.
 *
 * Calculator необходимо получать данные, поэтому Calculator имеет поле класса DataHolder.
 *
 * Поскольку мы имеем 2 источника данных по разным endpoint, в пакете dataholder были
 * созданны: Constants, FilmsInfoHolder, GenresHolder классы.
 * Constants для хранения endpoint для получения данных для классов FilmsInfoHolder, GenresHolder.
 * GenresHolder вытаскивает данные за жанр, статик класс.
 * FilmsInfoHolder вытаскивает данные о фильмах по страницам.
 * Поскольку желательно иметь одну точку взаимодействия с классами FilmsInfoHolder и GenresHolder был
 * созданн DataHolder. Через DataHolder получаем данные для обработки.
 *
 * Также поскольку FilmsInfoHolder и GenresHolder получают JSON файлы была необходимость парсирить
 * в нужное представление. Нужное представление описанно в vo (value object) пакете для жанра и информации
 * по фильмам.
 *
 * Взаимодействия с классоми из пакета dataholder происходит через класс DataHolder, остальные классы default visible.
 * Классы из пакета vo публичные.
 * Взаимодействие с классоми из пакета model происходит через класс LPCalculatorImpl, остальные классы default visible.
 */

@SpringBootApplication
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Main {
    public static void main(String[] args)  {
        SpringApplication.run(Main.class, args);
    }
}
