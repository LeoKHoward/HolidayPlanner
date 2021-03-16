package com.example.holidayplanner;

import com.example.holidayplanner.employee.EmployeeDetails;
import com.example.holidayplanner.projectdetails.ProjectRequirements;
import com.example.holidayplanner.publicholidays.Event;
import com.example.holidayplanner.services.EmployeeService;
import com.example.holidayplanner.employee.EmployeeSetUp;
import com.example.holidayplanner.services.HolidayService;
import com.example.holidayplanner.services.ProjectService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class ThePostStartUpClass {

    private final HolidayService holidayService;
    private final EmployeeSetUp employeeSetUp;
    private final EmployeeService employeeService;
    private final ProjectService projectService;


    public ThePostStartUpClass(HolidayService holidayService, EmployeeSetUp employeeSetUp,
                               EmployeeService employeeService, ProjectService projectService) {

        this.holidayService = holidayService;
        this.employeeSetUp = employeeSetUp;
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    @PostConstruct
    public void oneMethodToRuleThemAll() {

        List<Event> bankHols = holidayService.getBankHols();

        System.out.println("\n");

        showLoadingOfBankHols();

        System.out.println("\n");

        showSettingUpEmployees();

        System.out.println("\n");

        List<EmployeeDetails> allEmployees = employeeSetUp.getAllEmployeeDetails();

        ProjectRequirements projectOne = new ProjectRequirements(1,
                2, 1, 14);


        holidayService.workoutProjectEndDateAndIfItIncludesBankHols(projectOne, bankHols);

        System.out.println("\n");

        System.out.println("Employees assigned to project:");
        employeeService.assignPeopleToProject(projectOne, allEmployees);

        System.out.println("\n");

        holidayService.findOutIfEmployeeOnAnnualLeaveDuringProjectLifeSpan(allEmployees, );

    }

    private void showLoadingOfBankHols() {
        List<Event> eventList = holidayService.getBankHols();

        for (Event b : eventList) {
            System.out.println("Date: " + b.getDate() + "    Title: " + b.getTitle());
        }
    }

    public void showSettingUpEmployees() {
        List<EmployeeDetails> employees = employeeSetUp.getAllEmployeeDetails();

        for (EmployeeDetails employee : employees) {
            System.out.println(employee);
        }

    }


}

