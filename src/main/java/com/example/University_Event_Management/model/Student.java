package com.example.University_Event_Management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;

    @NotBlank(message = "First name cannot be blank")
    @Pattern(regexp = "^[A-Z][a-z]*", message = "First name should start with an uppercase letter")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Pattern(regexp = "^[A-Z][a-z]*", message = "Last name should start with an uppercase letter")
    private String lastName;

    @NotNull(message = "Age cannot be null")
    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 25, message = "Age should not be greater than 25")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Department cannot be null")
    private Department department;
}