package com.example.Olympics2024.services;

import com.example.Olympics2024.Models.Athlete;
import com.example.Olympics2024.Models.Country;
import com.example.Olympics2024.repository.AthleteRepository;
import com.example.Olympics2024.repository.CountryRepository;

import com.github.javafaker.Faker;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AthleteService {
    private final CountryRepository countryRepository;
    private final AthleteRepository athleteRepository;
    private final Faker faker;
    public AthleteService(Faker faker,AthleteRepository athleteRepository,CountryRepository countryRepository) {
        this.faker = faker;
        this.athleteRepository=athleteRepository;
        this.countryRepository=countryRepository;
    }


    public List<Athlete> getAllAthletes(){
        return athleteRepository.findAll();
    }

    public void createRandomAthletes(int n){
        int i=0;
        while(i<n) {
            Random random = new Random();
            Athlete athlete = new Athlete();
            athlete.setAge(random.nextInt(42) + 18);
            athlete.setFirstName(faker.name().firstName());
            athlete.setLastName(faker.name().lastName());
            athlete.setGender(random.nextBoolean() ? 'M' : 'F');
            athlete.setCountry(faker.address().countryCode());
            if (!countryRepository.existsCountryScoreByName(athlete.getCountry())) {
                Country country =new Country();
                country.setName(athlete.getCountry());
                country.setGold(0);
                country.setSilver(0);
                country.setBronze(0);
                countryRepository.save(country);
            }
            if(!athleteRepository.existsByFirstNameAndLastName(athlete.getFirstName(),athlete.getLastName())){
                athleteRepository.save(athlete);
                i++;
            }
        }
    }

    public List<Athlete> getTopper(int count) {
        Pageable pageable= PageRequest.of(0,count);
        return athleteRepository.getTopAthletes(pageable);
    }

    public List<Athlete> getTopperByGender(Character gender, int count) {
        Pageable pageable = PageRequest.of(0,count);
        return  athleteRepository.getTopAthletesByGender(gender,pageable);
    }
}
