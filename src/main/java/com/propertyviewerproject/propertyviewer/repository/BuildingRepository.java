package com.propertyviewerproject.propertyviewer.repository;

import com.propertyviewerproject.propertyviewer.entity.Building;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface BuildingRepository extends PagingAndSortingRepository<Building, Long> {
}
