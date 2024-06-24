package com.example.RentalManagementApi.properties;

import com.example.RentalManagementApi.properties.impl.PropertyServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/property")
@CrossOrigin(origins = "http://localhost:4200")
public class PropertyController {

    private final PropertyServiceImpl propertyService;

    public PropertyController(PropertyServiceImpl propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping
    public ResponseEntity<List<Property>> getAllProperty(){
        List<Property> properties = propertyService.getAllProperties();
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> addProperty(@RequestBody Property property){
        Long val = propertyService.addProperty(property);
        return new ResponseEntity<>(val, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getById(@PathVariable Long id){
        Property property = propertyService.getById(id);
        return new ResponseEntity<>(property, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProp(@PathVariable Long id){
        boolean delete = propertyService.deleteProp(id);
        if (delete)
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }



    @PutMapping("/{id}")
    public ResponseEntity<String> updateProp(@PathVariable Long id,
                                             @RequestBody Property property){
        boolean isUpdated = propertyService.UpdateProp(id,property);
        if (isUpdated)
            return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Not Found", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getPropertyCount(){
        int val = propertyService.getPropertyCount();
        return new ResponseEntity<>(val, HttpStatus.OK);
    }
}
