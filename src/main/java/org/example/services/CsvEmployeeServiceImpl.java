package org.example.services;

import org.example.entities.Employee;

import java.io.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class CsvEmployeeServiceImpl implements CsvEmployeeService {
    private static final String CSV_FILE_PATH = "src/main/resources/employees.csv";

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String meno = data[0];
                String priezvisko = data[1];
                String titul = data[2];
                String pohlavie = data[3];
                long timestamp = Long.parseLong(data[4]);



                LocalDate datumNarodenia = Instant.ofEpochSecond(timestamp)
                        .atZone(ZoneOffset.UTC)
                        .toLocalDate();

                Employee employee = new Employee(meno, priezvisko, titul, pohlavie, datumNarodenia);
                employees.add(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public void addEmployee(Employee employee) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH, true))) {
            String line = employee.getMeno() + "," +
                    employee.getPriezvisko() + "," +
                    employee.getTitul() + "," +
                    employee.getPohlavie() + "," +
                    employee.getDatumNarodenia().toString();
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
