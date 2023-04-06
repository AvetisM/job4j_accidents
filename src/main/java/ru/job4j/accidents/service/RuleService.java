package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class RuleService {

    private final List<Rule> rules;

    public RuleService() {
        this.rules = List.of(
                new Rule(1, "Статья. 1"),
                new Rule(2, "Статья. 2"),
                new Rule(3, "Статья. 3"));
    }

    public List<Rule> getRules() {
        return rules;
    }

    public Rule getRuleById(int id) {
        return rules.stream()
                .filter(rule -> id == rule.getId())
                .findAny()
                .orElse(null);
    }

    public Set<Rule> getRulesByIds(String[] rIds) {
        Set<Rule> result = new HashSet<>();
        for (String rId : rIds) {
            result.add(
                    getRuleById(Integer.parseInt(rId)));
        }
        return result;
    }
}
