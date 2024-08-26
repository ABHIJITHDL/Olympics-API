package com.example.Olympics2024.controllers;

import com.example.Olympics2024.Models.Athlete;
import com.example.Olympics2024.services.AthleteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/athletes")
public class AthleteController {
    AthleteService athleteService;

    public AthleteController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    @GetMapping()
    public ResponseEntity<List<Athlete>> getAllAthletes(){
        List<Athlete> athletes = athleteService.getAllAthletes();
        return new ResponseEntity<>(athletes, HttpStatus.OK);
    }

    @GetMapping("/toppers")
    public ResponseEntity<List<Athlete>> getToppers(@RequestParam(value = "gender",required = false)Character gender,@RequestParam(value ="count",defaultValue = "10")int count){
        List<Athlete> athletes;
        if(gender!=null){
            athletes = athleteService.getTopperByGender(gender,count);
        }else{
        athletes = athleteService.getTopper(count);
        }
        if(athletes == null)
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(athletes,HttpStatus.OK);
    }
}
