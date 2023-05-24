package org.example.services;

import org.example.entities.Employee;

import java.util.List;

public interface CsvEmployeeService {
    List<Employee> getAllEmployees();
    void addEmployee(Employee employee);
//    void updateEmployee(Employee employee);
//    void deleteEmployee(Employee employee);
}
