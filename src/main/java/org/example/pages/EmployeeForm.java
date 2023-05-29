package org.example.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.example.entities.Employee;
import org.example.services.CsvEmployeeService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class EmployeeForm {

    private static final Logger logger = LogManager.getLogger(EmployeeForm.class);
    @Inject
    private AlertManager alertManager;

    @PageActivationContext
    private String id;
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

    @Component(id = "titul")
    private Select titleSelect;

    @Component(id = "pohlavie")
    private Select pohlavieSelect;

    @InjectComponent
    private Form employeeForm;

    @InjectComponent("meno")
    private TextField menoField;
    @InjectComponent("priezvisko")
    private TextField priezviskoField;

    @SetupRender
    public void setup() {
        if (id != null) {
            employee = csvEmployeeService.getEmployeeById(id);

            if (employee != null) {
                meno = employee.getMeno();
                priezvisko = employee.getPriezvisko();
                selectedTitle = employee.getTitul();
                selectedPohlavie = employee.getPohlavie();
                datumNarodenia = employee.getDatumNarodenia();
            }
        }
    }

    public List<String> getTitleOptions() {
        return Arrays.asList("Ing.", "MUDr.", "JUDr.", "Bc.");
    }

    public List<String> getPohlavieOptions() {
        return Arrays.asList("Muž", "Źena", "Hybrid");
    }

    public Object onDeleteEmployee() {
        if (id != null) {
            employee = csvEmployeeService.getEmployeeById(id);
            if (employee != null) {
                csvEmployeeService.deleteEmployee(employee);
                employee = null; // Clear the employee object after deletion
            }
        }
        return EmployeeList.class;
    }

    public Object onSuccess() {
        if (id != null) {
            employee = csvEmployeeService.getEmployeeById(id);

            if (employee != null) {
                employee.setMeno(meno);
                employee.setPriezvisko(priezvisko);
                employee.setTitul(selectedTitle);
                employee.setPohlavie(selectedPohlavie);
                employee.setDatumNarodenia(datumNarodenia);
                csvEmployeeService.updateEmployee(employee);
                return EmployeeList.class;
            }
        }
        employee = new Employee(
                meno,
                priezvisko,
                selectedTitle,
                selectedPohlavie,
                datumNarodenia);
        csvEmployeeService.addEmployee(employee);

        return EmployeeList.class;
    }
    void onValidateFromEmployeeForm()
    {
        if (meno.length()< 3)
            employeeForm.recordError(menoField, "Enter valid First Name");

        if (priezvisko.length()< 3)
            employeeForm.recordError(priezviskoField, "Enter valid Last Name");

    }
    void onFailure()
    {
        logger.warn("Form error!");
        alertManager.error("Employee Form error, check for details!");
    }
}
