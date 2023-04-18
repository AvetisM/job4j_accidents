package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentRuleHibernate;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class RuleService {

    private final AccidentRuleHibernate store;

    public List<Rule> getRules() {
        return store.getRules();
    }

    public Set<Rule> getRulesByIds(Integer[] rIds) {
       return store.getRulesByIds(rIds);
    }
}
