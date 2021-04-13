package com.atta.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LocalityDto {

    private String name;

    private List<String> nameSports;

    private Double averageCostPerDay;

}
