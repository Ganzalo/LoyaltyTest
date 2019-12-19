package ru.fedorov.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fedorov.entity.Genre;
import ru.fedorov.model.loyaltyplant.dataholder.DataHolder;
import ru.fedorov.repository.GenresRepository;

import java.util.stream.Collectors;

@RestController
public class Controller {

    @Autowired
    GenresRepository repository;

    @GetMapping("/create")
    public String create() {
        repository.saveAll(new DataHolder().getGenres().entrySet()
                .stream().map(o -> new Genre(o.getKey(), o.getValue())).collect(Collectors.toList()));
        return "Genres are created";
    }

    @RequestMapping("/")
    public String getGenres() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Genre genre: repository.findAll())
            stringBuilder.append(genre.getId()).append("\t").append(genre.getName()).append("<br />");
        return stringBuilder.toString();
    }
}
