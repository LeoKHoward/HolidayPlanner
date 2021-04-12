package com.example.holidayplanner.employee;

import com.example.holidayplanner.employee.EmployeeDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeSetUp {

    /*
    Create a company of employees
    */

    public List<EmployeeDetails> getAllEmployeeDetails() {
        EmployeeDetails employee1 =
                new EmployeeDetails(
                        "Jack",
                        "Sparrow",
                        "Business Analyst",
                        (List.of(LocalDate.of(2021, 1, 4),
                                LocalDate.of(2021, 1, 5),
                                LocalDate.of(2021, 1, 6),
                                LocalDate.of(2021, 1, 7),
                                LocalDate.of(2021, 1, 8),
                                LocalDate.of(2021, 2, 1),
                                LocalDate.of(2021, 2, 2),
                                LocalDate.of(2021, 2, 3),
                                LocalDate.of(2021, 2, 4),
                                LocalDate.of(2021, 2, 5),
                                LocalDate.of(2021, 3, 1),
                                LocalDate.of(2021, 3, 2),
                                LocalDate.of(2021, 3, 3),
                                LocalDate.of(2021, 3, 4),
                                LocalDate.of(2021, 3, 5),
                                LocalDate.of(2021, 11, 1),
                                LocalDate.of(2021, 11, 2),
                                LocalDate.of(2021, 11, 3),
                                LocalDate.of(2021, 11, 4),
                                LocalDate.of(2021, 11, 5))));
        EmployeeDetails employee2 =
                new EmployeeDetails(
                        "Edward",
                        "Scissorhands",
                        "Business Analyst",
                        (List.of(LocalDate.of(2021, 9, 11),
                                LocalDate.of(2021, 9, 12),
                                LocalDate.of(2021, 9, 13),
                                LocalDate.of(2021, 9, 14),
                                LocalDate.of(2021, 9, 15),
                                LocalDate.of(2021, 2, 8),
                                LocalDate.of(2021, 2, 9),
                                LocalDate.of(2021, 2, 10),
                                LocalDate.of(2021, 2, 11),
                                LocalDate.of(2021, 2, 12),
                                LocalDate.of(2021, 3, 8),
                                LocalDate.of(2021, 3, 9),
                                LocalDate.of(2021, 3, 10),
                                LocalDate.of(2021, 3, 11),
                                LocalDate.of(2021, 3, 12),
                                LocalDate.of(2021, 4, 12),
                                LocalDate.of(2021, 4, 13),
                                LocalDate.of(2021, 4, 14),
                                LocalDate.of(2021, 4, 15),
                                LocalDate.of(2021, 4, 16))));
        EmployeeDetails employee3 =
                new EmployeeDetails(
                        "Willy",
                        "Wonka",
                        "Software Engineer",
                        (List.of(LocalDate.of(2021, 1, 18),
                                LocalDate.of(2021, 1, 19),
                                LocalDate.of(2021, 1, 20),
                                LocalDate.of(2021, 1, 21),
                                LocalDate.of(2021, 1, 22),
                                LocalDate.of(2021, 2, 15),
                                LocalDate.of(2021, 2, 16),
                                LocalDate.of(2021, 2, 17),
                                LocalDate.of(2021, 2, 18),
                                LocalDate.of(2021, 2, 19),
                                LocalDate.of(2021, 3, 15),
                                LocalDate.of(2021, 3, 16),
                                LocalDate.of(2021, 3, 17),
                                LocalDate.of(2021, 3, 18),
                                LocalDate.of(2021, 3, 19),
                                LocalDate.of(2021, 4, 19),
                                LocalDate.of(2021, 4, 20),
                                LocalDate.of(2021, 4, 21),
                                LocalDate.of(2021, 4, 22),
                                LocalDate.of(2021, 4, 23))));
        EmployeeDetails employee4 =
                new EmployeeDetails(
                        "Sweeney",
                        "Todd",
                        "Software Engineer",
                        (List.of(LocalDate.of(2021, 5, 4),
                                LocalDate.of(2021, 5, 5),
                                LocalDate.of(2021, 5, 6),
                                LocalDate.of(2021, 5, 7),
                                LocalDate.of(2021, 5, 10),
                                LocalDate.of(2021, 6, 7),
                                LocalDate.of(2021, 6, 8),
                                LocalDate.of(2021, 6, 9),
                                LocalDate.of(2021, 6, 10),
                                LocalDate.of(2021, 6, 11),
                                LocalDate.of(2021, 7, 5),
                                LocalDate.of(2021, 7, 6),
                                LocalDate.of(2021, 7, 7),
                                LocalDate.of(2021, 7, 8),
                                LocalDate.of(2021, 7, 9),
                                LocalDate.of(2021, 8, 2),
                                LocalDate.of(2021, 8, 3),
                                LocalDate.of(2021, 8, 4),
                                LocalDate.of(2021, 8, 5),
                                LocalDate.of(2021, 8, 6))));
        EmployeeDetails employee5 =
                new EmployeeDetails(
                        "Barnabas",
                        "Collins",
                        "Test Analyst",
                        (List.of(LocalDate.of(2021, 5, 17),
                                LocalDate.of(2021, 5, 18),
                                LocalDate.of(2021, 5, 19),
                                LocalDate.of(2021, 5, 20),
                                LocalDate.of(2021, 5, 21),
                                LocalDate.of(2021, 6, 14),
                                LocalDate.of(2021, 6, 15),
                                LocalDate.of(2021, 6, 16),
                                LocalDate.of(2021, 6, 17),
                                LocalDate.of(2021, 6, 18),
                                LocalDate.of(2021, 7, 12),
                                LocalDate.of(2021, 7, 13),
                                LocalDate.of(2021, 7, 14),
                                LocalDate.of(2021, 7, 15),
                                LocalDate.of(2021, 7, 16),
                                LocalDate.of(2021, 8, 9),
                                LocalDate.of(2021, 8, 10),
                                LocalDate.of(2021, 8, 11),
                                LocalDate.of(2021, 8, 12),
                                LocalDate.of(2021, 8, 13))));
        EmployeeDetails employee6 =
                new EmployeeDetails(
                        "Johnny",
                        "Depp",
                        "Test Analyst",
                        (List.of(LocalDate.of(2021, 5, 24),
                                LocalDate.of(2021, 5, 25),
                                LocalDate.of(2021, 5, 26),
                                LocalDate.of(2021, 5, 27),
                                LocalDate.of(2021, 5, 28),
                                LocalDate.of(2021, 6, 21),
                                LocalDate.of(2021, 6, 22),
                                LocalDate.of(2021, 6, 23),
                                LocalDate.of(2021, 6, 24),
                                LocalDate.of(2021, 6, 25),
                                LocalDate.of(2021, 7, 19),
                                LocalDate.of(2021, 7, 20),
                                LocalDate.of(2021, 7, 21),
                                LocalDate.of(2021, 7, 22),
                                LocalDate.of(2021, 7, 23),
                                LocalDate.of(2021, 8, 23),
                                LocalDate.of(2021, 8, 24),
                                LocalDate.of(2021, 8, 25),
                                LocalDate.of(2021, 8, 26),
                                LocalDate.of(2021, 8, 27))));


        /*
        Add all created employees to a master employee list
        */
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
