package com.atta.demo.service;

import com.atta.demo.dtos.LocalityDto;
import com.atta.demo.models.*;
import com.atta.demo.repositories.LocalityRepo;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class LocalityService {

    private final LocalityRepo localityRepo;
    private final RegionService regionService;

    @Autowired
    public LocalityService(LocalityRepo localityRepo, RegionService regionService) {
        this.localityRepo = localityRepo;
        this.regionService = regionService;
    }

    public Locality addLocality(Locality locality, Integer regionId) throws NotFoundException {
        Locality savedLocality;
        Region region = regionService.getRegionById(regionId).orElseThrow(() -> new NotFoundException("Region not found."));

        if (!localityRepo.existsLocalityByName(locality.getName())) {
            savedLocality = localityRepo.save(locality);
            localityRepo.addRegionToLocality(savedLocality.getId(), region);
        }
        return null;
    }

    public List<Locality> getAllLocalities() {
        return (List<Locality>) localityRepo.findAll();
    }

    public Optional<Locality> getLocalityById(Integer id) {
        return localityRepo.findById(id);
    }

    public void deleteLocality(Integer id) {
        localityRepo.deleteById(id);
    }

    public Locality updateLocality(Locality newLocality) {
        return localityRepo.save(newLocality);
    }

    public List<LocalityDto> searchLocalities(List<Integer> idSport, String startPeriod, String endPeriod) {
        List<LocationSport> locationSports = localityRepo.filterLocalitiesBySportsAndPeriod(idSport, startPeriod, endPeriod);
        HashMap<Integer, LocalityDto> dtoMap = new HashMap<>();
        LocalityDto localityDto;

        for (LocationSport locationSport: locationSports) {

            if ((localityDto = dtoMap.putIfAbsent(locationSport.getId().getIdLocality(),
                    new LocalityDto(locationSport.getLocality().getName(),
                            new ArrayList<>(List.of(locationSport.getSport().getName())),
                            locationSport.getAverageCostPerDay()))) != null) {

                localityDto.getNameSports().add(locationSport.getSport().getName());
                localityDto.setAverageCostPerDay(localityDto.getAverageCostPerDay() + locationSport.getAverageCostPerDay());
            }
        }

        for (Integer key: dtoMap.keySet()) {
            localityDto = dtoMap.get(key);
            if(localityDto.getNameSports().size() == 1) {
                localityDto.setAverageCostPerDay(localityDto.getAverageCostPerDay());
            }
            else {
                localityDto.setAverageCostPerDay(localityDto.getAverageCostPerDay() / localityDto.getNameSports().size());
            }
        }

        return new ArrayList<>(dtoMap.values());
    }

    public void addSport(Integer idLocality, Integer idSport, String startPeriod, String endPeriod, Double averageCostPerDay) {
        localityRepo.addSport(idLocality, idSport, startPeriod, endPeriod, averageCostPerDay);

        }

    public void updateCostLocality(Integer idLocality, Integer idSport, Double averageCostPerDay) {
        localityRepo.updateCostLocality(idLocality, idSport, averageCostPerDay);
    }
}
