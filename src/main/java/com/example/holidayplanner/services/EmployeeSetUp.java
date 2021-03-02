package com.example.holidayplanner.services;

import com.example.holidayplanner.employee.EmployeeDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeSetUp {

    public List<EmployeeDetails> setAllEmployeeDetails() {
        EmployeeDetails employee1 =
                new EmployeeDetails(
                        "Jack",
                        "Sparrow",
                        "Business Analyst",
                        (LocalDate.of(2021, 1, 1)),
                        (LocalDate.of(2021, 1, 30)));
        EmployeeDetails employee2 =
                new EmployeeDetails(
                        "Edward",
                        "Scissorhands",
                        "Business Analyst",
                        (LocalDate.of(2021, 2, 1)),
                        (LocalDate.of(2021, 2, 28)));
        EmployeeDetails employee3 =
                new EmployeeDetails(
                        "Willy",
                        "Wonka",
                        "Software Engineer",
                        (LocalDate.of(2021, 3, 1)),
                        (LocalDate.of(2021, 3, 30)));
        EmployeeDetails employee4 =
                new EmployeeDetails(
                        "Sweeney",
                        "Todd",
                        "Software Engineer",
                        (LocalDate.of(2021, 4, 1)),
                        (LocalDate.of(2021, 4, 30)));
        EmployeeDetails employee5 =
                new EmployeeDetails(
                        "Barnabas",
                        "Collins",
                        "Test Analyst",
                        (LocalDate.of(2021, 5, 1)),
                        (LocalDate.of(2021, 5, 30)));
        EmployeeDetails employee6 =
                new EmployeeDetails(
                        "Johnny",
                        "Depp",
                        "Test Analyst",
                        (LocalDate.of(2021, 6, 1)),
                        (LocalDate.of(2021, 6, 30)));

        List<EmployeeDetails> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        employees.add(employee5);
        employees.add(employee6);

        return employees;

    }


}
