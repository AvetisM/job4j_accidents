package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/accidents")
public class AccidentController {
    private final AccidentService accidentService;

    @GetMapping
    public String accidents(Model model) {
        model.addAttribute("user", "Petr Arsentev");
        model.addAttribute("accidents", accidentService.findAll());
        return "accident/accidents";
    }

    @GetMapping("/formAdd")
    public String formAdd() {
        return "accident/create";
    }

    @GetMapping("/formEdit/{id}")
    public String formDetail(Model model, @PathVariable("id") int id) {
        Optional<Accident> optionalAccident = accidentService.findById(id);
        if (optionalAccident.isEmpty()) {
            return "accident/accidents";
        }
        Accident accident = optionalAccident.get();
        model.addAttribute("accident", accident);
        return "accident/edit";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Accident accident) {
        accidentService.add(accident);
        return "redirect:/index";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident) {
        accidentService.update(accident);
        return "redirect:/index";
    }
}