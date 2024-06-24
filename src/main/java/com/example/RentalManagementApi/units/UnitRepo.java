package com.example.RentalManagementApi.units;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UnitRepo extends JpaRepository<Unit,Long> {
    List<Unit> findByPropertyId(Long id);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM unit WHERE property_id=:id",
            nativeQuery = true
    )
    void deleteAll(@Param("id") Long id);

    @Query(
            value = "SELECT u.id AS unitId, u.unit_no AS UnitNo, u.unit_type AS unitType, u.property_id AS propertyId, " +
                    "CASE WHEN t.id IS NOT NULL THEN 'Occupied' ELSE 'Vacant' END AS status " +
                    "FROM unit u " +
                    "LEFT JOIN tenant t ON u.id = t.unit_id " +
                    "WHERE u.property_id = :id " +
                    "ORDER BY u.unit_no",
            nativeQuery = true
    )
    List<Status> getStatus(@Param("id") Long id);

}
