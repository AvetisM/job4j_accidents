package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Accident;

import java.util.List;

public interface Store {
    List<Accident> findAll();
}
