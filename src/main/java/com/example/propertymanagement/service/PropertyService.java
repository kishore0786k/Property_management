package com.example.propertymanagement.service;
import org.springframework.stereotype.*;
import java.util.*;
import com.example.propertymanagement.entity.*;
import com.example.propertymanagement.entity.Property;
import com.example.propertymanagement.exception.PropertyNotFoundException;
import com.example.propertymanagement.repository.PropertyRepository;
import com.example.propertymanagement.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Service
public class PropertyService{ 
	private final PropertyRepository PR;

	public PropertyService(PropertyRepository PR) {
		super();
		this.PR = PR;
	}
	public Property saveProperty(Property property) {
	    return PR.save(property);
	}
	public List<Property> getAllProperties() {
	    return PR.findAll();
	}
	public Page<Property> getAllProperties(Pageable pageable) {
	    return PR.findAll(pageable);
	}
	public List<Property> searchByLocation(String location) {
	    return PR.findByLocationContainingIgnoreCase(location);
	}

public List<Property> searchByType(String type) {
    return PR.findByTypeIgnoreCase(type);
}

public List<Property> searchByStatus(String status) {
    return PR.findByStatusIgnoreCase(status);
}

public List<Property> searchByPriceRange(Double minPrice, Double maxPrice) {
    return PR.findByPriceBetween(minPrice, maxPrice);
}

	public Property getPropertyById(Long id) {
	    return PR.findById(id).orElseThrow(() ->
        new PropertyNotFoundException("Property not found with ID: " + id));
	}
	public Property updatePropertyPrice(Long id, Double price) {
	    Property property = PR.findById(id).orElseThrow(() ->
        new PropertyNotFoundException("Property not found with ID: " + id));

	    if (property != null) {
	        property.setPrice(price);
	        return PR.save(property);
	    }

	    return null;
	}

public void deleteProperty(Long id) {
    Property property = PR.findById(id)
        .orElseThrow(() ->
            new PropertyNotFoundException(
                "Property not found with ID: " + id
            ));

    PR.delete(property);
}
	public Property updateProperty(Long id, Property updatedProperty) {
	    Property property = PR.findById(id).orElseThrow(() -> 
        new PropertyNotFoundException("Property not found with ID: " + id));

	    if (property != null) {
	        property.setName(updatedProperty.getName());
	        property.setLocation(updatedProperty.getLocation());
	        property.setPrice(updatedProperty.getPrice());
	        property.setType(updatedProperty.getType());
	        property.setStatus(updatedProperty.getStatus());

	        return PR.save(property);
	    }
	    return null;
	}
}

