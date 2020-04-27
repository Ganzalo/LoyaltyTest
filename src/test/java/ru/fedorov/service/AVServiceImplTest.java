package ru.fedorov.service;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.fedorov.entity.AverageVote;
import ru.fedorov.entity.Genre;
import ru.fedorov.model.calculator.Calculator;
import ru.fedorov.repository.AverageVotesRepository;
import ru.fedorov.repository.FilmsRepository;
import ru.fedorov.repository.GenresRepository;
import ru.fedorov.service.frontend.model.AverageVoteModel;

import java.sql.Timestamp;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class AVServiceImplTest {

    @Autowired
    private AVServiceImpl avService;

    @MockBean
    private GenresRepository genresRepository;

    @MockBean
    private FilmsRepository filmsRepository;

    @MockBean
    private AverageVotesRepository averageVotesRepository;

    @MockBean
    private Calculator calculator;

    @Test
    @DisplayName("id not exist")
    void getAverageVote() {
        AverageVoteModel averageVoteModel = new AverageVoteModel();
        int id = 1;
        AverageVoteModel averageVoteModelTest = avService.getAverageVote(id);
        Mockito.verify(genresRepository, Mockito.times(1)).existsById(id);
        Assert.assertEquals(averageVoteModel, averageVoteModelTest);
        Assert.assertEquals(averageVoteModel.getAverageVote(), averageVoteModelTest.getAverageVote(), 0.01);
    }

    @Test
    @DisplayName("id exist")
    void getAverageVote2() {
        int id = 1;
        Genre genre = new Genre(1, "Drama");
        AverageVote averageVote = new AverageVote(1, 6.53f, new Timestamp(1604571001));

        AverageVoteModel averageVoteModel = new AverageVoteModel(averageVote.getId(), genre.getName(), averageVote.getAverageVote(), averageVote.getTimestamp());
        Mockito.doReturn(true).when(genresRepository).existsById(id);
        Mockito.doReturn(Optional.of(averageVote)).when(averageVotesRepository).findById(id);
        Mockito.doReturn(Optional.of(genre)).when(genresRepository).findById(id);

        AverageVoteModel averageVoteModelTest = avService.getAverageVote(id);

        Mockito.verify(genresRepository, Mockito.times(1)).existsById(id);
        Mockito.verify(averageVotesRepository, Mockito.times(1)).existsById(id);
        Mockito.verify(averageVotesRepository, Mockito.times(1)).findById(id);
        Mockito.verify(genresRepository, Mockito.times(1)).findById(id);

        Assert.assertEquals(averageVoteModel, averageVoteModelTest);
        Assert.assertEquals(averageVoteModel.getAverageVote(), averageVoteModelTest.getAverageVote(), 0.01);
}

    @Test
    void calculateAverageVote() {
        int id = 1;
        avService.calculateAverageVote(id);
        Mockito.verify(filmsRepository, Mockito.times(1)).getFilmsByGenreIds(id);
        Mockito.verify(calculator, Mockito.times(1))
                .calculateAverageVote(filmsRepository.getFilmsByGenreIds(id));
       Mockito.verify(averageVotesRepository, Mockito.times(1)).save(Mockito.any(AverageVote.class));
    }
}