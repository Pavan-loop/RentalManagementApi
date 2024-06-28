package com.example.RentalManagementApi.tenants;

import java.util.List;

public interface TenantService {
    List<Tenant> getAllTenant();
    boolean addTenant(Long id, Tenant tenant);
    boolean deleteTenant(Long id);
    int getCount();
    void deletePTenant(Long id);
    boolean updateStatus(Long id);
}
