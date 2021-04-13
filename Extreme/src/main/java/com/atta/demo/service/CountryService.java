package com.atta.demo.service;

import com.atta.demo.models.Country;
import com.atta.demo.repositories.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    private final CountryRepo countryRepo;

    @Autowired
    public CountryService(CountryRepo countryRepo) {
        this.countryRepo = countryRepo;
    }

    public Country addCountry(Country country) {
        if (!countryRepo.existsCountryByName(country.getName())) {
            return countryRepo.save(country);
        }
        return null;
    }

    public List<Country> getAllCountries() {
        return (List<Country>) countryRepo.findAll();
    }

    public Optional<Country> getCountryById(Integer id) {
        return countryRepo.findById(id);
    }

    public void deleteCountry(Integer id) {
        countryRepo.deleteById(id);
    }

    public void updateCountry(Integer id, String name) {
        countryRepo.updateCountry(id, name);
    }
}
