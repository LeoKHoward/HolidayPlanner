package com.example.holidayplanner.employee;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class EmployeeDetails {

    private String firstName;
    private String lastName;
    private String employeeRole;
    private LocalDate annualLeaveStartDate;
    private LocalDate annualLeaveEndDate;
    private boolean isOnAnnualLeave;

}
