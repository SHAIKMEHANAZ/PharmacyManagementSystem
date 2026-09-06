package com.pharmacy;

import com.pharmacy.entity.SaleRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<SaleRecord, Long> {
}