package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.util.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentTypeHibernate {

    private static final String FIND_ALL = "from AccidentType";
    private static final String FIND_BY_ID = "from AccidentType where id = :fId";

    private final CrudRepository crudRepository;

    public List<AccidentType> getAccidentTypes() {
        return crudRepository.query(FIND_ALL, AccidentType.class);
    }

    public Optional<AccidentType> getAccidentType(int id) {
        return crudRepository.optional(
                FIND_BY_ID, AccidentType.class,
                Map.of("fId", id)
        );
    }
}
