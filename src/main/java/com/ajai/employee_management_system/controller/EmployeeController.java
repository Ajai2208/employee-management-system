package com.ajai.employee_management_system.controller;

import org.springframework.data.domain.Page;
import com.ajai.employee_management_system.dto.EmployeeRequestDTO;
import com.ajai.employee_management_system.dto.EmployeeResponseDTO;
import com.ajai.employee_management_system.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(
    name = "Employee Management",
    description = "REST APIs for managing employees"
)
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // GET all employees
    @Operation(summary = "Get all employees")
    @GetMapping
    public org.springframework.data.domain.Page<EmployeeResponseDTO> getAllEmployees(

        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {

        return employeeService.getAllEmployees(page, size, sortBy, direction);
    }

    // GET employee by ID
    @Operation(summary = "Get employee by ID")
    @GetMapping("/{id}")
    public EmployeeResponseDTO getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    // GET employees by department
    @Operation(summary = "Get employees by department")
    @GetMapping("/department/{department}")
    public List<EmployeeResponseDTO> getEmployeesByDepartment(
        @PathVariable String department) {

    return employeeService.getEmployeesByDepartment(department);
    }

// GET employee by email
    @Operation(summary = "Get employee by email")
    @GetMapping("/email/{email}")
    public EmployeeResponseDTO getEmployeeByEmail(
        @PathVariable String email) {

    return employeeService.getEmployeeByEmail(email);
    }

// GET employees by status
    @Operation(summary = "Get employees by status")
    @GetMapping("/status/{status}")
    public List<EmployeeResponseDTO> getEmployeesByStatus(
        @PathVariable String status) {

    return employeeService.getEmployeesByStatus(status);
    }

    @Operation(summary = "Search employees by first name")
    @GetMapping("/search")
    public List<EmployeeResponseDTO> searchEmployees(
        @RequestParam String keyword) {

        return employeeService.searchEmployees(keyword);
    }

    // CREATE employee
    @Operation(summary = "Create a new employee")
    @PostMapping
    public EmployeeResponseDTO createEmployee(
            @Valid @RequestBody EmployeeRequestDTO requestDTO) {

        return employeeService.saveEmployee(requestDTO);
    }

    // UPDATE employee
    @Operation(summary = "Update an existing employee")
    @PutMapping("/{id}")
    public EmployeeResponseDTO updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequestDTO requestDTO) {

        return employeeService.updateEmployee(id, requestDTO);
    }

    // DELETE employee
    @Operation(summary = "Delete an employee")
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return "Employee deleted successfully.";
    }
}