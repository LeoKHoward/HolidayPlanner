package com.example.holidayplanner.projectdetails;

import com.example.holidayplanner.employee.EmployeeDetails;
import com.example.holidayplanner.publicholidays.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
public class NewProjectDetails {

    /*
    Fields will be completed when the application is run
    Parameters are set in ThePostStartUp Class which will effect the results of the fields below
    */

    private LocalDate projectSuggestedStartDate;
    private LocalDate projectSuggestedEndDate;
    private List<EmployeeDetails> employeeDetails;
    private List<Event> event;
    private List<LocalDate> allDaysInProjectLifeSpan;

}
