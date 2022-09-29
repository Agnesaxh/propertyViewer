package com.propertyviewerproject.propertyviewer.service;

import com.propertyviewerproject.propertyviewer.dto.GeoLocationResponseData;
import com.propertyviewerproject.propertyviewer.entity.Building;
import com.propertyviewerproject.propertyviewer.entity.User;
import com.propertyviewerproject.propertyviewer.exception.NotFoundException;
import com.propertyviewerproject.propertyviewer.repository.BuildingRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {

    private BuildingRepository buildingRepository;
    private GeoLocationService geoLocationService;

    public BuildingService(
            BuildingRepository buildingRepository,
            GeoLocationService geoLocationService) {
        this.buildingRepository = buildingRepository;
        this.geoLocationService = geoLocationService;
    }

    public void saveBuilding(List<Building> buildings, User user) {
        for (Building building: buildings) {
            GeoLocationResponseData data = geoLocationService.getGeoLocationData(building);
            building.setLat(Double.parseDouble(data.getLat()));
            building.setLon(Double.parseDouble(data.getLon()));
            building.setUser(user);
            buildingRepository.save(building);
        }
    }

    public Building findBuildingById(Long id, User user) {
        return buildingRepository.findByIdAndUser(id, user).orElseThrow(NotFoundException::new);
    }

    public List<Building> findBuildings(User user, long page) {
        return buildingRepository.findByUserOrderByNameAsc(user, PageRequest.of((int) page, 10));
    }

    public void deleteBuilding(long id, User user) {
        buildingRepository.deleteByIdAndUser(id, user);
    }

    public void updateBuilding(Building building, User user) {
        Building existingBuilding = buildingRepository.findByIdAndUser(building.getId(), user).orElse(null);
        if (existingBuilding == null) {
            throw new NotFoundException();
        }
        existingBuilding.setName(building.getName());
        existingBuilding.setStreet(building.getStreet());
        existingBuilding.setPostalCode(building.getPostalCode());
        existingBuilding.setCity(building.getCity());
        existingBuilding.setCountry(building.getCountry());
        existingBuilding.setDescription(building.getDescription());
        existingBuilding.setUser(building.getUser());

        GeoLocationResponseData data = geoLocationService.getGeoLocationData(building);
        existingBuilding.setLat(Double.parseDouble(data.getLat()));
        existingBuilding.setLon(Double.parseDouble(data.getLon()));

        buildingRepository.save(existingBuilding);
    }
}
