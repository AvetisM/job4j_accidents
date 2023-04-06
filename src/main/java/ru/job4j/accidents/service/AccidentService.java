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

    private final Store store;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    public List<Accident> findAll() {
      return store.findAll();
    }

    public  Optional<Accident>  findById(int id) {
        return store.findById(id);
    }

    public boolean add(Accident accident,  String[] rIds) {
        accident.setType(accidentTypeService.getAccidentType(accident.getType().getId()));
        if (rIds != null) {
            accident.setRules(ruleService.getRulesByIds(rIds));
        }
        return store.add(accident);
    }

    public boolean update(Accident accident, String[] rIds) {
        accident.setType(accidentTypeService.getAccidentType(accident.getType().getId()));
        if (rIds != null) {
            accident.setRules(ruleService.getRulesByIds(rIds));
        }
        return store.update(accident);
    }

}
