package com.atta.demo.repositories;

import com.atta.demo.models.Country;
import com.atta.demo.models.Region;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface RegionRepo extends CrudRepository<Region, Integer> {

    boolean existsRegionByName(String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Region r SET r.idCountry = :country WHERE r.id = :idRegion")
    void addCountryToRegion(@Param("idRegion") Integer idRegion, @Param("country") Country country);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Region r SET r.name = :name WHERE r.id = :idRegion")
    void updateRegion(@Param("idRegion") Integer idRegion, @Param("name") String name);
}
