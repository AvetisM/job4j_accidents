package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@AllArgsConstructor
public class AccidentTypeJdbcTemplate {

    public static final String FIND_ALL = "select id, name from accident_type";
    public static final String FIND_BY_ID = "select id, name from accident_type where id = ?";
    private final JdbcTemplate jdbc;

    public List<AccidentType> getAccidentTypes() {
        return jdbc.query(FIND_ALL, (rs, row) -> getType(rs));
    }

    public AccidentType getAccidentType(int id) {
        return jdbc.queryForObject(FIND_BY_ID,
                (rs, row) -> getType(rs), id);
    }

    private AccidentType getType(ResultSet rs) throws SQLException {
        return new AccidentType(
                rs.getInt("id"),
                rs.getString("name"));
    }
}
