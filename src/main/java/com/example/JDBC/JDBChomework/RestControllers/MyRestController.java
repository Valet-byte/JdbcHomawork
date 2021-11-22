package com.example.JDBC.JDBChomework.RestControllers;

import com.example.JDBC.JDBChomework.models.Book;
import com.example.JDBC.JDBChomework.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyRestController {

    @Autowired
    BookService service;

    @GetMapping("/getBookById/{id}")
    public Book getById(@PathVariable long id){
        return service.findById(id);
    }

    @GetMapping("/addBook/{name}/{authorName}/{genre}")
    public void addBook(@PathVariable("name") String name,
                          @PathVariable("authorName") String authorName,
                          @PathVariable("genre") String genre){
        service.addBook(name, authorName, genre);
    }

    @GetMapping("getByName/{name}")
    public List<Book> getByName(@PathVariable String name){
        return service.findByName(name);
    }

    @GetMapping("/all")
    public List<Book> getAll(){
        return service.getAll();
    }

}
