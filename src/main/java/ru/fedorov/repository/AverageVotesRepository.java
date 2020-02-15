package ru.fedorov.repository;

import org.springframework.data.repository.CrudRepository;
import ru.fedorov.entity.AverageVote;

public interface AverageVotesRepository extends CrudRepository<AverageVote, Integer> {

}
