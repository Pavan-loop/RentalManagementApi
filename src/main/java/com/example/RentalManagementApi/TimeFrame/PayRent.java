package com.example.RentalManagementApi.TimeFrame;

import com.example.RentalManagementApi.tenants.Tenant;
import com.example.RentalManagementApi.tenants.TenantRepo;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@EnableScheduling
public class PayRent {

    public final TenantRepo tenantRepo;

    public PayRent(TenantRepo tenantRepo) {
        this.tenantRepo = tenantRepo;
    }

    @Scheduled(cron = "0 0 0 5 * ?")
    public void alert(){
        List<Tenant> tenants = tenantRepo.findAll();
        for (Tenant tenant: tenants){
            if (Objects.equals(tenant.getStatus(), "Paid")){
                tenant.setStatus("Un Paid");
                tenantRepo.save(tenant);
            }
        }
    }
}
