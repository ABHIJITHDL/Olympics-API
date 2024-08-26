package com.example.Olympics2024.controllers;

import com.example.Olympics2024.Models.Country;
import com.example.Olympics2024.repository.CountryRepository;
import com.example.Olympics2024.services.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {
    CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<Country> getAllCountries(){
        return countryService.getAllCountries();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/goldWinners")
    public List<Country> getGoldWinner(@RequestParam(value = "count",defaultValue = "10") int count){
        return countryService.topGoldCountries(count);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/silverWinners")
    public List<Country> getSilverWinner(@RequestParam(value = "count",defaultValue = "10") int count){
        return countryService.topSilverCountries(count);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/bronzeWinners")
    public List<Country> getBronzeWinner(@RequestParam(value = "count",defaultValue = "10") int count){
        return countryService.topBronzeCountries(count);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/topMedalWinners")
    public List<Country> getTopMedalWinners(@RequestParam(value = "count",defaultValue = "10") int count){
        return countryService.topMedalWinnerCountries(count);
    }

}
