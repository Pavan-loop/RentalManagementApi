package com.example.RentalManagementApi.properties;

import java.util.List;

public interface PropertyService {

    List<Property> getAllProperties();
    Long addProperty(Property property);
    Property getById(Long id);
    boolean deleteProp(Long id);
    boolean UpdateProp(Long id, Property property);
    int getPropertyCount();
}
