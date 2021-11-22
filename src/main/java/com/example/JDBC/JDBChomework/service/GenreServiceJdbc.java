package com.example.JDBC.JDBChomework.service;

import com.example.JDBC.JDBChomework.DAO.GenreJdbcDAO;
import com.example.JDBC.JDBChomework.models.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceJdbc {

    private final GenreJdbcDAO dao;

    @Transactional
    public Genre findById(long id){
        try {
            return dao.findById(id);
        } catch (Exception ignore){
            return new Genre(-1, "");
        }
    }

    @Transactional
    public long insert(Genre genre){
        try {
            return dao.insert(genre);
        }catch (Exception ignore){
            return -1;
        }
    }

    @Transactional
    public long insert(String genre){
        try {
            return dao.insert(genre);
        }catch (Exception ignore){
            return -1;
        }
    }

    @Transactional
    public List<Genre> getAll(){
        try {
            return dao.getAll();
        } catch (Exception ignore){
            List<Genre> list = new ArrayList<>(1);
            list.add(new Genre(-1, ""));
            return list;
        }
    }

    @Transactional
    public List<Genre> findByGenre(String genre){
        try {
            return dao.findByName(genre);
        } catch (Exception ignore){
            List<Genre> list = new ArrayList<>(1);
            list.add(new Genre(-1, ""));
            return list;
        }
    }

}
