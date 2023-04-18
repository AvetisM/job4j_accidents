package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.job4j.accidents.model.Rule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class RuleJdbcTemplate {

    public static final String FIND_ALL = "select id, name from accident_rule";
    public static final String FIND_BY_ID = "select id, name from accident_rule where id = ?";
    public static final String FIND_BY_IDS = "select id, name from accident_rule where id in (?)";
    private final JdbcTemplate jdbc;

    public List<Rule> getRules() {
        return jdbc.query(FIND_ALL, (rs, row) -> getRule(rs));
    }

    public Rule getRuleById(int id) {
        return jdbc.queryForObject(FIND_BY_ID, (rs, row) -> getRule(rs), id);
    }

    public Set<Rule> getRulesByIds(String[] rIds) {
        List<Rule> rules = jdbc.query(FIND_BY_IDS,
                (rs, row) -> getRule(rs), rIds);
        return new HashSet<>(rules);
    }

    private Rule getRule(ResultSet rs) throws SQLException {
        return new Rule(
                rs.getInt("id"),
                rs.getString("name"));
    }
}
