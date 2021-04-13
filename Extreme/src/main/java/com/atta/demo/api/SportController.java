package com.atta.demo.api;

import com.atta.demo.models.Sport;
import com.atta.demo.service.SportService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/sport")
@RestController
public class SportController {
    private final SportService sportService;

    @Autowired
    public SportController(SportService sportService) {
        this.sportService = sportService;
    }

    @PostMapping
    public Sport addSport(@NotNull @RequestBody Sport sport) {
        return sportService.addSport(sport);
    }

    @GetMapping
    public List<Sport> getAllSports() {
        return sportService.getAllSports();
    }

    @GetMapping(path = "{id}")
    public Sport getSportById(@PathVariable("id") Integer id) {
        return sportService.getSportById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteSportById(@PathVariable("id") Integer id) {
        sportService.deleteSport(id);
    }

    @PutMapping(path = "{id}")
    public void updateSport(@PathVariable("id") Integer id, @NotNull @RequestBody Sport sportToUpdate) {
        sportService.updateSport(id, sportToUpdate.getName());
    }
}
