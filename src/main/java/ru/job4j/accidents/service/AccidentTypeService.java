package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AccidentTypeService {

    private final List<AccidentType> accidentTypes;

    public AccidentTypeService() {
        this.accidentTypes = new ArrayList<>();
        this.accidentTypes.add(new AccidentType(1, "Две машины"));
        this.accidentTypes.add(new AccidentType(2, "Машина и человек"));
        this.accidentTypes.add(new AccidentType(3, "Машина и велосипед"));
    }

    public List<AccidentType> getAccidentTypes() {
        return accidentTypes;
    }

    public AccidentType getAccidentType(int id) {
        return accidentTypes.stream()
                .filter(type -> id == type.getId())
                .findAny()
                .orElse(null);
    }
}
