package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class RuleService {

    private final RuleRepository store;

    public List<Rule> getRules() {
        return store.findAll();
    }

    public Set<Rule> getRulesByIds(Integer[] rIds) {
       return store.findAllById(new HashSet<>(List.of(rIds)));
    }
}
