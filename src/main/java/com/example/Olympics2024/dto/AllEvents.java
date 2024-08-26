package com.example.Olympics2024.dto;

import com.example.Olympics2024.Models.Athlete;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AllEvents {
    private Long id;
    private String name;
    private List<AthleteShortInfo> athletes;
    private AthleteShortInfo goldMedalist;
    private AthleteShortInfo silverMedalist;
    private AthleteShortInfo bronzeMedalist;
    private String date;
    private String location;
}

