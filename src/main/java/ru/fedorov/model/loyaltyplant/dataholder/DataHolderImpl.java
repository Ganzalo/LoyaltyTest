package ru.fedorov.model.loyaltyplant.dataholder;

import ru.fedorov.model.loyaltyplant.vo.filmsinfo.FilmInfo;
import ru.fedorov.model.loyaltyplant.vo.genres.GenreInfo;

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
