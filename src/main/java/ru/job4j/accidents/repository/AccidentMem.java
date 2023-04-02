package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentMem implements Store {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public AccidentMem() {
        Accident acc1 = new Accident(1, "Сбил пешехода",
                "Черный седан номера м138ае26", "Московская 50");
        this.accidents.put(acc1.getId(), acc1);
    }

    @Override
    public List<Accident> findAll() {
        return new ArrayList<>(accidents.values());
    }
}
