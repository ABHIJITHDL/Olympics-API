package com.example.Olympics2024.mapper;

import com.example.Olympics2024.Models.Athlete;
import com.example.Olympics2024.Models.Event;
import com.example.Olympics2024.dto.AllEvents;
import com.example.Olympics2024.dto.AthleteShortInfo;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AllEventsMapper {
    public static AllEvents mapAllEvents(Event event){
        List<AthleteShortInfo> athleteShortInfos = event.getAthletes().stream().filter(Objects::nonNull)
        .map(AllEventsMapper::mapAthleteShortInfo).toList();
        AllEvents allEvents = new AllEvents();
        allEvents.setId(event.getId());
        allEvents.setName(event.getName());
        allEvents.setAthletes(athleteShortInfos);
        allEvents.setGoldMedalist(mapAthleteShortInfo(event.getGoldMedalist()));
        allEvents.setSilverMedalist(mapAthleteShortInfo(event.getSilverMedalist()));
        allEvents.setBronzeMedalist(mapAthleteShortInfo(event.getBronzeMedalist()));
        allEvents.setDate(event.getDate());
        allEvents.setLocation(event.getLocation());
        return allEvents;
    }

    public static AthleteShortInfo mapAthleteShortInfo(Athlete athlete){
        if (athlete == null) {
            return null;
        }
        AthleteShortInfo athleteShortInfo = new AthleteShortInfo();
        athleteShortInfo.setFirstName(athlete.getFirstName());
        athleteShortInfo.setLastName(athlete.getLastName());
        athleteShortInfo.setCountry(athlete.getCountry());
        return athleteShortInfo;
    }
}
