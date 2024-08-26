package com.example.Olympics2024.services;

import com.example.Olympics2024.Models.Athlete;
import com.example.Olympics2024.Models.Country;
import com.example.Olympics2024.Models.Event;
import com.example.Olympics2024.Models.OlympicEventList;

import com.example.Olympics2024.dto.AllEvents;
import com.example.Olympics2024.mapper.AllEventsMapper;
import com.example.Olympics2024.repository.AthleteRepository;
import com.example.Olympics2024.repository.CountryRepository;
import com.example.Olympics2024.repository.EventRepository;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService {
    private EventRepository eventRepository;
    private AthleteRepository athleteRepository;
    private CountryRepository countryRepository;
    private AthleteService athleteService;
    private Faker faker;
    public EventService(EventRepository eventItemRepository, Faker faker,AthleteRepository athleteRepository,
                        AthleteService athleteService,CountryRepository countryRepository) {
        this.athleteRepository = athleteRepository;
        this.eventRepository = eventItemRepository;
        this.faker = faker;
        this.athleteService=athleteService;
        this.countryRepository=countryRepository;
    }

    public Event createEvent(){
        Event event = new Event();
        event.setName(OlympicEventList.EVENTS[new Random().nextInt(100)]);
        Date start = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(LocalDateTime.now().plusDays(10).atZone(ZoneId.systemDefault()).toInstant());
        event.setDate(String.valueOf(faker.date().between(start, end)));
        return event;
    }

    public void createRandomEvents(int events, int participants, String country){
        int i=0;
        while(i<events){
            Event event=createEvent();
            if(!eventRepository.existsByName(event.getName())){
                List<Athlete> athletes= (List<Athlete>) athleteRepository.getRandomAthletes(participants);
                Set<Country> countries = athletes.stream().map(athlete -> countryRepository.findById(athlete.getCountry()))
                        .filter(Optional::isPresent).map(Optional::get).distinct().collect(Collectors.toSet());
                event.setCountries(countries);
                event.setLocation(country);
                event.setAthletes(athletes);
                eventRepository.save(event);
                i++;
            }
        }
    }

    public List<Country> getTopCountriesByEvent(Long id){
        return eventRepository.findTopCountriesByEvent(id);
    }

    public List<AllEvents> getAllEvents(){
        return eventRepository.findAll().stream().map(AllEventsMapper::mapAllEvents).toList();
    }

    public Event getEventById(Long id){
        return eventRepository.findById(id).orElse(null);
    }

    public Athlete awardAthlete(Athlete athlete,Event event,int n){
        if(n==1){
            athlete.setGoldMedals(athlete.getGoldMedals()+1);
            Country country=countryRepository.findById(athlete.getCountry()).orElse(null);
            country.setGold(country.getGold()+1);
            event.setGoldMedalist(athlete);
            countryRepository.save(country);
        } else if (n==2) {
            athlete.setSilverMedals(athlete.getSilverMedals()+1);
            Country country=countryRepository.findById(athlete.getCountry()).orElse(null);
            country.setSilver(country.getSilver()+1);
            event.setSilverMedalist(athlete);
            countryRepository.save(country);
        }else{
            athlete.setBronzeMedals(athlete.getBronzeMedals()+1);
            Country country=countryRepository.findById(athlete.getCountry()).orElse(null);
            event.setBronzeMedalist(athlete);
            country.setBronze(country.getBronze()+1);
        }
        return athlete;
    }

    public Optional<Event> simulateEvent(Long id){
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if(optionalEvent.isEmpty()){
            return optionalEvent;
        }
        Event event=optionalEvent.get();
        Collections.shuffle(event.getAthletes());
        List<Athlete> winners= new ArrayList<>(event.getAthletes().stream().limit(3).toList());
        winners.set(0,awardAthlete(winners.get(0),event,1));
        winners.set(1,awardAthlete(winners.get(1),event,2));
        winners.set(2,awardAthlete(winners.get(2),event,3));
        athleteRepository.saveAll(winners);
        return Optional.of(event);
    }


    public List<Event> simulateAllEvents() {
        return eventRepository.findAll().stream().map((Event id) -> simulateEvent(id.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get).toList();
    }


    public void startNewOlympics(int events,int athletes,int participants,String  country) {
        eventRepository.deleteAll();
        athleteRepository.deleteAll();
        countryRepository.deleteAll();
        athleteService.createRandomAthletes(athletes);
        createRandomEvents(events,participants,country);
    }
}
