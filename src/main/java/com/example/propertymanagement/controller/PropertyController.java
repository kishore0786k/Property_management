
package com.example.propertymanagement.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.propertymanagement.entity.Property;
import com.example.propertymanagement.service.PropertyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    // Create a property
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Property createProperty(@Valid @RequestBody Property property) {
        return propertyService.saveProperty(property);
    }

    // Get all properties
    @GetMapping
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    // Get property by ID
    @GetMapping("/{id}")
    public Property getPropertyById(@PathVariable Long id) {
        return propertyService.getPropertyById(id);
    }

    // Search properties by location
    @GetMapping("/search")
    public List<Property> searchByLocation(@RequestParam String location) {
        return propertyService.searchByLocation(location);
    }

    // Filter properties by type
    @GetMapping("/type")
    public List<Property> searchByType(@RequestParam String type) {
        return propertyService.searchByType(type);
    }

    // Filter properties by status
    @GetMapping("/status")
    public List<Property> searchByStatus(@RequestParam String status) {
        return propertyService.searchByStatus(status);
    }

    // Filter properties by price range
    @GetMapping("/price-range")
    public List<Property> searchByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        return propertyService.searchByPriceRange(minPrice, maxPrice);
    }

    // Update a property
    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(
            @PathVariable Long id,
            @Valid @RequestBody Property property) {

        Property updatedProperty =
                propertyService.updateProperty(id, property);

        return ResponseEntity.ok(updatedProperty);
    }

    // Delete a property
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }

    // Get properties with pagination and sorting
    @GetMapping("/page")
    public Page<Property> getPropertiesPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "price") String sortBy) {

        if (page < 0) {
            throw new IllegalArgumentException(
                    "Page number cannot be negative");
        }

        if (size < 1 || size > 100) {
            throw new IllegalArgumentException(
                    "Page size must be between 1 and 100");
        }

        List<String> allowedFields = List.of(
                "id", "name", "location", "price", "type", "status");

        if (!allowedFields.contains(sortBy)) {
            throw new IllegalArgumentException(
                    "Invalid sort field: " + sortBy);
        }

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortBy).ascending());

        return propertyService.getAllProperties(pageable);
    }
}
