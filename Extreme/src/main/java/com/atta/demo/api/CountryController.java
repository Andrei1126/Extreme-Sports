package com.atta.demo.api;

import com.atta.demo.models.Country;
import com.atta.demo.service.CountryService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/country")
@RestController
public class CountryController {
    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping
    public Country addCountry(@NotNull @RequestBody Country country) {
        return countryService.addCountry(country);
    }

    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping(path = "{id}")
    public Country getCountryById(@PathVariable("id") Integer id) {
        return countryService.getCountryById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteCountryById(@PathVariable("id") Integer id) {
        countryService.deleteCountry(id);
    }

    @PutMapping(path = "{id}")
    public void updateCountry(@PathVariable("id") Integer id, @NotNull @RequestBody Country countryToUpdate) {
        countryService.updateCountry(id, countryToUpdate.getName());
    }
}
