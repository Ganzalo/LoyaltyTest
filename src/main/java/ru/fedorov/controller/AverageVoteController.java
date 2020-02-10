package ru.fedorov.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.fedorov.service.ui.AVGenreModel;
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

}
