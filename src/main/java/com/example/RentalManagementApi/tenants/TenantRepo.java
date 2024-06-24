package com.example.RentalManagementApi.tenants;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TenantRepo extends JpaRepository<Tenant,Long> {
    @Query(
            value = "SELECT COUNT(*) FROM tenant",
            nativeQuery = true
    )
    int getCount();

    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM tenant WHERE unit_id IN (SELECT id FROM unit WHERE property_id=:id)",
            nativeQuery = true
    )
    void deletePTenant(@Param("id") Long id);
}
