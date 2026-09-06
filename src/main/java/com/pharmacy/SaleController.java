package com.pharmacy;

import com.pharmacy.entity.SaleRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping
    public List<SaleRecord> getAllSalesLogs() {
        return saleService.getAllSales();
    }

    @PostMapping
    public ResponseEntity<?> processSale(@RequestBody Map<String, Object> payload) {
        try {
            Long medicineId = Long.valueOf(payload.get("medicineId").toString());
            Integer quantity = Integer.valueOf(payload.get("quantity").toString());
            
            SaleRecord transaction = saleService.recordASale(medicineId, quantity);
            return ResponseEntity.ok(transaction);
        } catch (Exception err) {
            return ResponseEntity.badRequest().body(err.getMessage());
        }
    }
}