package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.PizzaModel;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepo;

    @GetMapping
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/pizzas")
    public String getListaPizzas(Model model) {
        List<PizzaModel> pizzas = pizzaRepo.findAll();

        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    @GetMapping("/pizzas/{id}")
    public String getPizzaById(@PathVariable("id") int id, Model model) {
        model.addAttribute("pizza", pizzaRepo.findById(id).get());

        return "pizzas/show";
    }

    @GetMapping("/search")
    public String searchTitle(@RequestParam String nome, Model model) {
        List<PizzaModel> pizzas = pizzaRepo.findByNomeContaining(nome);
        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    @GetMapping("/pizzas/create")
    public String create(Model model) {
        model.addAttribute("pizza", new PizzaModel());
        return "pizzas/create";
    }

    @PostMapping("/pizzas/create")
    public String store(@Valid @ModelAttribute("pizza") PizzaModel formPizza, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "pizzas/create";
        }
        pizzaRepo.save(formPizza);
        return "redirect:/pizzas";
    }

    @GetMapping("/pizzas/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("pizza", pizzaRepo.findById(id).get());
        return "pizzas/edit";
    }

    @PostMapping("/pizzas/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") PizzaModel formPizza, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "pizzas/edit";
        }
        pizzaRepo.save(formPizza);
        return "redirect:/pizzas";
    }


    @PostMapping("pizzas/delete/{id}")
    public String delete(@PathVariable Integer id){
        pizzaRepo.deleteById(id);
        return "redirect:/pizzas";
    }
}
