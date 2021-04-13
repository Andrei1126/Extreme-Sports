package com.atta.demo.service;

import com.atta.demo.models.Country;
import com.atta.demo.models.Region;
import com.atta.demo.repositories.RegionRepo;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {
    private final RegionRepo regionRepo;
    private final CountryService countryService;

    @Autowired
    public RegionService(RegionRepo regionRepo, CountryService countryService) {
        this.regionRepo = regionRepo;
        this.countryService = countryService;
    }

    public Region addRegion(Region region, Integer countryId) throws NotFoundException {
        Region savedRegion;
        Country country = countryService.getCountryById(countryId).orElseThrow(() -> new NotFoundException("Country not found."));

        if (!regionRepo.existsRegionByName(region.getName())) {
            savedRegion = regionRepo.save(region);
            regionRepo.addCountryToRegion(savedRegion.getId(), country);
        }
        return null;
    }

    public List<Region> getAllRegions() {
        return (List<Region>) regionRepo.findAll();
    }

    public Optional<Region> getRegionById(Integer id) {
        return regionRepo.findById(id);
    }

    public void deleteRegion(Integer id) {
        regionRepo.deleteById(id);
    }

    public void updateRegion(Integer id, String name) {
        regionRepo.updateRegion(id, name);
    }
}
