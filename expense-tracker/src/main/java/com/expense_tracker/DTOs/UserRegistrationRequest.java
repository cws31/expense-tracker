package com.expense_tracker.DTOs;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequest {

    @NotBlank(message = "full Name is required")
    private String fullName;
    @NotBlank(message = "user name is required")
    @Size(min = 3, message = "user should be minimum of 3 characters")
    @Column(unique = true)
    private String email;
    @NotBlank(message = "password is required")
    @Size(min = 8, message = "password will be minimum of 8 character")
    private String password;

    @NotNull(message = "monthly budget is essential")
    @Positive(message = "montlybudget should be positive")
    private Double monthlyBudget;

}
