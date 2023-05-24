package org.example.pages;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.example.entities.Employee;
import org.example.services.CsvEmployeeService;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

public class EmployeeForm {

    @Property
    private Employee employee;

    @Property
    private String meno;
    @Property
    private String priezvisko;


    @Property
    private String selectedTitle;

    @Property
    private String selectedPohlavie;

    @Property
    private LocalDate datumNarodenia;

    @Inject
    private CsvEmployeeService csvEmployeeService;

    @Component
    private Form employeeForm;

    @Component(id = "titul")
    private Select titleSelect;

    @Component(id = "pohlavie")
    private Select pohlavieSelect;

//    @SetupRender
//    public void setup() {
//        if (employee == null) {
//            employee = new Employee();
//        }
//    }

    public List<String> getTitleOptions() {
        return Arrays.asList("Ing.", "MUDr.", "JUDr.", "Bc.");
    }

    public List<String> getPohlavieOptions() {
        return Arrays.asList("Muž", "Źena", "Hybrid");
    }

    public Object onSuccess() {
        employee = new Employee(meno,
                priezvisko,
                selectedTitle,
                selectedPohlavie,
                datumNarodenia);
        csvEmployeeService.addEmployee(employee);
        return EmployeeList.class;
    }
}
