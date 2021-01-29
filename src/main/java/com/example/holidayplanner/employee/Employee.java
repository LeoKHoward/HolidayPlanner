package com.example.holidayplanner.employee;

import java.util.ArrayList;
import java.util.List;


public class Employee {

    private String firstName;
    private String lastName;
    private String employeeRole;


    public Employee(String firstName, String lastName, String employeeRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeRole = employeeRole;
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

    public List<Employee> addNewEmployee() {
        List<Employee> listEmployee = new ArrayList<>();
        listEmployee.add(new Employee("Jack", "Sparrow", "Business Analyst"));
        listEmployee.add(new Employee("Edward", "Scissorhands", "Business Analyst"));
        listEmployee.add(new Employee("Willy", "Wonka", "Software Engineer"));
        listEmployee.add(new Employee("Sweeney", "Todd", "Software Engineer"));
        listEmployee.add(new Employee("Barnabas", "Collins", "Test Analyst"));
        listEmployee.add(new Employee("Johnny", "Depp", "Test Analyst"));

        return listEmployee;
    }
}
