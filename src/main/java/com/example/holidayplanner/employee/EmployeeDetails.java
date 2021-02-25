package com.example.holidayplanner.employee;

import java.time.LocalDate;


public class EmployeeDetails {

    private String firstName;
    private String lastName;
    private String employeeRole;
    private LocalDate annualLeaveStartDate;
    private LocalDate annualLeaveEndDate;

    public EmployeeDetails(String firstName, String lastName, String employeeRole, LocalDate annualLeaveStartDate,
                           LocalDate annualLeaveEndDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeRole = employeeRole;
        this.annualLeaveStartDate = annualLeaveStartDate;
        this.annualLeaveEndDate = annualLeaveEndDate;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public LocalDate getAnnualLeaveStartDate() {
        return annualLeaveStartDate;
    }

    public void setAnnualLeaveStartDate(LocalDate annualLeaveStartDate) {
        this.annualLeaveStartDate = annualLeaveStartDate;
    }

    public LocalDate getAnnualLeaveEndDate() {
        return annualLeaveEndDate;
    }

    public void setAnnualLeaveEndDate(LocalDate annualLeaveEndDate) {
        this.annualLeaveEndDate = annualLeaveEndDate;
    }

    @Override
    public String toString() {
        return "EmployeeDetails{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", employeeRole='" + employeeRole + '\'' +
                ", annualLeaveStartDate=" + annualLeaveStartDate +
                ", annualLeaveEndDate=" + annualLeaveEndDate +
                '}';
    }
}
