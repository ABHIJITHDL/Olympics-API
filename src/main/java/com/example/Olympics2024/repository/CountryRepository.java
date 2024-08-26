package com.example.Olympics2024.repository;

import com.example.Olympics2024.Models.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CountryRepository extends JpaRepository<Country,String> {
    @Query("SELECT c FROM Country c ORDER BY c.gold DESC")
    List<Country> findTopByOrderByGoldDesc(Pageable pageable);

    @Query("SELECT c FROM Country c ORDER BY c.silver DESC")
    List<Country>   findTopByOrderBySilverDesc(Pageable pageable);

    @Query("SELECT c FROM Country c ORDER BY c.bronze DESC")
    List<Country>   findTopByOrderByBronzeDesc(Pageable n);

    @Query("SELECT c FROM Country c ORDER BY (c.gold + c.silver + c.bronze) DESC")
    List<Country> findTopCountriesByTotalMedals(Pageable pageable);

    boolean existsCountryScoreByName(String name);
}
