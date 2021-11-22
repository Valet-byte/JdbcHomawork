package com.example.JDBC.JDBChomework.service;

import com.example.JDBC.JDBChomework.DAO.BookJdbcDAO;
import com.example.JDBC.JDBChomework.models.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceJDBC {

    private final BookJdbcDAO dao;

    @Transactional
    public List<Book> getAll(){
        return dao.findAll();
    }

    @Transactional
    public long addBook(String name, String author, String genre) {
        return dao.insertBook(name, author, genre);
    }

    @Transactional
    public long addBook(Book book){
        return dao.insertBook(book);
    }

    @Transactional
    public List<Book> getBookByAuthorName(String name) {return dao.findByAuthorName(name);}

    @Transactional
    public List<Book> getBookByGenre(String genre){
        return dao.findПожалуйстаByGenre(genre);
    }

    @Transactional
    public Book getBookById (long id){
        return dao.findById(id);
    }

}
