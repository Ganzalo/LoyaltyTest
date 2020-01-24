package ru.fedorov.repository;

import org.springframework.data.repository.CrudRepository;
import ru.fedorov.entity.Film;

public interface FilmsRepository extends CrudRepository<Film, Long> {

}
