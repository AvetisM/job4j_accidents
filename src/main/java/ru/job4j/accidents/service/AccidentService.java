package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.repository.Store;
import ru.job4j.accidents.model.Accident;

import java.util.List;

@Service
@AllArgsConstructor
public class AccidentService {

    private Store store;

    public List<Accident> findAll() {
      return store.findAll();
    }
}
