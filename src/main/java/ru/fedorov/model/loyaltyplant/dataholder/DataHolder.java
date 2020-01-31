package ru.fedorov.model.loyaltyplant.dataholder;

import ru.fedorov.model.loyaltyplant.vo.filmsinfo.FilmInfo;
import ru.fedorov.model.loyaltyplant.vo.genres.GenreInfo;

import java.util.List;

interface DataHolder {

    List<GenreInfo> getGenres();

    List<FilmInfo> getFilmsInfo();

}
