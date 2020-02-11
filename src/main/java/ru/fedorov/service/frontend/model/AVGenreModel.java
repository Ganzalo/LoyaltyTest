package ru.fedorov.service.frontend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AVGenreModel {

    private int id;

    private String nameGenre;

    private float averageVote;

    private Timestamp timestamp;

}
