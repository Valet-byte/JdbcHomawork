package com.example.JDBC.JDBChomework.DAO;

import com.example.JDBC.JDBChomework.models.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreDAO extends
        CrudRepository<Genre, Long> {
    //Genre findByGenre (String genre);
    Genre findByName (String name);
}