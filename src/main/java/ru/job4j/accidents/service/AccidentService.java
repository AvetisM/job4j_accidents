package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.repository.AccidentJdbcTemplate;
import ru.job4j.accidents.model.Accident;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentService {

    private final AccidentJdbcTemplate store;
    private final AccidentTypeService accidentTypeService;

    public List<Accident> findAll() {
      return store.findAll();
    }

    public  Optional<Accident>  findById(int id) {
        return store.findById(id);
    }

    @Transactional
    public Accident add(Accident accident,  String[] rIds) {
        accident.setType(accidentTypeService.getAccidentType(accident.getType().getId()));
        int[] rulesIdIntArray = Arrays.stream(rIds).mapToInt(Integer::parseInt).toArray();
        store.add(accident);
        store.addAccidentRules(accident, rulesIdIntArray);
        return accident;
    }

    @Transactional
    public boolean update(Accident accident, String[] rIds) {
        accident.setType(accidentTypeService.getAccidentType(accident.getType().getId()));
        int[] rulesIdIntArray = Arrays.stream(rIds).mapToInt(Integer::parseInt).toArray();
        return store.update(accident) && store.updateAccidentRules(accident, rulesIdIntArray);
    }
}
