package com.propertyviewerproject.propertyviewer.repository;

import com.propertyviewerproject.propertyviewer.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User,Long> {

    Optional<User> findByEmail(String email);
}
