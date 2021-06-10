package com.example.holidayplanner;

import com.example.holidayplanner.employee.EmployeeDetails;
import com.example.holidayplanner.projectdetails.ProjectRequirements;
import com.example.holidayplanner.publicholidays.Event;
import com.example.holidayplanner.services.EmployeeService;
import com.example.holidayplanner.employee.EmployeeSetUp;
import com.example.holidayplanner.services.HolidayService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class ThePostStartUpClass {

    /*
    Set up constructor to allow all necessary classes to be accessed
    */

    private final HolidayService holidayService;
    private final EmployeeSetUp employeeSetUp;
    private final EmployeeService employeeService;


    public ThePostStartUpClass(HolidayService holidayService, EmployeeSetUp employeeSetUp,
                               EmployeeService employeeService) {

        this.holidayService = holidayService;
        this.employeeSetUp = employeeSetUp;
        this.employeeService = employeeService;
    }

    @PostConstruct
    public void oneMethodToRuleThemAll() {

        /*
        Call GOV.uk API to get bank holidays from JSON
        */
        List<Event> bankHols = holidayService.getBankHols();

        System.out.println("\n");

        /*
        Print out all England/Wales bank holidays from API
        */
        getEnglandAndWalesBankHolidayDatesAndTitles();

        System.out.println("\n");

        /*
        Print out all employees and their details in the company
        */
        List<EmployeeDetails> companyEmployees = getAllCompanyEmployeeDetails();

        System.out.println("\n");


        /*
        Specify required number BAs/SEs/TAs and total expected business days in project length
        */
        ProjectRequirements projectOne = new ProjectRequirements(1,
                2, 2, 6);


        /*
        Print out days in project/start date/end date/whether its across bank holidays
        */
        holidayService.workoutProjectEndDateAndIfItIncludesBankHols(projectOne, bankHols);

        System.out.println("\n");

        /*
        Print out required team member numbers for project
        */
        System.out.println("Required numbers of each role:" +
                "\nBusiness Analysts: " + projectOne.getNoOfBusinessAnalystsRequired() +
                "\nSoftware Engineers: " + projectOne.getNoOfSoftwareEngineersRequired() +
                "\nTest Analysts: " + projectOne.getNoOfTestAnalystsRequired());

        System.out.println("\n");


        /*
        Print out all employees assigned to project based on numbers required above
        */
        System.out.println("Employees assigned to project:\n");
        employeeService.assignPeopleToProject(projectOne, companyEmployees);

        System.out.println("\n");


    }


    /*
    Organise bank holiday dates and title into a readable format
    */
    private void getEnglandAndWalesBankHolidayDatesAndTitles() {
        List<Event> eventList = holidayService.getBankHols();

        for (Event b : eventList) {
            System.out.println("Date: " + b.getDate() + "    Title: " + b.getTitle());
        }
    }

    /*
    Get details of all employees at company that could potentially be chosen to be in the team
    */
    private List<EmployeeDetails> getAllCompanyEmployeeDetails() {
        List<EmployeeDetails> companyEmployees = Arrays.asList(employeeSetUp.getAllEmployeeDetails());

        for (EmployeeDetails companyEmployee : companyEmployees) {
            System.out.println(companyEmployee);
        }
        return companyEmployees;
    }


}

