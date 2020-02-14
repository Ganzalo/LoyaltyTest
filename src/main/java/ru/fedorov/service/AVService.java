package ru.fedorov.service;

import ru.fedorov.service.frontend.model.AVGenreModel;

import java.util.List;

public interface AVService {

    AVGenreModel getAverageVote(int id);

    List<AVGenreModel> getAverageVotes();

    void calculateAverageVote(int id);

    void calculateAverageVotes();

    @Deprecated
    String show();
}
