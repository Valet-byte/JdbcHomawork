package com.example.JDBC.JDBChomework.service;

import com.example.JDBC.JDBChomework.DAO.AuthorJdbcDAO;
import com.example.JDBC.JDBChomework.models.Author;
import com.example.JDBC.JDBChomework.models.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorServiceJdbc {

    private final AuthorJdbcDAO dao;

    @Transactional
    public Author findById(long id){
        try {
            return dao.findById(id);
        } catch (Exception ignore){
            return new Author(-1, "");
        }
    }

    @Transactional
    public List<Author> findByName(String name){
        try {
            return dao.findByName(name);
        } catch (Exception ignore){
            List<Author> list = new ArrayList<>(1);
            list.add(new Author(-1, ""));
            return list;
        }
    }

    @Transactional
    public List<Author> getAll(){
        try {
            return dao.getAll();
        } catch (Exception ignore){
            List<Author> list = new ArrayList<>(1);
            list.add(new Author(-1, ""));
            return list;
        }
    }

    @Transactional
    public long insert(Author author){
        try {
            return dao.insert(author);
        }catch (Exception ignore){
            return -1;
        }
    }

    @Transactional
    public long insert(String name){
        try {
            return dao.insert(name);
        }catch (Exception ignore){
            return -1;
        }
    }
}
