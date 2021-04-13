package com.atta.demo.api;

import com.atta.demo.models.Region;
import com.atta.demo.service.RegionService;
import com.sun.istack.NotNull;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/region")
@RestController
public class RegionController {

    private final RegionService regionService;

    @Autowired
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @PostMapping
    public Region addRegion(@NotNull @RequestBody Region region,
                            @RequestHeader Integer countryId) throws NotFoundException {
        return regionService.addRegion(region, countryId);
    }

    @GetMapping
    public List<Region> getAllRegions() {
        return regionService.getAllRegions();
    }

    @GetMapping(path = "{id}")
    public Region getRegionById(@PathVariable("id") Integer id) {
        return regionService.getRegionById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteRegionById(@PathVariable("id") Integer id) {
        regionService.deleteRegion(id);
    }

    @PutMapping(path = "{id}")
    public void updateRegion(@PathVariable("id") Integer id, @NotNull @RequestBody Region regionToUpdate) {
        regionService.updateRegion(id, regionToUpdate.getName());
    }
}
