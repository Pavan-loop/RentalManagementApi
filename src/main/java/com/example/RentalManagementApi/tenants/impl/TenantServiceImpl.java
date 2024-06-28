package com.example.RentalManagementApi.tenants.impl;

import com.example.RentalManagementApi.tenants.Tenant;
import com.example.RentalManagementApi.tenants.TenantRepo;
import com.example.RentalManagementApi.tenants.TenantService;
import com.example.RentalManagementApi.units.Unit;
import com.example.RentalManagementApi.units.UnitService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TenantServiceImpl implements TenantService {
    private final TenantRepo tenantRepo;
    private UnitService unitService;

    public TenantServiceImpl(TenantRepo tenantRepo, UnitService unitService) {
        this.tenantRepo = tenantRepo;
        this.unitService = unitService;
    }

    @Override
    public List<Tenant> getAllTenant() {
        return tenantRepo.findAll();
    }

    @Override
    public boolean addTenant(Long id, Tenant tenant) {
        Unit unit = unitService.getUnitById(id);
        if (unit != null){
            tenant.setUnit(unit);
            tenantRepo.save(tenant);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteTenant(Long id) {
        try{
            tenantRepo.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public int getCount() {
        return tenantRepo.getCount();
    }

    @Override
    public void deletePTenant(Long id) {
        tenantRepo.deletePTenant(id);
    }

    @Override
    public boolean updateStatus(Long id) {
        Optional<Tenant> optionalTenant = tenantRepo.findById(id);
        if (optionalTenant.isPresent()){
            Tenant tenant1 = optionalTenant.get();
            tenant1.setStatus("Paid");
            tenantRepo.save(tenant1);
            return true;
        }
        return false;
    }

}
