package com.propertyviewerproject.propertyviewer.repository;

import com.propertyviewerproject.propertyviewer.entity.Building;
import com.propertyviewerproject.propertyviewer.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface BuildingRepository extends PagingAndSortingRepository<Building, Long> {

    List<Building> findByUserOrderByNameAsc(User user, Pageable pageable);

    Optional<Building> findByIdAndUser(Long id, User user);

    void deleteByIdAndUser(Long id, User user);
}
