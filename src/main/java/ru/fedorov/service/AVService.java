package ru.fedorov.service;

import ru.fedorov.service.ui.AVGenreModel;

import java.util.List;

public interface AVService {

    AVGenreModel getAverageVote(int id);

    List<AVGenreModel> getAverageVotes();
}
