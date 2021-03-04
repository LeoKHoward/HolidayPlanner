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
                        (LocalDate.of(2021, 1, 1)),
                        (LocalDate.of(2021, 1, 30)), false);
        EmployeeDetails employee2 =
                new EmployeeDetails(
                        "Edward",
                        "Scissorhands",
                        "Business Analyst",
                        (LocalDate.of(2021, 2, 1)),
                        (LocalDate.of(2021, 2, 28)), false);
        EmployeeDetails employee3 =
                new EmployeeDetails(
                        "Willy",
                        "Wonka",
                        "Software Engineer",
                        (LocalDate.of(2021, 3, 1)),
                        (LocalDate.of(2021, 3, 30)), false);
        EmployeeDetails employee4 =
                new EmployeeDetails(
                        "Sweeney",
                        "Todd",
                        "Software Engineer",
                        (LocalDate.of(2021, 4, 1)),
                        (LocalDate.of(2021, 4, 30)), false);
        EmployeeDetails employee5 =
                new EmployeeDetails(
                        "Barnabas",
                        "Collins",
                        "Test Analyst",
                        (LocalDate.of(2021, 5, 1)),
                        (LocalDate.of(2021, 5, 30)), false);
        EmployeeDetails employee6 =
                new EmployeeDetails(
                        "Johnny",
                        "Depp",
                        "Test Analyst",
                        (LocalDate.of(2021, 6, 1)),
                        (LocalDate.of(2021, 6, 30)), false);

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