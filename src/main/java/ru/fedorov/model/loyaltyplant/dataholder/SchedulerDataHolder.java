package ru.fedorov.model.loyaltyplant.dataholder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.fedorov.entity.Film;
import ru.fedorov.entity.Genre;
import ru.fedorov.entity.converter.FilmConverter;
import ru.fedorov.entity.converter.GenreConverter;
import ru.fedorov.repository.FilmsRepository;
import ru.fedorov.repository.GenresRepository;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableScheduling
public class SchedulerDataHolder {

    @Autowired
    private GenresRepository genresRepository;

    @Autowired
    private FilmsRepository filmsRepository;

    @Scheduled(fixedRate = 1000 * 60 * 60)//cron = "0 0 * * * *"
    public void schedulerStart() {
        DataHolder dataHolder = new DataHolderImpl();

        List<Genre> genres = dataHolder.getGenres().stream()
                .map(GenreConverter::convertToGenre).collect(Collectors.toList());
        List<Film> filmsInfo = dataHolder.getFilmsInfo().stream()
                .map(FilmConverter::convertToFilm).collect(Collectors.toList());

        genresRepository.saveAll(genres);
        filmsRepository.saveAll(filmsInfo);
    }

}

