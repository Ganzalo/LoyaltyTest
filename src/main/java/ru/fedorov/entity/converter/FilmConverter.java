package ru.fedorov.entity.converter;

import lombok.NonNull;
import ru.fedorov.entity.Film;
import ru.fedorov.model.dataholder.loyaltyplant.vo.filmsinfo.FilmInfo;

public class FilmConverter {

    public static Film convertToFilm(@NonNull FilmInfo filmInfo) {
        return Film.builder()
                .id(filmInfo.getId())
                .genreIds(filmInfo.getGenreIds())
                .voteCount(filmInfo.getVoteCount())
                .averageVote(filmInfo.getAverageVote())
                .build();
    }

    public static FilmInfo convertToFilmInfo(@NonNull Film film) {
        return FilmInfo.builder()
                .id(film.getId())
                .genreIds(film.getGenreIds())
                .voteCount(film.getVoteCount())
                .averageVote(film.getAverageVote())
                .build();
    }

}
