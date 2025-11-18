package com.expense_tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.expense_tracker.entities.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByOwnerEmail(String email);

}
