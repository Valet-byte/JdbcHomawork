package com.example.JDBC.JDBChomework.DAO;

import com.example.JDBC.JDBChomework.models.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(GenreJdbcDAO.class)
@DisplayName("Тест GenreJdbcDAO")
class GenreJdbcDAOTest {

    @Autowired
    private GenreJdbcDAO dao;

    @Test
    @DisplayName("Тест insert и findById")
    public void test1(){

        Genre genre = new Genre("Fantazy");

        long id = dao.insert(genre);

        Genre genre1 = dao.findById(id);

        assertEquals(genre.getName(), genre1.getName());
        assertEquals(id, genre1.getID());

    }

    @Test
    @DisplayName("getAll")
    public void test2(){

        Genre genre = new Genre("Fantazy");
        Genre genre1 = new Genre("Detectiv");
        Genre genre2 = new Genre("genre1");

        dao.insert(genre);
        dao.insert(genre1);
        dao.insert(genre2);

        List<Genre> list = dao.getAll();

        assertEquals(list.size(), 3);
    }

    @Test
    @DisplayName("Поиск по имени")
    public void test3(){

        Genre genre = new Genre("Fantazy");

        long id = dao.insert(genre);

        List<Genre> list = dao.findByName(genre.getName());

        Genre genre1 = list.stream().filter( g -> id == g.getID()).findFirst().orElse(new Genre(-1, ""));

        assertEquals(id, genre1.getID());
        assertEquals(genre.getName(), genre1.getName());

    }
}