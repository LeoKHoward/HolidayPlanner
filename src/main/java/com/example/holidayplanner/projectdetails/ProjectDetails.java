package com.example.holidayplanner.projectdetails;

import com.example.holidayplanner.employee.EmployeeDetails;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectDetails {

    private LocalDate projectStartDate;
    private LocalDate projectEndDate;
    private int expectedProjectLengthInDays;
    private EmployeeDetails employee;

}
