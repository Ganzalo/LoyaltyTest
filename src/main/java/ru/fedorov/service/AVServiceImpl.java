package ru.fedorov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fedorov.entity.AverageVote;
import ru.fedorov.entity.Film;
import ru.fedorov.entity.Genre;
import ru.fedorov.model.calculator.Calculator;
import ru.fedorov.repository.AverageVotesRepository;
import ru.fedorov.repository.FilmsRepository;
import ru.fedorov.repository.GenresRepository;
import ru.fedorov.service.frontend.model.AverageVoteModel;

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

    private final Calculator calculator;

    @Override
    public AverageVoteModel getAverageVote(int id) {
        if (!genresRepository.existsById(id))
            return new AverageVoteModel();

        if (!averageVotesRepository.existsById(id)) {
            calculateAndSaveAV(id);
        }

        AverageVote averageVote = averageVotesRepository.findById(id).orElse(new AverageVote());
        Genre genre = genresRepository.findById(id).orElse(new Genre());

        return AverageVoteModel.builder().id(averageVote.getId()).name(genre.getName())
                .averageVote(averageVote.getAverageVote()).timestamp(averageVote.getTimestamp()).build();
    }

    @Override
    public List<AverageVoteModel> getAverageVotes() {
        List<AverageVoteModel> avGenres = new ArrayList<>();
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

    protected void calculateAndSaveAV(int id) {
        List<Film> films = filmsRepository.getFilmsByGenreIds(id);
        float averageVote = calculator.calculateAverageVote(films);
        averageVotesRepository.save(new AverageVote(id, averageVote, Timestamp.valueOf(LocalDateTime.now())));
    }

    @Override
    @Deprecated
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
