package ru.fedorov.model.dataholder.loyaltyplant;

import ru.fedorov.model.dataholder.loyaltyplant.vo.filmsinfo.FilmInfo;
import ru.fedorov.model.dataholder.loyaltyplant.vo.genres.GenreInfo;

import java.util.List;

public class DataHolderImpl implements DataHolder {

    private GenresHolder genresHolder = new GenresHolder();
    private FilmsInfoHolder filmsInfoHolder = new FilmsInfoHolder();

    @Override
    public List<GenreInfo> getGenres() {
        return genresHolder.requestGenres();
    }

    @Override
    public List<FilmInfo> getFilmsInfo() {
        return filmsInfoHolder.getFilmsInfo();
    }
}
