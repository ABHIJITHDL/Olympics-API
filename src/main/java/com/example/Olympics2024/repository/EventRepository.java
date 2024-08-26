package com.example.Olympics2024.repository;

import com.example.Olympics2024.Models.Country;
import com.example.Olympics2024.Models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "SELECT c FROM Event e JOIN e.countries c WHERE e.id = :eventId ORDER BY (c.gold + c.silver + c.bronze) DESC")
    List<Country> findTopCountriesByEvent(@Param("eventId") long eventId);

    boolean existsByName(String name);
}
