package com.pharmacy.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sales_records")
public class SaleRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String medicineName;
    private Integer quantitySold;
    private Double totalAmount;
    private LocalDateTime saleTimestamp;

    public SaleRecord() {}

    public SaleRecord(String medicineName, Integer quantitySold, Double totalAmount, LocalDateTime saleTimestamp) {
        this.medicineName = medicineName;
        this.quantitySold = quantitySold;
        this.totalAmount = totalAmount;
        this.saleTimestamp = saleTimestamp;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMedicineName() { return medicineName; }
    public void setMedicineName(String medicineName) { this.medicineName = medicineName; }
    public Integer getQuantitySold() { return quantitySold; }
    public void setQuantitySold(Integer quantitySold) { this.quantitySold = quantitySold; }
    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    public LocalDateTime getSaleTimestamp() { return saleTimestamp; }
    public void setSaleTimestamp(LocalDateTime saleTimestamp) { this.saleTimestamp = saleTimestamp; }
}