package com.smartbiz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "business")
@Getter
@Setter
@NoArgsConstructor
public class Business {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false, length = 50)
    private String businessName;
    @Column(nullable = false, length = 50)
    private String businessCategory;
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;
    @ManyToOne
    @JoinColumn(name = "subscription_id", nullable = false)
    private SubscriptionPlan subscriptionPlan;
    @OneToMany(mappedBy = "business")
    private List<Customer> customers;
    @OneToMany(mappedBy = "business")
    private List<Income> incomes;
    @OneToMany(mappedBy = "business")
    private List<Expense> expenses;
    @OneToMany(mappedBy = "business")
    private List<Product> products;
    @OneToMany(mappedBy = "business")
    private List<Supplier> suppliers;
    @OneToMany(mappedBy = "business")
    private List<Order> orders;

}
