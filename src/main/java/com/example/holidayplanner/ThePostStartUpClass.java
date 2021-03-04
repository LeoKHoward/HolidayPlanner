package com.example.holidayplanner;

import com.example.holidayplanner.employee.EmployeeDetails;
import com.example.holidayplanner.projectdetails.NewProjectDetails;
import com.example.holidayplanner.projectdetails.ProjectRequirements;
import com.example.holidayplanner.publicholidays.Event;
import com.example.holidayplanner.services.EmployeeService;
import com.example.holidayplanner.employee.EmployeeSetUp;
import com.example.holidayplanner.services.BankHolidayService;
import com.example.holidayplanner.services.ProjectService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class ThePostStartUpClass {

    private final BankHolidayService bankHolidayService;
    private final EmployeeSetUp employeeSetUp;
    private final EmployeeService employeeService;
    private final ProjectService projectService;


    public ThePostStartUpClass(BankHolidayService bankHolidayService, EmployeeSetUp employeeSetUp,
                               EmployeeService employeeService, ProjectService projectService) {

        this.bankHolidayService = bankHolidayService;
        this.employeeSetUp = employeeSetUp;
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    @PostConstruct
    public void oneMethodToRuleThemAll() {

        List<Event> bankHols = bankHolidayService.getBankHols();

        System.out.println("\n");

        showLoadingOfBankHols();

        System.out.println("\n");

        showSettingUpEmployees();

        System.out.println("\n");

        List<EmployeeDetails> allEmployees = employeeSetUp.getAllEmployeeDetails();

        ProjectRequirements projectOne = new ProjectRequirements(1,
                2, 1, 30);

        System.out.println("Employees assigned to project:");
        List<EmployeeDetails> teamMembers = employeeService.assignPeopleToProject(projectOne, allEmployees);

        System.out.println("\n");

        projectService.figureOutBestStartDateBasedOnTeamMembersAnnualLeave(teamMembers);

        bankHolidayService.workoutProjectEndDateAndIfItIncludesBankHols(projectOne, bankHols);


    }

    private void showLoadingOfBankHols() {
        List<Event> eventList = bankHolidayService.getBankHols();

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

