package org.lessons.java.spring_la_mia_pizzeria_crud.model;

import java.time.LocalDate;

import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "offerte_speciali")
public class OffertaSpeciale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private PizzaModel pizza;

    @NotBlank(message = "Insert a title")
    private String title;

    @NotNull(message = "The special offer cannot be null")
    private LocalDate startSpecialOffer;

    @NotNull(message = "The special offer cannot be null")
    private LocalDate finishSpecialOffer;

}
