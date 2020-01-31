package ru.fedorov.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.fedorov.entity.Film;

import java.util.List;

public interface FilmsRepository extends CrudRepository<Film, Long> {

   //List<Film> findFilmsByGenreIds(int genreId);
   @Query(
           value = "select * from films where ?1 = any (films.genre_ids)",
           nativeQuery = true)
   List<Film> getFilmsByGenreIds(int genreId);

}
