package com.atta.demo.api;

import com.atta.demo.dtos.LocalityDto;
import com.atta.demo.models.Locality;
import com.atta.demo.service.LocalityService;
import com.sun.istack.NotNull;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/locality")
@RestController
public class LocalityController {

    private final LocalityService localityService;

    @Autowired
    public LocalityController(LocalityService localityService) {
        this.localityService = localityService;
    }

    @PostMapping
    public Locality addLocality(@NotNull @RequestBody Locality locality,
                                @RequestHeader Integer regionId) throws NotFoundException {
        return localityService.addLocality(locality, regionId);
    }

    @GetMapping(path = "search")
    public List<LocalityDto> searchLocalities(@RequestBody List<Integer> idSport,
                                              @RequestHeader String startPeriod,
                                              @RequestHeader String endPeriod) {
        return localityService.searchLocalities(idSport, startPeriod, endPeriod);
    }

    @GetMapping
    public List<Locality> getAllLocalities() {
        return localityService.getAllLocalities();
    }

    @GetMapping(path = "{id}")
    public Locality getLocalityById(@PathVariable("id") Integer id) {
        return localityService.getLocalityById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteLocalityById(@PathVariable("id") Integer id) {
        localityService.deleteLocality(id);
    }

    @PutMapping(path = "{id}")
    public void updateLocality(@PathVariable("id") Integer id, @NotNull @RequestBody Locality localityToUpdate) {
        localityService.updateLocality(localityToUpdate);
    }

    @PostMapping(path = "addSport")
    public void addSport(@RequestHeader Integer idLocality,
                         @RequestHeader Integer idSport,
                         @RequestHeader String startPeriod,
                         @RequestHeader String endPeriod,
                         @RequestHeader Double averageCostPerDay) {
        localityService.addSport(idLocality, idSport, startPeriod, endPeriod, averageCostPerDay);
    }

    @PostMapping(path = "{idLocality}/{idSport}")
    public void updateCostLocality(@PathVariable("idLocality") Integer idLocality,
                                   @PathVariable("idSport")  Integer idSport,
                                   @NotNull @RequestBody Double averageCostPerDay) {
        localityService.updateCostLocality(idLocality, idSport, averageCostPerDay);
    }
}
