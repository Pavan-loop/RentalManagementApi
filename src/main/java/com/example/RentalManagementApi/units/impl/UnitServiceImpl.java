package com.example.RentalManagementApi.units.impl;

import com.example.RentalManagementApi.properties.Property;
import com.example.RentalManagementApi.properties.PropertyService;
import com.example.RentalManagementApi.units.Status;
import com.example.RentalManagementApi.units.Unit;
import com.example.RentalManagementApi.units.UnitRepo;
import com.example.RentalManagementApi.units.UnitService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitServiceImpl implements UnitService {

    private final UnitRepo unitRepo;
    private final PropertyService propertyService;

    public UnitServiceImpl(UnitRepo unitRepo, PropertyService propertyService) {
        this.unitRepo = unitRepo;
        this.propertyService = propertyService;
    }

    @Override
    public List<Unit> getAllUnit() {
        return unitRepo.findAll();
    }

    @Override
    public boolean createUnit(Long propertyId, List<Unit> unit) {
        Property property = propertyService.getById(propertyId);
        if (property != null){
            for (Unit unit1 : unit){
                unit1.setProperty(property);
                unitRepo.save(unit1);
            }
            return true;
        }
        return false;
    }


    @Override
    public Unit getUnitById(Long id) {
        return unitRepo.findById(id).orElse(null);
    }

    @Override
    public boolean updateById(Long id, Unit unit) {
        Optional<Unit> optionalUnit = unitRepo.findById(id);
        if (optionalUnit.isPresent()){
            Unit unit1 = optionalUnit.get();
            unit1.setUnitNo(unit.getUnitNo());
            unit1.setUnitType(unit.getUnitType());
            unit1.setAmount(unit.getAmount());
            unit1.setProperty(unit.getProperty());
            unitRepo.save(unit1);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            unitRepo.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Unit> findUnitByPropertyId(Long id) {
        return unitRepo.findByPropertyId(id);
    }

    @Override
    public boolean deleteAll(Long propertyId) {
        try {
            unitRepo.deleteAll(propertyId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Status> getStatus(Long propertyId) {
        return unitRepo.getStatus(propertyId);
    }

    @Override
    public String getRentAmount(Long id) {
        return unitRepo.getRentAmount(id);
    }
}
