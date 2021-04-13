package com.atta.demo.service;

import com.atta.demo.models.Sport;
import com.atta.demo.repositories.SportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SportService {

    private final SportRepo sportRepo;

    @Autowired
    public SportService(SportRepo sportRepo) {
        this.sportRepo = sportRepo;
    }

    public Sport addSport(Sport sport) {
        if (!sportRepo.existsSportByName(sport.getName())) {
            return sportRepo.save(sport);
        }
        return null;
    }

    public List<Sport> getAllSports() {
        return (List<Sport>) sportRepo.findAll();
    }

    public Optional<Sport> getSportById(Integer id) {
        return sportRepo.findById(id);
    }

    public void deleteSport(Integer id) {
        sportRepo.deleteById(id);
    }

    public void updateSport(Integer id, String name) {
        sportRepo.updateSport(id, name);
    }

}
