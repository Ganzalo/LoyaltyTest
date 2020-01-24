package ru.fedorov.repository;

import org.springframework.data.repository.CrudRepository;
import ru.fedorov.entity.AVGenre;

public interface AverageVotesRepository extends CrudRepository<AVGenre, Integer> {

}
