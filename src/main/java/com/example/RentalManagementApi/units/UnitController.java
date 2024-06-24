package com.example.RentalManagementApi.units;

import com.example.RentalManagementApi.units.impl.UnitServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("unit")
@CrossOrigin("*")
public class UnitController {
    private final UnitServiceImpl unitService;

    public UnitController(UnitServiceImpl unitService) {
        this.unitService = unitService;
    }

    @GetMapping
    public ResponseEntity<List<Unit>> getAllUnit(){
        List<Unit> units = unitService.getAllUnit();
        return new ResponseEntity<>(units, HttpStatus.OK);
    }

    @PostMapping("/property/{propertyId}")
    public ResponseEntity<String> createUnit(@PathVariable Long propertyId,
                                             @RequestBody List<Unit> unit){
        boolean isAdded = unitService.createUnit(propertyId,unit);
        if (isAdded)
            return new ResponseEntity<>("Inserted Successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Problema", HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("{id}")
    public ResponseEntity<Unit> getUnitById(@PathVariable Long id){
        Unit unit = unitService.getUnitById(id);
        return new ResponseEntity<>(unit, HttpStatus.FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateUnit(@PathVariable Long id,
                                             @RequestBody Unit unit){
        boolean isUpdated = unitService.updateById(id,unit);
        if (isUpdated)
            return new ResponseEntity<>("Updated Successfully", HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>("Not Present", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUnit(@PathVariable Long id){
        boolean isDeleted = unitService.deleteById(id);
        if (isDeleted)
            return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/all/{id}")
    public ResponseEntity<String> deleteAll(@PathVariable Long id){
        boolean isDeleted = unitService.deleteAll(id);
        if (isDeleted)
            return new ResponseEntity<>("SuccessFully Deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>("Problem", HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/property/{id}")
    public ResponseEntity<List<Unit>> getSpecificProperty(@PathVariable Long id){
        List<Unit> units = unitService.findUnitByPropertyId(id);
        return new ResponseEntity<>(units, HttpStatus.OK);
    }

    @GetMapping("/status/property/{id}")
    public ResponseEntity<List<Status>> getStatus(@PathVariable Long id){
        List<Status> status = unitService.getStatus(id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
