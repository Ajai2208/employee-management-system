package com.ajai.employee_management_system.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private Double salary;
    private LocalDate joiningDate;
    private String status;
}