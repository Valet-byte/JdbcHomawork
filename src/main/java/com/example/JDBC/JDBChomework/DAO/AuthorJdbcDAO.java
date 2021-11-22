package com.example.JDBC.JDBChomework.DAO;

import com.example.JDBC.JDBChomework.mappers.AuthorMapper;
import com.example.JDBC.JDBChomework.models.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Repository
public class AuthorJdbcDAO {

    private final NamedParameterJdbcTemplate jdbc;

    public Author findById(long id){
        final Map<String, Object> map = new HashMap<>(1);
        map.put("id", id);

        return jdbc.queryForObject("select * from author where id = :id", map, new AuthorMapper());
    }

    public List<Author> getAll(){
        return jdbc.query("select * from author", new AuthorMapper());
    }

    public List<Author> findByName(String name){
        final Map<String, String> param = new HashMap<>(1);
        param.put("name", name);

        return jdbc.query("select * from author where name = :name", param, new AuthorMapper());
    }

    public long insert(Author author){

        final MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("name", author.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update("insert into author (name) values (:name)", param, keyHolder);

        return (long) Objects.requireNonNull(keyHolder.getKeys()).get("ID");
    }

    public long insert(String authorName){

        final MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("name", authorName);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update("insert into author (name) values (:name)", param, keyHolder);

        return (long) Objects.requireNonNull(keyHolder.getKeys()).get("ID");
    }
}
