package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

public interface Store {
    List<Accident> findAll();

    Optional<Accident> findById(int id);

    Accident add(Accident accident);

    boolean update(Accident accident);

    Accident addAccidentRules(Accident accident, int[] rulesIdIntArray);

    boolean updateAccidentRules(Accident accident, int[] rulesIdIntArray);
}
