package com.smartbiz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "subscription_plan")
@Getter @Setter @NoArgsConstructor
public class SubscriptionPlan {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "subscription_name", nullable = false, length = 50)
    private String subscriptionName;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subscriptionPrice;
    // @OneToMany(mappedBy = "subscriptionPlan")
    // private List<Owner> owners;
    // @OneToMany(mappedBy = "subscriptionPlan")
    // private List<Business> businesses;
}
