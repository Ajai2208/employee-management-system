package com.ajai.employee_management_system.service;

import com.ajai.employee_management_system.dto.EmployeeRequestDTO;
import com.ajai.employee_management_system.dto.EmployeeResponseDTO;
import com.ajai.employee_management_system.entity.Employee;
import com.ajai.employee_management_system.mapper.EmployeeMapper;
import com.ajai.employee_management_system.repository.EmployeeRepository;
import com.ajai.employee_management_system.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Get all employees
    public List<EmployeeResponseDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeMapper::toResponse)
                .collect(Collectors.toList());
    }

    // Get employee by ID
    public EmployeeResponseDTO getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
        .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
        return EmployeeMapper.toResponse(employee);
    }

    // Create employee
    public EmployeeResponseDTO saveEmployee(EmployeeRequestDTO requestDTO) {

        Employee employee = EmployeeMapper.toEntity(requestDTO);

        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.toResponse(savedEmployee);
    }

    // Update employee
    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO requestDTO) {

        Employee employee = employeeRepository.findById(id)
        .orElseThrow(() ->
                new EmployeeNotFoundException("Employee with ID " + id + " not found"));

        employee.setFirstName(requestDTO.getFirstName());
        employee.setLastName(requestDTO.getLastName());
        employee.setEmail(requestDTO.getEmail());
        employee.setDepartment(requestDTO.getDepartment());
        employee.setSalary(requestDTO.getSalary());
        employee.setJoiningDate(requestDTO.getJoiningDate());
        employee.setStatus(requestDTO.getStatus());

        Employee updatedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.toResponse(updatedEmployee);
    }

    // Delete employee
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));

        employeeRepository.delete(employee);
    }
}