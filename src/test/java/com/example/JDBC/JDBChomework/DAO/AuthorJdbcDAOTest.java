package com.example.JDBC.JDBChomework.DAO;

import com.example.JDBC.JDBChomework.models.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(AuthorJdbcDAO.class)
@DisplayName("Тест AuthorJdbcDAOTest")
class AuthorJdbcDAOTest {

    @Autowired
    private AuthorJdbcDAO dao;

    @Test
    @DisplayName("Поиск по id")
    public void test1(){
        Author author = new Author("Pushkin");

        long id = dao.insert(author);

        Author author1 = dao.findById(id);

        assertEquals(author.getName(), author1.getName());
        assertEquals(id, author1.getID());
    }

    @Test
    @DisplayName("Поиск по имени")
    public void test2(){
        Author author = new Author("Pushkin");

        dao.insert(author);

        List<Author> author1 = dao.findByName(author.getName());

        assertTrue(forTrue(author1, author));
    }

    @Test
    @DisplayName("тест на наличие всех")
    public void test3(){
        Author author = new Author("Pushkin");
        Author author1 = new Author("Lermontov");
        Author author2 = new Author("Dantes");

        dao.insert(author);
        dao.insert(author1);
        dao.insert(author2);

        assertEquals(dao.getAll().size(), 3);
    }


    private boolean forTrue (List<Author> authors, Author... authorsL){
        boolean eq = true;
        for (Author author1 : authors) {
            for (Author author : authorsL) {
                if (!author1.getName().equals(author.getName())) eq = false;
            }
        }
        return eq;
    }

}