package com.example.JDBC.JDBChomework.DAO;

import com.example.JDBC.JDBChomework.models.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookDAO extends
        CrudRepository<Book, Long> {

    List<Book> findAll();

    Book findById(long id);

    List<Book> findByName(String name);

    @Query("select b from Book b where b.author.name = ?1")
    List<Book> findByAuthorName(String name);

    @Query("select b from Book b where b.genre.name = ?1")
    List<Book> findПожалуйстаByGenre(String genre);

    List<Book> findByNameEndingWith(String suffix);

}