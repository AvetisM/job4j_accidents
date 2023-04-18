package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentHibernate;
import ru.job4j.accidents.model.Accident;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AccidentService {

    private final AccidentHibernate store;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    public List<Accident> findAll() {
      return store.findAll();
    }

    public  Optional<Accident>  findById(int id) {
        return store.findById(id);
    }

    @Transactional
    public boolean add(Accident accident,  String[] rIds) {
        if (!checkAndFillAccidentType(accident)) {
            return false;
        }
        fillAccidentRules(accident, rIds);
        return store.add(accident);
    }

    @Transactional
    public boolean update(Accident accident, String[] rIds) {
        if (!checkAndFillAccidentType(accident)) {
            return false;
        }
        fillAccidentRules(accident, rIds);
        return store.update(accident);
    }

    private boolean checkAndFillAccidentType(Accident accident) {
        Optional<AccidentType> type =
                accidentTypeService.getAccidentType(accident.getType().getId());
        type.ifPresent(accident::setType);
        return type.isPresent();
    }

    private void fillAccidentRules(Accident accident, String[] rIds) {
        Integer[] rulesIdIntArray = Arrays.stream(rIds)
                .mapToInt(Integer::parseInt)
                .boxed()
                .toArray(Integer[]::new);
        Set<Rule> rules = ruleService.getRulesByIds(rulesIdIntArray);
        accident.setRules(rules);
    }
}
