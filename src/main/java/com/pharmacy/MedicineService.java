package com.pharmacy;

import com.pharmacy.entity.Medicine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository repository;

    public List<Medicine> getAllMedicines() { return repository.findAll(); }
    public List<Medicine> searchMedicinesByName(String name) { return repository.findByNameContainingIgnoreCase(name); }
    public Medicine getMedicineById(Long id) { return repository.findById(id).orElse(null); }
    public Medicine saveOrUpdateMedicine(Medicine medicine) { return repository.save(medicine); }
    public void deleteMedicine(Long id) { repository.deleteById(id); }
}