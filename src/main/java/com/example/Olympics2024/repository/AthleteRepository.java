package com.example.Olympics2024.repository;

import com.example.Olympics2024.Models.Athlete;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AthleteRepository extends JpaRepository<Athlete,Long> {
    @Query(value = "SELECT * FROM athlete ORDER BY RANDOM() LIMIT :n", nativeQuery = true)
    List<Athlete> getRandomAthletes(@Param("n") int n);

    @Query(value = "SELECT a FROM Athlete a ORDER BY (a.goldMedals + a.silverMedals + a.bronzeMedals) DESC")
    List<Athlete> getTopAthletes(Pageable pageable);

    @Query(value = "SELECT a FROM Athlete a WHERE a.gender = :gender ORDER BY (a.goldMedals + a.silverMedals + a.bronzeMedals) DESC")
    List<Athlete> getTopAthletesByGender(@Param("gender") Character gender, Pageable pageable);

    boolean existsByFirstNameAndLastName(String firstName,String lastName);
}
