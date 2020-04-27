package ru.fedorov.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import ru.fedorov.entity.Genre;

@Repository
public interface GenresRepository extends CrudRepository<Genre, Integer> {

}