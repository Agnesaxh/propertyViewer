package com.propertyviewerproject.propertyviewer.repository;

import com.propertyviewerproject.propertyviewer.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User,Long> {
}
