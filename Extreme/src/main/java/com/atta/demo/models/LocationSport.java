package com.atta.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationSport {

    @EmbeddedId
    private LocationSportKey id;

    @ManyToOne
    @MapsId("idLocality")
    @JoinColumn(name = "id_locality")
    private Locality locality;

    @ManyToOne
    @MapsId("idSport")
    @JoinColumn(name = "id_sport")
    private Sport sport;

    private String startPeriod;

    private String endPeriod;

    private Double averageCostPerDay;

}
