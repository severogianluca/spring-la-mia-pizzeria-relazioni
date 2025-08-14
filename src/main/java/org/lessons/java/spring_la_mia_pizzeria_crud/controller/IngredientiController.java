package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.IngredienteModel;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredienti")
public class IngredientiController {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("ingredienti", ingredienteRepository.findAll());
        return "ingredienti/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ingrediente", new IngredienteModel());
        return "ingredienti/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingrediente") IngredienteModel formIngrediente, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "ingredienti/create-or-edit";
        }
        ingredienteRepository.save(formIngrediente);
        return "redirect:/ingredienti";
    }
}
