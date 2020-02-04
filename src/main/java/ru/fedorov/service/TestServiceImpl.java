package ru.fedorov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fedorov.entity.Film;
import ru.fedorov.entity.Genre;
import ru.fedorov.repository.FilmsRepository;
import ru.fedorov.repository.GenresRepository;
import ru.fedorov.service.ui.Message;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    @Autowired
    private GenresRepository genresRepository;

    @Autowired
    private FilmsRepository filmsRepository;

    @Override
    public String show() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Genre genre : genresRepository.findAll())
            stringBuilder.append(genre.getId()).append("\t").append(genre.getName()).append("<br/>");

        stringBuilder.append("<br/>").append("<br/>");

        for (Film film : filmsRepository.findAll())
            stringBuilder.append(film).append("<br/>");
        return stringBuilder.toString();
    }

    @Override
    @Deprecated
    public Message greeting(String name) {
        return new Message(name);
    }
}
