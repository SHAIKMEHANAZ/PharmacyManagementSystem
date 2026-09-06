package com.pharmacy;

import com.pharmacy.entity.Medicine;
import com.pharmacy.entity.SaleRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    public List<SaleRecord> getAllSales() {
        return saleRepository.findAll();
    }

    public SaleRecord recordASale(Long medicineId, Integer quantityToSell) {
        // FIXED TYPO HERE: Changed orElsethrow to orElseThrow
        Medicine med = medicineRepository.findById(medicineId)
                .orElseThrow(() -> new IllegalArgumentException("Medicine record not found"));

        if (med.getQuantity() < quantityToSell) {
            throw new IllegalStateException("Insufficient stock available for order processing.");
        }

        // Deduct inventory balance count
        med.setQuantity(med.getQuantity() - quantityToSell);
        medicineRepository.save(med);

        // Save transactional ledger track
        double totalPrice = med.getPrice() * quantityToSell;
        SaleRecord record = new SaleRecord(med.getName(), quantityToSell, totalPrice, LocalDateTime.now());
        
        return saleRepository.save(record);
    }
}