package ru.fedorov.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import ru.fedorov.entity.Genre;

import java.util.List;

@Repository
public interface GenresRepository extends CrudRepository<Genre, Integer>{

    //todo u don't need this, findById and findAll already in CrudRepository
    List<Genre> findAll();
    Genre findById(int id);
}