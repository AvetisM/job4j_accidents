package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import ru.job4j.accidents.model.Accident;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
public class AccidentMem {

    private final AtomicInteger currentId = new AtomicInteger(0);
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public List<Accident> findAll() {
        return new ArrayList<>(accidents.values());
    }

    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    public boolean add(Accident accident) {
        accident.setId(currentId.incrementAndGet());
        return accidents.putIfAbsent(accident.getId(), accident) == null;
    }

    public boolean update(Accident accident) {
        return accidents.replace(accident.getId(), accidents.get(accident.getId()), accident);
    }
}
