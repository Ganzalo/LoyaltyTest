package ru.fedorov.service;

import ru.fedorov.service.frontend.model.AverageVoteModel;

import java.util.List;

public interface AVService {

    AverageVoteModel getAverageVote(int id);

    List<AverageVoteModel> getAverageVotes();

    void calculateAverageVote(int id);

    void calculateAverageVotes();

    String show();
}
