package com.expense_tracker.DTOs;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseRequest {

    @NotNull(message = "date should not be null")
    private LocalDate date;
    @NotNull(message = "amount is required")
    private Double amount;
    @NotBlank(message = "payment method is required")
    private String paymentMethod;
    @NotBlank(message = "note is required")
    @Positive(message = "expense amount should be positivve")
    @Column(length = 1000)
    private String note;

}
