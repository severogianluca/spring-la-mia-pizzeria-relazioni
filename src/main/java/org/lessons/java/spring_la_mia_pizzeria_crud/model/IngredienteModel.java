package org.lessons.java.spring_la_mia_pizzeria_crud.model;

import java.util.List;

import org.hibernate.annotations.ManyToAny;
import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "ingredienti")
public class IngredienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    

    @NotBlank
    private String nome;

    @ManyToMany( mappedBy = "ingredienti")
    private List<PizzaModel> pizze;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<PizzaModel> getPizze() {
        return pizze;
    }

    public void setPizze(List<PizzaModel> pizze) {
        this.pizze = pizze;
    }

    
}
