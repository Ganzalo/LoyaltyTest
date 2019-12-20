package ru.fedorov.repository;

import org.springframework.data.repository.CrudRepository;
import ru.fedorov.entity.Film;

import java.util.List;

public interface FilmsRepository extends CrudRepository<Film, Long> {

    List<Film> findAll();

}
