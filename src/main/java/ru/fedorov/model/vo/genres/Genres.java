package ru.fedorov.model.vo.genres;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Genres {

    private List<Genre> genres;

}
