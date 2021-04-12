package com.example.holidayplanner.services;

import com.example.holidayplanner.employee.EmployeeDetails;
import com.example.holidayplanner.projectdetails.ProjectRequirements;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeServiceTest {

    @Test
    public void testAssignPeopleToProject() {

        List<EmployeeDetails> testEmployeeDetails = testEmployeeDetailData();

        ProjectRequirements testProject = new ProjectRequirements(1,
                2, 1, 60);

        EmployeeService testEmployeeService = new EmployeeService();

        List<EmployeeDetails> result = testEmployeeService.assignPeopleToProject(testProject, testEmployeeDetails);

        assertThat(result.size()).isEqualTo(4);

        EmployeeDetails businessAnalyst = result.get(0);
        assertThat(businessAnalyst.getFirstName()).isEqualTo("Jack");

        EmployeeDetails softwareEngineer1 = result.get(1);
        assertThat(softwareEngineer1.getFirstName()).isEqualTo("Willy");

        EmployeeDetails softwareEngineer2 = result.get(2);
        assertThat(softwareEngineer2.getFirstName()).isEqualTo("Sweeney");

        EmployeeDetails testAnalyst = result.get(3);
        assertThat(testAnalyst.getFirstName()).isEqualTo("Barnabas");


    }

    private List<EmployeeDetails> testEmployeeDetailData() {
        EmployeeDetails employee1 =
                new EmployeeDetails(
                        "Jack",
                        "Sparrow",
                        "Business Analyst",
                        (List.of(LocalDate.of(2021, 1, 4),
                                LocalDate.of(2021, 1, 5),
                                LocalDate.of(2021, 1, 6),
                                LocalDate.of(2021, 1, 7),
                                LocalDate.of(2021, 1, 8))));
        EmployeeDetails employee2 =
                new EmployeeDetails(
                        "Edward",
                        "Scissorhands",
                        "Business Analyst",
                        (List.of(LocalDate.of(2021, 1, 11),
                                LocalDate.of(2021, 1, 12),
                                LocalDate.of(2021, 1, 13),
                                LocalDate.of(2021, 1, 14),
                                LocalDate.of(2021, 1, 15))));
        EmployeeDetails employee3 =
                new EmployeeDetails(
                        "Willy",
                        "Wonka",
                        "Software Engineer",
                        (List.of(LocalDate.of(2021, 1, 18),
                                LocalDate.of(2021, 1, 19),
                                LocalDate.of(2021, 1, 20),
                                LocalDate.of(2021, 1, 21),
                                LocalDate.of(2021, 1, 22))));
        EmployeeDetails employee4 =
                new EmployeeDetails(
                        "Sweeney",
                        "Todd",
                        "Software Engineer",
                        (List.of(LocalDate.of(2021, 5, 4),
                                LocalDate.of(2021, 5, 5),
                                LocalDate.of(2021, 5, 6),
                                LocalDate.of(2021, 5, 7),
                                LocalDate.of(2021, 5, 10))));
        EmployeeDetails employee5 =
                new EmployeeDetails(
                        "Barnabas",
                        "Collins",
                        "Test Analyst",
                        (List.of(LocalDate.of(2021, 5, 17),
                                LocalDate.of(2021, 5, 18),
                                LocalDate.of(2021, 5, 19),
                                LocalDate.of(2021, 5, 20),
                                LocalDate.of(2021, 5, 21))));
        EmployeeDetails employee6 =
                new EmployeeDetails(
                        "Johnny",
                        "Depp",
                        "Test Analyst",
                        (List.of(LocalDate.of(2021, 5, 24),
                                LocalDate.of(2021, 5, 25),
                                LocalDate.of(2021, 5, 26),
                                LocalDate.of(2021, 5, 27),
                                LocalDate.of(2021, 5, 28))));

        List<EmployeeDetails> testEmployeesList = new ArrayList<>();
        testEmployeesList.add(employee1);
        testEmployeesList.add(employee2);
        testEmployeesList.add(employee3);
        testEmployeesList.add(employee4);
        testEmployeesList.add(employee5);
        testEmployeesList.add(employee6);

        return testEmployeesList;

    }

}