package com.example.Olympics2024.services;

import com.example.Olympics2024.Models.Country;
import com.example.Olympics2024.repository.CountryRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries(){
        return countryRepository.findAll();
    }

    public List<Country> topBronzeCountries(int n){
        Pageable pageable = PageRequest.of(0,n);
        return countryRepository.findTopByOrderByBronzeDesc(pageable);
    }

    public List<Country> topSilverCountries(int n){
        Pageable pageable = PageRequest.of(0,n);
        return countryRepository.findTopByOrderBySilverDesc(pageable);
    }

    public List<Country> topGoldCountries(int n){
        Pageable pageable = PageRequest.of(0,n);
        return countryRepository.findTopByOrderByGoldDesc(pageable);
    }

    public List<Country> topMedalWinnerCountries(int n){
        Pageable pageable = PageRequest.of(0,n);
        return countryRepository.findTopCountriesByTotalMedals(pageable);
    }

    public List<Country> toppersByEvent(long id){
        return countryRepository.findAll();
    }
}
