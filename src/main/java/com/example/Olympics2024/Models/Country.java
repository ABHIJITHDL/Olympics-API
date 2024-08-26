package com.example.Olympics2024.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Country {
    @Id
    @Column(unique = true)
    private String name;
    private int gold;
    private int silver;
    private int bronze;

}
