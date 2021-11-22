package com.example.JDBC.JDBChomework.service;

import com.example.JDBC.JDBChomework.DAO.AuthorDAO;
import com.example.JDBC.JDBChomework.DAO.BookDAO;
import com.example.JDBC.JDBChomework.DAO.GenreDAO;
import com.example.JDBC.JDBChomework.models.Author;
import com.example.JDBC.JDBChomework.models.Book;
import com.example.JDBC.JDBChomework.models.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookDAO dao;
    private final GenreDAO genreDAO;
    private final AuthorDAO authorDAO;

    @Transactional
    public List<Book> getAll(){
        return dao.findAll();
    }

    @Transactional
    public void addBook(String name, String author, String genre) {


        Author author1 = authorDAO.findByName(author);
        Genre genre1 = genreDAO.findByName(genre);

        if (genre1 == null){
            genre1 = new Genre(genre);
        }

        if (author1 == null){
            author1 = new Author(author);
        }

        Book book = new Book(name, author1, genre1);
        dao.save(book);
    }

    @Transactional
    public Book findById(long id){
        return dao.findById(id);
    }

    @Transactional
    public List<Book> findByName(String name){return dao.findByName(name);}

    @Transactional
    public List<Book> getBookByAuthorName(String name) {
        return dao.findByAuthorName(name);
    }

    @Transactional
    public List<Book> getBookByGenre(String genre){
        return dao.findПожалуйстаByGenre(genre);
    }

    @Transactional
    public List<Book> getBookEndingWith(String suffix){return dao.findByNameEndingWith(suffix);}
}
