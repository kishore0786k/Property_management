package com.example.propertymanagement.repository;
import com.example.propertymanagement.entity.*;
import com.example.propertymanagement.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.*;

import org.hibernate.query.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property,Long> {
	List<Property> findByLocationContainingIgnoreCase(String location);
    List<Property> findByTypeIgnoreCase(String type);
    List<Property> findByStatusIgnoreCase(String status);
    List<Property> findByPriceBetween(Double minPrice, Double maxPrice);
    Page<Property> findAll(Pageable pageable);
}
