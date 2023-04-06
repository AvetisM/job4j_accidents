package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.AccidentTypeService;
import ru.job4j.accidents.service.RuleService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/accidents")
public class AccidentController {
    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    @GetMapping
    public String accidents(Model model) {
        model.addAttribute("user", "Petr Arsentev");
        model.addAttribute("accidents", accidentService.findAll());
        return "accident/accidents";
    }

    @GetMapping("/formAdd")
    public String formAdd(Model model) {
        model.addAttribute("types", accidentTypeService.getAccidentTypes());
        model.addAttribute("rules", ruleService.getRules());
        return "accident/create";
    }

    @GetMapping("/formEdit")
    public String formEdit(@RequestParam("id") int id, Model model) {
        Optional<Accident> optionalAccident = accidentService.findById(id);
        if (optionalAccident.isEmpty()) {
            return "accident/accidents";
        }
        Accident accident = optionalAccident.get();
        model.addAttribute("accident", accident);
        model.addAttribute("types", accidentTypeService.getAccidentTypes());
        model.addAttribute("accidentTypeId", accident.getType().getId());
        model.addAttribute("rules", ruleService.getRules());
        model.addAttribute("accidentRules", accident.getRules());
        return "accident/edit";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] rIds = req.getParameterValues("rIds");
        accidentService.add(accident, rIds);
        return "redirect:/index";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] rIds = req.getParameterValues("rIds");
        accidentService.update(accident, rIds);
        return "redirect:/index";
    }
}