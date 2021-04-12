package com.example.holidayplanner.employee;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
public class EmployeeDetails {

    /*
    All relevant employee details
    */

    private String firstName;
    private String lastName;
    private String employeeRole;
    private List<LocalDate> daysOnLeave;

}
