package com.example.Olympics2024.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    private List<Athlete> athletes;

    @ManyToOne
    private Athlete goldMedalist;

    @ManyToOne
    private Athlete silverMedalist;

    @ManyToOne
    private Athlete bronzeMedalist;

    @ManyToMany
    private Set<Country> countries;

    private String date;
    private String location;
}
