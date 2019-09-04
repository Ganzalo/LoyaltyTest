package ru.fedorov.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class Genres implements Serializable {
    private List<Genre> genres;


}
