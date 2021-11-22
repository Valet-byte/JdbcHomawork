package com.example.JDBC.JDBChomework.DAO;

import com.example.JDBC.JDBChomework.models.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorDAO extends
        CrudRepository<Author, Long> {
    Author findByName (String name);
}
