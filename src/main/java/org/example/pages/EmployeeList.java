package org.example.pages;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.example.entities.Employee;
import org.example.services.CsvEmployeeService;

import java.util.List;

public class EmployeeList {

    @Inject
    private CsvEmployeeService csvEmployeeService;

    @Property
    private List<Employee> employees;

    @InjectPage
    private EmployeeForm employeeForm;

    @SetupRender
    public void setup() {
        employees = csvEmployeeService.getAllEmployees();
    }

    public void onAddEmployee() {
        employeeForm.setEmployee(new Employee());
    }

    public EmployeeForm onEditEmployee(Employee employee) {
        employeeForm.setEmployee(employee);
        return employeeForm;
    }

}

