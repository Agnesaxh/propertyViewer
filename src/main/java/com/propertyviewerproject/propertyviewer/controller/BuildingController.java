package com.propertyviewerproject.propertyviewer.controller;

import com.propertyviewerproject.propertyviewer.entity.Building;
import com.propertyviewerproject.propertyviewer.entity.User;
import com.propertyviewerproject.propertyviewer.service.BuildingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BuildingController {

    private BuildingService buildingService;

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @PostMapping("/buildings")
    public ResponseEntity<?> addBuilding(
            @RequestBody List<Building> building,
            Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        buildingService.saveBuilding(building, user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/buildings")
    public List<Building> findBuildings(
            @RequestParam("page") int page,
            Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return buildingService.findBuildings(user, page);
    }

    @GetMapping("/buildings/{id}")
    public Building findBuildingById(
            @PathVariable Long id,
            Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return buildingService.findBuildingById(id, user);
    }

    @DeleteMapping("/buildings/id")
    public void deleteBuilding(long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        buildingService.deleteBuilding(id, user);
    }

    @PutMapping("/buildings")
    public Building updateBuilding(
            Building building,
            Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        buildingService.updateBuilding(building, user);
        return building;
    }
}