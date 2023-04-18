package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@AllArgsConstructor
public class AccidentJdbcTemplate {

    public static final String INSERT =
            "insert into accident (name, text, address, type_id) values (?, ?, ?, ?)";
    public static final String UPDATE =
            "update accident set name = ?, text = ?, address = ?, type_id = ? where id = ?";

    public static final String DELETE_ACC_RULES =
            "delete from accident_rules  where accident_id = ?";

    public static final String ADD_ACC_RULES = new StringBuilder()
            .append("insert into accident_rules (accident_id, rule_id) ")
            .append("select acc.id, r.id from accident acc, accident_rule r ")
            .append("where acc.id = ? AND r.id = ?").toString();

    public static final String FIND_ALL = new StringBuilder()
            .append("select acc.id, acc.name, acc.text, acc.address, acc.type_id, ")
            .append("acc_t.name as type_name from accident as acc ")
            .append("left join accident_type as acc_t ")
            .append("on acc.type_id = acc_t.id").toString();

    public static final String FIND_BY_ID = new StringBuilder()
            .append(FIND_ALL)
            .append(" where acc.id = ?").toString();

    public static final String FIND_ACC_RULES_BY_ID = new StringBuilder()
            .append("select rule_id, a_r.name from accident_rules ")
            .append("left join accident_rule as a_r ")
            .append("on rule_id = a_r.id ")
            .append("where accident_id = ?").toString();

    private final JdbcTemplate jdbc;

    public Accident add(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT, new String[] {"id"});
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
            }, keyHolder);
        accident.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return accident;
    }

    public Accident addAccidentRules(Accident accident, int[] rIds) {
        for (int rId : rIds) {
            jdbc.update(ADD_ACC_RULES, accident.getId(), rId);
        }
        return accident;
    }

    public List<Accident> findAll() {
        return jdbc.query(FIND_ALL, (rs, row) -> getAccident(rs));
    }

    public Optional<Accident> findById(int id) {
        Accident accident = jdbc.queryForObject(FIND_BY_ID,
                (resultSet, rowNum) -> getAccident(resultSet), id);
        List<Rule> rules = jdbc.query(FIND_ACC_RULES_BY_ID,
                (resultSet, row) -> {
                    return new Rule(resultSet.getInt("rule_id"), resultSet.getString("name"));
                }, id);
        accident.setRules(new HashSet<>(rules));
        return Optional.of(accident);
    }

    public boolean update(Accident accident) {
        return jdbc.update(
                UPDATE,
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId()) != -1;
    }

    public boolean updateAccidentRules(Accident accident, int[] rIds) {
        boolean result = false;
        jdbc.update(DELETE_ACC_RULES, accident.getId());
        for (int rId : rIds) {
            result = jdbc.update(ADD_ACC_RULES, accident.getId(), rId) != -1;
        }
        return result;
    }

    private Accident getAccident(ResultSet rs) throws SQLException {
        return new Accident(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("text"),
                rs.getString("address"),
                new AccidentType(rs.getInt("type_id"), rs.getString("type_name")),
                new HashSet<>());
    }
}
