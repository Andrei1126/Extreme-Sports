package com.atta.demo.repositories;

import com.atta.demo.models.Sport;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface SportRepo extends CrudRepository<Sport, Integer> {

    public boolean existsSportByName(String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Sport s SET s.name = :name WHERE s.id = :idSport")
    void updateSport(@Param("idSport") Integer idSport, @Param("name") String name);
}
