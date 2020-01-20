package ru.fedorov.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.fedorov.entity.AVGenre;
import ru.fedorov.entity.Film;
import ru.fedorov.entity.Genre;
import ru.fedorov.entity.converter.FilmConverter;
import ru.fedorov.model.loyaltyplant.Calculator;
import ru.fedorov.model.loyaltyplant.dataholder.DataHolder;
import ru.fedorov.repository.AverageVotesRepository;
import ru.fedorov.repository.FilmsRepository;
import ru.fedorov.repository.GenresRepository;
import ru.fedorov.ui.fronttest.Message;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

//todo Bad practice to use only one controller for different types of endpoints: split it to three: each one for each repository
@RestController
public class Controller {

    @Autowired
    GenresRepository genresRepository;

    @Autowired
    FilmsRepository filmsRepository;

    @Autowired
    AverageVotesRepository averageVotesRepository;

    private DataHolder dataHolder = new DataHolder();

    @GetMapping("/fill/genre")
    public String fillGenre() {
        genresRepository.saveAll(dataHolder.getGenres().entrySet().stream()
                .map(o -> new Genre(o.getKey(), o.getValue())).collect(Collectors.toList()));
        return "Genres are created";
    }

    @GetMapping("/fill/film")
    public String fillFilm() {
        filmsRepository.saveAll(dataHolder.getFilmsInfo(20).stream()
                .map(FilmConverter::convertToFilm).collect(Collectors.toList()));
        return "Films are created";
    }

    @GetMapping("/fill/all")
    public String fillAll() {
        fillGenre();
        fillFilm();

        return "All are created";
    }

    @RequestMapping("/averageVote/{id}")
    public String getAverageVote(@PathVariable int id) {
        if (!genresRepository.existsById(id))
            return "No such genre this id";

        AVGenre avGenre = averageVotesRepository.findById(id);
        if (avGenre == null) {
            float averageVote = new Calculator(id).calculateAverageVote(filmsRepository.findAll().stream()
                    .map(FilmConverter::convertToFilmInfo).collect(Collectors.toList()));
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
            avGenre = new AVGenre(id, averageVote, timestamp);
            averageVotesRepository.save(avGenre);
        }

        String genreName = genresRepository.findById(id).getName();
        return String.format("Average vote %s = %.2f actual for current = ",
                genreName, avGenre.getAverageVote()) + avGenre.getTimestamp().toLocalDateTime()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @RequestMapping("/show")
    public String getGenres() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Genre genre : genresRepository.findAll())
            stringBuilder.append(genre.getId()).append("\t").append(genre.getName()).append("<br/>");

        stringBuilder.append("<br/>").append("<br/>");

        for (Film film : filmsRepository.findAll())
            stringBuilder.append(film).append("<br/>");
        return stringBuilder.toString();
    }

    @RequestMapping("/hello")
    public Message greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Message(name);
    }

}
