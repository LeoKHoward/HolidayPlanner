package com.example.holidayplanner.projectdetails;

import com.example.holidayplanner.employee.EmployeeDetails;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
public class NewProjectDetails {

    private LocalDate projectSuggestedStartDate;
    private LocalDate projectSuggestedEndDate;
    private List<EmployeeDetails> employeeDetails;
    private Boolean employeesOnAnnualLeave;
    private Boolean projectRunsAcrossBankHolidays;


}
