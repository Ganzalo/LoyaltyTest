package ru.fedorov.entity.converter;

import lombok.NonNull;
import ru.fedorov.entity.Genre;
import ru.fedorov.model.dataholder.loyaltyplant.vo.genres.GenreInfo;

public class GenreConverter {

    public static Genre convertToGenre(@NonNull GenreInfo genreInfo) {
        return Genre.builder()
                .id(genreInfo.getId())
                .name(genreInfo.getName())
                .build();
    }

    public static GenreInfo convertToGenreInfo(@NonNull Genre genre) {
        return GenreInfo.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }

}
