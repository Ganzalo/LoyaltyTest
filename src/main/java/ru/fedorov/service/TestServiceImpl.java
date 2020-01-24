package ru.fedorov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fedorov.entity.Film;
import ru.fedorov.entity.Genre;
import ru.fedorov.entity.converter.FilmConverter;
import ru.fedorov.model.loyaltyplant.dataholder.DataHolder;
import ru.fedorov.model.loyaltyplant.vo.filmsinfo.FilmInfo;
import ru.fedorov.repository.FilmsRepository;
import ru.fedorov.repository.GenresRepository;
import ru.fedorov.ui.fronttest.Message;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    @Autowired
    private GenresRepository genresRepository;

    @Autowired
    private FilmsRepository filmsRepository;

    private DataHolder dataHolder = new DataHolder();

    private static final int PAGE_STEP = 20;

    @Override
    public boolean fillGenre() {
        Map<Integer, String> genres = dataHolder.getGenres();
        genresRepository.saveAll(genres.entrySet().stream()
                .map(o -> new Genre(o.getKey(), o.getValue())).collect(Collectors.toList()));
        return !genres.isEmpty();
    }

    @Override
    public boolean fillFilm() {
        List<FilmInfo> filmsInfo = dataHolder.getFilmsInfo(PAGE_STEP);
        filmsRepository.saveAll(filmsInfo.stream()
                .map(FilmConverter::convertToFilm).collect(Collectors.toList()));
        return !filmsInfo.isEmpty();
    }

    @Override
    public boolean fillAll() {
        return fillGenre() && fillFilm();
    }

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
