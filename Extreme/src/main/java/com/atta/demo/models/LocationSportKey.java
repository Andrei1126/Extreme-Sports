package com.atta.demo.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class LocationSportKey implements Serializable {

    @Column(name = "id_locality")
    Integer idLocality;

    @Column(name = "id_sport")
    Integer idSport;
}
