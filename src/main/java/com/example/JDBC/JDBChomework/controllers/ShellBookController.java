package com.example.JDBC.JDBChomework.controllers;

import com.example.JDBC.JDBChomework.models.Book;
import com.example.JDBC.JDBChomework.service.BookService;
import com.example.JDBC.JDBChomework.service.BookServiceJDBC;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellBookController {

    private final BookService service;

    @ShellMethod(value = "get all books", key = {"all", "get_all"})
    public List<Book> getAll(){
        return service.getAll();
    }

    @ShellMethod(value = "put new book", key = {"put", "add", "add_book"})
    public void addBook(
        @ShellOption(value = {"name"})   String name,
        @ShellOption(value = {"author"}) String author,
        @ShellOption(value = {"genre"})  String genre
    ){
        service.addBook(name, author, genre);
    }

    @ShellMethod(value = "get books by author name", key = {"get_by_author", "author"})
    public List<Book> getBookByAuthorName(
           @ShellOption(value = {"name_author"}) String name ){
        return service.getBookByAuthorName(name);
    }

    @ShellMethod(value = "get books by genre", key = {"genre"})
    public List<Book> getBookByGenreName(
            @ShellOption(value = {"genre"}) String genre
    ){
        return service.getBookByGenre(genre);
    }
}
