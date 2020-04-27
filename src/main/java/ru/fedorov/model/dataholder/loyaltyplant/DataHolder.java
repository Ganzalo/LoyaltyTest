package ru.fedorov.model.dataholder.loyaltyplant;

import ru.fedorov.model.dataholder.loyaltyplant.vo.filmsinfo.FilmInfo;
import ru.fedorov.model.dataholder.loyaltyplant.vo.genres.GenreInfo;

import java.util.List;

interface DataHolder {

    List<GenreInfo> getGenres();

    List<FilmInfo> getFilmsInfo();

}
