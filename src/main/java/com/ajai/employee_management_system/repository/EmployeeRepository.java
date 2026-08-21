package com.ajai.employee_management_system.repository;

import com.ajai.employee_management_system.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartment(String department);

    Optional<Employee> findByEmail(String email);

    List<Employee> findByStatus(String status);

    List<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
        String firstName,
        String lastName,
        String email);
}