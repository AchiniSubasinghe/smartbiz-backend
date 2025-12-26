package com.smartbiz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "owner")
@Getter
@Setter
@NoArgsConstructor
public class Owner {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false, length = 50)
    private String ownerName;
    @Column(nullable = false, length = 100, unique = true)
    private String ownerEmail;
    @Column(nullable = false, length = 100)
    private String ownerPassword;
    @Column(nullable = false, length = 10)
    private String ownerContactNo;
    @ManyToOne
    @JoinColumn(name = "subscription_plan_id", nullable = false)
    private SubscriptionPlan subscriptionPlan;
    @OneToMany(mappedBy = "owner")
    private List<Business> businesses;

}
