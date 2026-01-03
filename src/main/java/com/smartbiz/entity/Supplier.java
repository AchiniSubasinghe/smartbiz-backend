package com.smartbiz.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "supplier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String supplierName;

    @Column(length = 10)
    private String supplierContactNo;

    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;
}
