package com.atta.demo.repositories;

import com.atta.demo.models.Locality;
import com.atta.demo.models.LocationSport;
import com.atta.demo.models.Region;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface LocalityRepo extends CrudRepository<Locality, Integer> {

    boolean existsLocalityByName(String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Locality l SET l.region = :region WHERE l.id = :idLocality")
    void addRegionToLocality(@Param("idLocality") Integer idLocality, @Param("region") Region region);

    @Query("SELECT l FROM LocationSport l WHERE l.id.idSport IN :idSport AND " +
            "l.startPeriod <= :endPeriod AND l.endPeriod >= :startPeriod ORDER BY l.averageCostPerDay")
    List<LocationSport> filterLocalitiesBySportsAndPeriod(@Param("idSport") List<Integer> idSport,
                                                          @Param("startPeriod") String startPeriod,
                                                          @Param("endPeriod") String endPeriod);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO location_sport (id_locality, id_sport, start_period, end_period, average_cost_per_day) " +
            "VALUES (:idLocality,:idSport, :startPeriod, :endPeriod, :averageCostPerDay)", nativeQuery = true)
    void addSport(@Param("idLocality") Integer idLocality, @Param("idSport") Integer idSport,
                  @Param("startPeriod") String startPeriod, @Param("endPeriod") String endPeriod,
                  @Param("averageCostPerDay") Double averageCostPerDay);

    @Transactional
    @Modifying
    @Query(value = "UPDATE LocationSport ls SET ls.averageCostPerDay = :averageCostPerDay WHERE ls.id.idLocality = :idLocality " +
            "AND ls.id.idSport = :idSport")
    void updateCostLocality(@Param("idLocality") Integer locality,
                            @Param("idSport") Integer sport,
                            @Param("averageCostPerDay") Double averageCostPerDay);
}
