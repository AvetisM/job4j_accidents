package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentTypeService {

    private final AccidentTypeRepository store;

    public List<AccidentType> getAccidentTypes() {
        return store.findAll();
    }

    public Optional<AccidentType> getAccidentType(int id) {
        return store.findById(id);
    }

}
