package com.example.RentalManagementApi.properties.impl;

import com.example.RentalManagementApi.properties.Property;
import com.example.RentalManagementApi.properties.PropertyRepo;
import com.example.RentalManagementApi.properties.PropertyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

   private final PropertyRepo propertyRepo;

    public PropertyServiceImpl(PropertyRepo propertyRepo) {
        this.propertyRepo = propertyRepo;
    }

    @Override
    public List<Property> getAllProperties() {
        return propertyRepo.findAll();
    }

    @Override
    public Long addProperty(Property property) {
        propertyRepo.save(property);
        return property.getId();
    }

    @Override
    public Property getById(Long id) {
        return propertyRepo.findById(id).orElse(null);
    }

    @Override
    public boolean deleteProp(Long id) {
        try {
            propertyRepo.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean UpdateProp(Long id, Property property) {
        Optional<Property> optionalProperty = propertyRepo.findById(id);
        if(optionalProperty.isPresent()){
            Property property1 = optionalProperty.get();
            property1.setName(property.getName());
            property1.setAddress(property.getAddress());
            propertyRepo.save(property1);
            return true;
        }
        return false;
    }

    @Override
    public int getPropertyCount() {
        return propertyRepo.getPropertyCount();
    }
}
