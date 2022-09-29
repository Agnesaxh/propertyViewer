package com.propertyviewerproject.propertyviewer.service;

import com.propertyviewerproject.propertyviewer.entity.Building;
import com.propertyviewerproject.propertyviewer.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    public Building saveBuilding(Building building){
        return buildingRepository.save(building);
    }

    public Building findBuildingById(Long id){
        return buildingRepository.findById(id).orElse(null);
    }

    public void deleteBuilding(Building building){
        buildingRepository.delete(building);
        System.out.println("Building deleted!");
    }

    public Building updateBuiding(Building building){
        Building existingBuilding = buildingRepository.findById(building.getId()).orElse(null);
        existingBuilding.setName(existingBuilding.getName());
        existingBuilding.setStreet(existingBuilding.getStreet());
        existingBuilding.setPostalCode(existingBuilding.getPostalCode());
        existingBuilding.setCity(existingBuilding.getCity());
        existingBuilding.setCountry(existingBuilding.getCountry());
        existingBuilding.setDescription(existingBuilding.getDescription());
        existingBuilding.setUser(existingBuilding.getUser());
        //add lon and lat

        return buildingRepository.save(existingBuilding);
    }
}
