package ru.fedorov.model.dataholder.loyaltyplant.vo.filmsinfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class Page {

    private List<FilmInfo> results;

}
