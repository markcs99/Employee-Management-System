package org.example.pages;

import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.example.entities.Employee;
import org.example.services.CsvEmployeeService;

public class EmployeeForm {

    @Property
    private Employee employee;

    @Inject
    private CsvEmployeeService csvEmployeeService;

    @InjectPage
    private EmployeeList employeeList;

    @Component
    private Form employeeForm;

    @SetupRender
    public void setup() {
        if (employee == null) {
            employee = new Employee();
        }
    }

    public void onSuccess() {
        csvEmployeeService.addEmployee(employee);
        employeeList.setup();
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
