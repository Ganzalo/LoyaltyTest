package ru.fedorov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fedorov.entity.AVGenre;
import ru.fedorov.entity.Film;
import ru.fedorov.entity.Genre;
import ru.fedorov.entity.converter.FilmConverter;
import ru.fedorov.model.loyaltyplant.Calculator;
import ru.fedorov.model.loyaltyplant.dataholder.DataHolder;
import ru.fedorov.model.loyaltyplant.vo.filmsinfo.FilmInfo;
import ru.fedorov.repository.AverageVotesRepository;
import ru.fedorov.repository.FilmsRepository;
import ru.fedorov.repository.GenresRepository;
import ru.fedorov.ui.fronttest.Message;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    public String getAverageVote(int id) {
        if (!genresRepository.existsById(id))
            return "No such genre this id";

        AVGenre avGenre = averageVotesRepository.findById(id).orElse(null);
        if (avGenre == null) {
            float averageVote = new Calculator(id)
                    .calculateAverageVote(StreamSupport.stream(filmsRepository.findAll().spliterator(), false)
                            .map(FilmConverter::convertToFilmInfo).collect(Collectors.toList()));
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
            avGenre = new AVGenre(id, averageVote, timestamp);
            averageVotesRepository.save(avGenre);
        }

        String genreName = genresRepository.findById(id).orElse(new Genre(id, "Неизвестный жанр")).getName();
        return String.format("Average vote %s = %.2f actual for current = ",
                genreName, avGenre.getAverageVote()) + avGenre.getTimestamp().toLocalDateTime()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
