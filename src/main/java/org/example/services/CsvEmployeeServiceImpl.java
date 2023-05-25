package org.example.services;

import org.example.entities.Employee;

import java.io.*;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;
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
                int id = Integer.parseInt(data[0]);
                String meno = data[1];
                String priezvisko = data[2];
                String titul = data[3];
                String pohlavie = data[4];
                LocalDate datumNarodenia =  LocalDate.parse(data[5]);;

                Employee employee = new Employee(id, meno, priezvisko, titul, pohlavie, datumNarodenia);
                employees.add(employee);
            }
        } catch (IOException | DateTimeParseException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public void addEmployee(Employee employee) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH, true))) {
            int nextId = getNextId();
            String line = nextId + "," +
                    employee.getMeno() + "," +
                    employee.getPriezvisko() + "," +
                    employee.getTitul() + "," +
                    employee.getPohlavie() + "," +
                    employee.getDatumNarodenia();
            writer.newLine();
            writer.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getNextId() {
        int nextId = 1;

        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                if (id >= nextId) {
                    nextId = id + 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nextId;
    }

    private LocalDate parseDate(String dateStr) {
        try {
            long timestamp = Long.parseLong(dateStr);
            return Instant.ofEpochSecond(timestamp).atZone(ZoneOffset.UTC).toLocalDate();
        } catch (NumberFormatException e) {
            return LocalDate.parse(dateStr);
        }
    }

    private String formatDate(LocalDate date) {
        long timestamp = date.atStartOfDay(ZoneOffset.UTC).toEpochSecond();
        return String.valueOf(timestamp);
    }
}
