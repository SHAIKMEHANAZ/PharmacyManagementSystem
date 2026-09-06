package com.pharmacy;

import com.pharmacy.entity.Medicine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MedicineController {

    @Autowired
    private MedicineService service;

    @GetMapping
    public List<Medicine> getAllMedicines(@RequestParam(required = false) String search) {
        if (search != null && !search.trim().isEmpty()) {
            return service.searchMedicinesByName(search);
        }
        return service.getAllMedicines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable Long id) {
        Medicine med = service.getMedicineById(id);
        return med != null ? ResponseEntity.ok(med) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Medicine addMedicine(@RequestBody Medicine medicine) {
        return service.saveOrUpdateMedicine(medicine);
    }

    // NEW EXPLICIT UPDATE ROUTE FOR ID, NAME, AND PRICE
    @PutMapping("/{id}/update-basic")
    public ResponseEntity<Medicine> updateMedicineNameAndPrice(@PathVariable Long id, @RequestBody Medicine details) {
        Medicine existing = service.getMedicineById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        
        // Update only the requested name and price fields
        existing.setName(details.getName());
        existing.setPrice(details.getPrice());
        
        Medicine saved = service.saveOrUpdateMedicine(existing);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable Long id, @RequestBody Medicine details) {
        Medicine existing = service.getMedicineById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        
        existing.setName(details.getName());
        existing.setQuantity(details.getQuantity());
        existing.setPrice(details.getPrice());
        existing.setExpiryDate(details.getExpiryDate());
        
        return ResponseEntity.ok(service.saveOrUpdateMedicine(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable Long id) {
        service.deleteMedicine(id);
        return ResponseEntity.noContent().build();
    }
}