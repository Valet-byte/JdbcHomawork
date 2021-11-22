package com.example.JDBC.JDBChomework.DAO;

import com.example.JDBC.JDBChomework.DAO.BookJdbcDAO;
import com.example.JDBC.JDBChomework.models.Author;
import com.example.JDBC.JDBChomework.models.Book;
import com.example.JDBC.JDBChomework.models.Genre;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({BookJdbcDAO.class})
@DisplayName("Тест BookDaoJDBC")
class TestBookJdbcDAO {

    @Autowired
    private BookJdbcDAO dao;

    @Test
    @DisplayName("Проверка добавления и проверка правильности добавления")
    public void test1(){
        Book book = new Book("Harry_Potter", new Author("Roalng"), new Genre("Fantazy"));

        long id1 = dao.insertBook(book);

        Book testBook = dao.findById(id1);

        assertEquals(book.getName(), testBook.getName());
        assertEquals(book.getAuthor().getName(), testBook.getAuthor().getName());
        assertEquals(book.getGenre().getName(), testBook.getGenre().getName());
    }

    @Test
    @DisplayName("Проверка на наличие всех книг")
    public void test2(){

        dao.insertBook(new Book("Harry_Potter", new Author("Roalng"), new Genre("Fantazy")));
        dao.insertBook(new Book("Пока идет снег", new Author("Макс максимов"), new Genre("Fantazy")));

        List<Book> books = dao.findAll();

        assertEquals(books.size(), 2);
    }

    @Test
    @DisplayName("Проверка нахождения по жанру")
    public void test3(){
        Book book = new Book("Harry_Potter", new Author("Roalng"), new Genre("Fantazy"));

        dao.insertBook(book);

        List<Book> books = dao.findПожалуйстаByGenre("Fantazy");

        assertTrue(forTrue(books, book));
    }

    @Test
    @DisplayName("Проверка нахождения по имени автора")
    public void test4(){
        Book book = new Book("Harry_Potter", new Author("Roalng"), new Genre("Fantazy"));

        dao.insertBook(book);

        List<Book> books = dao.findByAuthorName("Roalng");

        assertTrue(forTrue(books, book));
    }

    @Test
    @DisplayName("Проверка нахождения по имени")
    public void test5(){
        Book book = new Book("Harry_Potter", new Author("Roalng"), new Genre("Fantazy"));

        dao.insertBook(book);

        List<Book> books = dao.findByName("Harry_Potter");

        assertTrue(forTrue(books, book));
    }


    @Test
    @DisplayName("Обновление имени книги")
    public void test6(){
        Book book = new Book("Harry_Potter", new Author("Roalng"), new Genre("Fantazy"));

        long id = dao.insertBook(book);

        Book book1 = dao.updateNameBookById("Harry_Potter", id);

        assertEquals(id, book1.getID());
    }

    private boolean forTrue (List<Book> books, Book book){
        for (Book bookL : books) {
            if (bookL.getName().equals(book.getName())) return true;
        }
        return false;
    }
}