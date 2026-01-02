package com.smartbiz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    @JoinColumn(name = "subscription_id", nullable = false)
    private SubscriptionPlan subscriptionPlan;
   @ManyToMany(mappedBy = "owners")
    public Set<Owner> owners = new HashSet<>();

}
