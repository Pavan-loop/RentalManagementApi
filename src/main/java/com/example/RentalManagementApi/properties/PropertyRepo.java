package com.example.RentalManagementApi.properties;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PropertyRepo extends JpaRepository<Property, Long> {
    @Query(
            value = "SELECT COUNT(*) FROM property",
            nativeQuery = true
    )
    public int getPropertyCount();


}
