package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements Store {

    private final AtomicInteger currentId = new AtomicInteger(0);
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public AccidentMem() {
        Set<Rule> rules = Set.of(
                new Rule(1, "Статья. 1"),
                new Rule(2, "Статья. 2"),
                new Rule(3, "Статья. 3"));
        Accident acc1 = new Accident(currentId.incrementAndGet(), "Сбил пешехода",
                "Черный седан номера м138ае26", "Московская 50",
                new AccidentType(1, "Две машины"), rules);
        this.accidents.put(acc1.getId(), acc1);
    }

    @Override
    public List<Accident> findAll() {
        return new ArrayList<>(accidents.values());
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    @Override
    public boolean add(Accident accident) {
        accident.setId(currentId.incrementAndGet());
        return accidents.putIfAbsent(accident.getId(), accident) == null;
    }

    @Override
    public boolean update(Accident accident) {
        return accidents.replace(accident.getId(), accidents.get(accident.getId()), accident);
    }
}
