package com.example.holidayplanner.annualleave;

import com.example.holidayplanner.employee.Employee;

import java.time.LocalDate;

public class AnnualLeave {

    private Employee employee;
    private LocalDate annualLeaveStartDate;
    private LocalDate annualLeaveEndDate;
    private boolean isEmployeeOnHoliday;

    public AnnualLeave(Employee employee, LocalDate annualLeaveStartDate, LocalDate annualLeaveEndDate,
                       boolean isEmployeeOnHoliday) {
        this.employee = employee;
        this.annualLeaveStartDate = annualLeaveStartDate;
        this.annualLeaveEndDate = annualLeaveEndDate;
        this.isEmployeeOnHoliday = isEmployeeOnHoliday;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public boolean isEmployeeOnHoliday() {
        return isEmployeeOnHoliday;
    }

    public void setEmployeeOnHoliday(boolean employeeOnHoliday) {
        isEmployeeOnHoliday = employeeOnHoliday;
    }

}
