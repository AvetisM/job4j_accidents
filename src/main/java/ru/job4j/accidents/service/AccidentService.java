package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.repository.Store;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentService {

    private Store store;

    public List<Accident> findAll() {
      return store.findAll();
    }

    public  Optional<Accident>  findById(int id) {
        return store.findById(id);
    }

    public boolean add(Accident accident) {
        return store.add(accident);
    }

    public boolean update(Accident accident) {
        return store.update(accident);
    }
}
