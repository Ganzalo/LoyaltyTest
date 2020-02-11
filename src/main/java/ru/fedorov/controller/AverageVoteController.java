package ru.fedorov.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.fedorov.service.frontend.model.AVGenreModel;
import ru.fedorov.service.AVService;

import java.util.List;

@RestController
//@RequestMapping(path = "AV")
@RequiredArgsConstructor
public class AverageVoteController {

    private final AVService avService;
    private Logger logger = LoggerFactory.getLogger("controllers");

    @RequestMapping("/averageVote/{id}")
    public AVGenreModel getAverageVote(@PathVariable int id) {
        logger.info("Запрос на получения средний оценки за жанр id = " + id);
        return avService.getAverageVote(id);
    }

    @RequestMapping("/averageVotes")
    public List<AVGenreModel> getAverageVotes() {
        logger.info("Получения средний оценки за жанры");
        return avService.getAverageVotes();
    }

    @RequestMapping("/calculate/{id}")
    public void calculateAverageVote(@PathVariable int id) {
        logger.info("Запрос на подсчет средний оценки за жанр id = " + id);
        avService.calculateAverageVote(id);
    }

    @RequestMapping("/calculates")
    public void calculateAverageVotes() {
        logger.info("Подсчет средний оценки за жанры");
        avService.calculateAverageVotes();
    }

}
