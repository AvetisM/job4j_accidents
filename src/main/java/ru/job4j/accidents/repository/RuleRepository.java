package ru.job4j.accidents.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Rule;

import java.util.List;
import java.util.Set;

public interface RuleRepository extends CrudRepository<Rule, Integer> {

    @Override
    List<Rule> findAll();

    @Override
    Set<Rule> findAllById(Iterable<Integer> integers);

}
