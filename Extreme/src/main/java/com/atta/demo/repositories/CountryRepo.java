package com.atta.demo.repositories;

import com.atta.demo.models.Country;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface CountryRepo extends CrudRepository<Country, Integer> {

    public boolean existsCountryByName(String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Country c SET c.name = :name WHERE c.id = :idCountry")
    void updateCountry(@Param("idCountry") Integer idCountry, @Param("name") String name);
}
