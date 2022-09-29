package com.propertyviewerproject.propertyviewer.controller;

import com.propertyviewerproject.propertyviewer.entity.Building;
import com.propertyviewerproject.propertyviewer.service.BuildingService;
import org.springframework.web.bind.annotation.*;

@RestController
public class BuildingController {

    private BuildingService buildingService;

    @PostMapping("/addBuilding")
    public Building addBuilding(@RequestBody Building building) {
        return buildingService.saveBuilding(building);
    }

    @GetMapping("/findBuildingById/{id}")
    public Building findBuildingById(@PathVariable Long id) {
        return buildingService.findBuildingById(id);
    }

    @DeleteMapping("/deleteBuilding")
    public void deleteBuilding(Building building) {
        buildingService.deleteBuilding(building);
    }

    @PutMapping("/updateBuilding")
    public Building updateBuilding(Building building) {
        return buildingService.updateBuiding(building);
    }
}