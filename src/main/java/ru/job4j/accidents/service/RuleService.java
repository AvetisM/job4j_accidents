package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleJdbcTemplate;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class RuleService {

    private final RuleJdbcTemplate store;

    public List<Rule> getRules() {
        return store.getRules();
    }

    public Rule getRuleById(int id) {
        return store.getRuleById(id);
    }

    public Set<Rule> getRulesByIds(String[] rIds) {
       return store.getRulesByIds(rIds);
    }
}
