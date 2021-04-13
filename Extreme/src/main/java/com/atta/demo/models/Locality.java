package com.atta.demo.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Locality {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "locality", cascade = CascadeType.ALL)
    List<LocationSport> locationSports;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Region region;
}
