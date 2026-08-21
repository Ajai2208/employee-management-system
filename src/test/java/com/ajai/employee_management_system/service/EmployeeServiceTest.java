package com.ajai.employee_management_system.service;

import com.ajai.employee_management_system.dto.EmployeeResponseDTO;
import com.ajai.employee_management_system.entity.Employee;
import com.ajai.employee_management_system.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void shouldReturnEmployeeWhenIdExists() {

        // Arrange
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Ajai");
        employee.setLastName("Kumar");
        employee.setEmail("ajai@gmail.com");
        employee.setDepartment("IT");
        employee.setSalary(60000.0);
        employee.setJoiningDate(LocalDate.of(2024, 1, 10));
        employee.setStatus("ACTIVE");

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        // Act
        EmployeeResponseDTO result = employeeService.getEmployeeById(1L);

        // Assert
        assertEquals("Ajai", result.getFirstName());
        assertEquals("IT", result.getDepartment());
        assertEquals("ACTIVE", result.getStatus());
    }
}