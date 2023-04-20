package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.util.CrudRepository;

import java.util.*;

@AllArgsConstructor
public class AccidentRuleHibernate {

    private static final String FIND_ALL = "from Rule";
    private static final String FIND_BY_IDS = "from Rule where id in (:fId)";

    private final CrudRepository crudRepository;

    public List<Rule> getRules() {
        return crudRepository.query(FIND_ALL, Rule.class);
    }

    public Set<Rule> getRulesByIds(Integer[] rIds) {
        List<Rule> rules = crudRepository.queryParameterList(
                FIND_BY_IDS, Rule.class, Map.of("fId", rIds));
        return new HashSet<>(rules);

    }
}
