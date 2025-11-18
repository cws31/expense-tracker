package com.expense_tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.expense_tracker.DTOs.ExpenseRequest;
import com.expense_tracker.DTOs.ExpenseResponse;
import com.expense_tracker.services.ExpenseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/add")
    public ResponseEntity<ExpenseResponse> saveExpense(@Valid @RequestBody ExpenseRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        ExpenseResponse response = expenseService.saveExpense(request, email);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> getAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        List<ExpenseResponse> res = expenseService.getAllExpenses(email);
        return ResponseEntity.ok(res);
    }
}
