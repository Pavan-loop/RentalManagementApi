package com.example.RentalManagementApi.tenants;

import com.example.RentalManagementApi.tenants.impl.TenantServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tenant")
@CrossOrigin("*")
public class TenantController {
    private final TenantServiceImpl tenantService;

    public TenantController(TenantServiceImpl tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping
    public ResponseEntity<List<Tenant>> getAllTenants(){
        List<Tenant> tenants = tenantService.getAllTenant();
        return new ResponseEntity<>(tenants, HttpStatus.OK);
    }

    @PostMapping("/unit/{id}")
    public ResponseEntity<String> addTenants(@PathVariable Long id,
                                             @RequestBody Tenant tenant){
        boolean isAdded = tenantService.addTenant(id,tenant);
        if (isAdded)
            return new ResponseEntity<>("Added Successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Problema", HttpStatus.NOT_ACCEPTABLE);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTenant(@PathVariable Long id){
        boolean isDeleted = tenantService.deleteTenant(id);
        if (isDeleted)
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Unsuccessfull", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getCount(){
        int num = tenantService.getCount();
        return new ResponseEntity<>(num, HttpStatus.OK);
    }

    @DeleteMapping("/property/{id}")
    public ResponseEntity<String> deletePTenant(@PathVariable Long id){
        tenantService.deletePTenant(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
