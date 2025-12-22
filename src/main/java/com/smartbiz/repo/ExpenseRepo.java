package com.smartbiz.repo;

import com.smartbiz.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExpenseRepo extends JpaRepository<Expense, UUID> {

}
