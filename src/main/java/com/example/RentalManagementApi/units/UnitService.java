package com.example.RentalManagementApi.units;

import java.util.List;

public interface UnitService {
    List<Unit> getAllUnit();
    boolean createUnit(Long propertyId, List<Unit> unit);
    Unit getUnitById(Long id);
    boolean updateById(Long id, Unit unit);
    boolean deleteById(Long id);
    List<Unit> findUnitByPropertyId(Long id);
    boolean deleteAll(Long propertyId);
    List<Status> getStatus(Long propertyId);
}
