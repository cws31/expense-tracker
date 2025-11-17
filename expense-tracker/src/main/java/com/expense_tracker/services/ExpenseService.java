package com.expense_tracker.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense_tracker.DTOs.ExpenseRequest;
import com.expense_tracker.DTOs.ExpenseResponse;
import com.expense_tracker.entities.Expense;
import com.expense_tracker.entities.User;
import com.expense_tracker.repositories.ExpenseRepository;
import com.expense_tracker.repositories.UserRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    public ExpenseResponse saveExpense(ExpenseRequest expenseRequest, String email) {

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new RuntimeException("User not found with email: " + email);
        }

        Expense exp = new Expense();
        exp.setDate(expenseRequest.getDate());
        exp.setAmount(expenseRequest.getAmount());
        exp.setPaymentmethod(expenseRequest.getPaymentMethod());
        exp.setNote(expenseRequest.getNote());
        exp.setOwner(user.get());

        Expense saved = expenseRepository.save(exp);

        return new ExpenseResponse(
                saved.getDate(),
                saved.getAmount(),
                saved.getPaymentmethod(),
                saved.getNote(),
                saved.getOwner().getFullName());
    }

}
