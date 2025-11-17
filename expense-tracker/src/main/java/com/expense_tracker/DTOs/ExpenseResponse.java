package com.expense_tracker.DTOs;

import java.time.LocalDate;

import com.expense_tracker.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseResponse {

    private LocalDate date;
    private Double amount;
    private String paymentMethod;
    private String note;
    private String owner;

}
