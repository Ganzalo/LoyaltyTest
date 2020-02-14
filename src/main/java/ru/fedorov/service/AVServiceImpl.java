package ru.fedorov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fedorov.entity.AVGenre;
import ru.fedorov.entity.Film;
import ru.fedorov.entity.Genre;
import ru.fedorov.model.calculator.Calculator;
import ru.fedorov.repository.AverageVotesRepository;
import ru.fedorov.repository.FilmsRepository;
import ru.fedorov.repository.GenresRepository;
import ru.fedorov.service.frontend.model.AVGenreModel;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AVServiceImpl implements AVService {

    @Autowired
    private GenresRepository genresRepository;

    @Autowired
    private FilmsRepository filmsRepository;

    @Autowired
    private AverageVotesRepository averageVotesRepository;

    @Override
    public AVGenreModel getAverageVote(int id) {
        if (!genresRepository.existsById(id))
            return new AVGenreModel();

        if (!averageVotesRepository.existsById(id)) {
            calculateAndSaveAV(id);
        }

        AVGenre avGenre = averageVotesRepository.findById(id).orElse(new AVGenre());
        Genre genre = genresRepository.findById(id).orElse(new Genre());

        return AVGenreModel.builder().id(avGenre.getId()).nameGenre(genre.getName())
                .averageVote(avGenre.getAverageVote()).timestamp(avGenre.getTimestamp()).build();
    }

    @Override
    public List<AVGenreModel> getAverageVotes() {
        List<AVGenreModel> avGenres = new ArrayList<>();
        for (Genre genre  : genresRepository.findAll())
            avGenres.add(getAverageVote(genre.getId()));

        return avGenres;
    }

    @Override
    public void calculateAverageVote(int id) {
        calculateAndSaveAV(id);
    }

    @Override
    public void calculateAverageVotes() {
        for (Genre genre  : genresRepository.findAll())
            calculateAndSaveAV(genre.getId());
    }

    private void calculateAndSaveAV(int id) {
        List<Film> films = filmsRepository.getFilmsByGenreIds(id);
        float averageVote = new Calculator().calculateAverageVote(films);
        averageVotesRepository.save(new AVGenre(id, averageVote, Timestamp.valueOf(LocalDateTime.now())));
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

}
