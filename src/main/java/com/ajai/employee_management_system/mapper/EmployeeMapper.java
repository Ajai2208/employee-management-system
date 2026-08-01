package com.ajai.employee_management_system.mapper;

import com.ajai.employee_management_system.dto.EmployeeRequestDTO;
import com.ajai.employee_management_system.dto.EmployeeResponseDTO;
import com.ajai.employee_management_system.entity.Employee;

public class EmployeeMapper {

    public static Employee toEntity(EmployeeRequestDTO dto) {

        Employee employee = new Employee();

        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setSalary(dto.getSalary());
        employee.setJoiningDate(dto.getJoiningDate());
        employee.setStatus(dto.getStatus());

        return employee;
    }

    public static EmployeeResponseDTO toResponse(Employee employee) {

        EmployeeResponseDTO dto = new EmployeeResponseDTO();

        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setDepartment(employee.getDepartment());
        dto.setSalary(employee.getSalary());
        dto.setJoiningDate(employee.getJoiningDate());
        dto.setStatus(employee.getStatus());

        return dto;
    }
}