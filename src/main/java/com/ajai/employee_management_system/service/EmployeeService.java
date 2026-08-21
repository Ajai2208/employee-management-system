package com.ajai.employee_management_system.service;

import com.ajai.employee_management_system.dto.EmployeeRequestDTO;
import com.ajai.employee_management_system.dto.EmployeeResponseDTO;
import com.ajai.employee_management_system.entity.Employee;
import com.ajai.employee_management_system.exception.EmployeeNotFoundException;
import com.ajai.employee_management_system.mapper.EmployeeMapper;
import com.ajai.employee_management_system.repository.EmployeeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private static final Logger logger =
            LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Get all employees
    public Page<EmployeeResponseDTO> getAllEmployees(
            int page,
            int size,
            String sortBy,
            String direction) {

        logger.info("Fetching employees - page {}, size {}", page, size);

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return employeeRepository.findAll(pageable)
                .map(EmployeeMapper::toResponse);
    }

    // Get employee by ID
    public EmployeeResponseDTO getEmployeeById(Long id) {

        logger.info("Fetching employee with ID {}", id);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee with ID " + id + " not found"));

        return EmployeeMapper.toResponse(employee);
    }

    // Get employees by department
    public List<EmployeeResponseDTO> getEmployeesByDepartment(String department) {

        logger.info("Fetching employees from department: {}", department);

        return employeeRepository.findByDepartment(department)
                .stream()
                .map(EmployeeMapper::toResponse)
                .collect(Collectors.toList());
    }

    // Get employee by email
    public EmployeeResponseDTO getEmployeeByEmail(String email) {

        logger.info("Fetching employee with email: {}", email);

        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee with email " + email + " not found"));

        return EmployeeMapper.toResponse(employee);
    }

    // Get employees by status
    public List<EmployeeResponseDTO> getEmployeesByStatus(String status) {

        logger.info("Fetching employees with status: {}", status);

        return employeeRepository.findByStatus(status)
                .stream()
                .map(EmployeeMapper::toResponse)
                .collect(Collectors.toList());
    }

    // Search employees by first name
    // Search employees by first name, last name or email
    public List<EmployeeResponseDTO> searchEmployees(String keyword) {

        logger.info("Searching employees with keyword: {}", keyword);

        return employeeRepository
            .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                    keyword,
                    keyword,
                    keyword)
            .stream()
            .map(EmployeeMapper::toResponse)
            .collect(Collectors.toList());
    }
    // Create employee
    public EmployeeResponseDTO saveEmployee(EmployeeRequestDTO requestDTO) {

        logger.info("Creating employee {}", requestDTO.getEmail());

        Employee employee = EmployeeMapper.toEntity(requestDTO);

        Employee savedEmployee = employeeRepository.save(employee);

        logger.info("Employee created successfully with ID {}", savedEmployee.getId());

        return EmployeeMapper.toResponse(savedEmployee);
    }

    // Update employee
    public EmployeeResponseDTO updateEmployee(Long id,
                                              EmployeeRequestDTO requestDTO) {

        logger.info("Updating employee with ID {}", id);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee with ID " + id + " not found"));

        employee.setFirstName(requestDTO.getFirstName());
        employee.setLastName(requestDTO.getLastName());
        employee.setEmail(requestDTO.getEmail());
        employee.setDepartment(requestDTO.getDepartment());
        employee.setSalary(requestDTO.getSalary());
        employee.setJoiningDate(requestDTO.getJoiningDate());
        employee.setStatus(requestDTO.getStatus());

        Employee updatedEmployee = employeeRepository.save(employee);

        logger.info("Employee {} updated successfully", id);

        return EmployeeMapper.toResponse(updatedEmployee);
    }

    // Delete employee
    public void deleteEmployee(Long id) {

        logger.info("Deleting employee with ID {}", id);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee with ID " + id + " not found"));

        employeeRepository.delete(employee);

        logger.info("Employee {} deleted successfully", id);
    }
}