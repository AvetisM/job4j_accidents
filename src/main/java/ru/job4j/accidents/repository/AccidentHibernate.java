package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.util.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentHibernate {

    private static final String FIND_ALL = "from Accident as acc join fetch acc.type";
    private static final String FIND_BY_ID =
            "from Accident as a join fetch a.type join fetch a.rules where a.id = :fId";

    private final CrudRepository crudRepository;

    public boolean add(Accident accident) {
        try {
            crudRepository.run(session -> session.persist(accident));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Accident accident) {
        try {
            crudRepository.run(session -> session.merge(accident));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Accident> findAll() {
        return crudRepository.query(FIND_ALL, Accident.class);
    }

    public Optional<Accident> findById(int id) {
        return crudRepository.optional(
                FIND_BY_ID, Accident.class,
                Map.of("fId", id)
        );
    }
}
