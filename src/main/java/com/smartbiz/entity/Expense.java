package com.smartbiz.entity;

import com.smartbiz.enums.ExpenseType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter@Setter@NoArgsConstructor@AllArgsConstructor@Builder
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private double amount;
    @Column(nullable = false)
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExpenseType type;
}
