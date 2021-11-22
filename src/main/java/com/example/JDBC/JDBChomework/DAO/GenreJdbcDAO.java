package com.example.JDBC.JDBChomework.DAO;
import com.example.JDBC.JDBChomework.mappers.GenreMapper;
import com.example.JDBC.JDBChomework.models.Genre;
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
public class GenreJdbcDAO {

    private final NamedParameterJdbcTemplate jdbc;

    public Genre findById(long id){
        final Map<String, Object> param = new HashMap<>(1);
        param.put("id", id);
        return jdbc.queryForObject("select * from genre where id = :id", param, new GenreMapper());
    }

    public List<Genre> getAll(){
        return jdbc.query("select * from genre", new GenreMapper());
    }

    public List<Genre> findByName(String name){
        final Map<String, String> param = new HashMap<>(1);
        param.put("name", name);

        return jdbc.query("select * from genre where name = :name", param, new GenreMapper());
    }


    public long insert(Genre genre){
        final MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("name", genre.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update("insert into genre (name) values (:name)", param, keyHolder);

        return (long) Objects.requireNonNull(keyHolder.getKeys()).get("ID");
    }

    public long insert(String genre){
        final MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("name", genre);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update("insert into genre (name) values (:name)", param, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }
}
