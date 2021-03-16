package com.example.holidayplanner.services;

import com.example.holidayplanner.employee.EmployeeDetails;
import com.example.holidayplanner.projectdetails.ProjectRequirements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeService {

    public List<EmployeeDetails> assignPeopleToProject(ProjectRequirements projectRequirements,
                                                       List<EmployeeDetails> allEmployees) {

        List<EmployeeDetails> teamMembers = new ArrayList<>();

        HolidayService holidayService = new HolidayService();

        /*
        I need to access employee holidays and see if the flag for on annual leave is true

        If it is true then they will be unable to be picked from list below

        If two are required for a role and one or both is unavailable then project start date needs to move

         */


        List<EmployeeDetails> businessAnalysts = allEmployees.stream()
                .filter(e -> e.getEmployeeRole().equals("Business Analyst"))
                .limit(projectRequirements.getNoOfBusinessAnalystsRequired())
                .collect(Collectors.toList());


        teamMembers.addAll(businessAnalysts);

        List<EmployeeDetails> softwareEngineers = allEmployees.stream()
                .filter(e -> e.getEmployeeRole().equals("Software Engineer"))
                .limit(projectRequirements.getNoOfSoftwareEngineersRequired())
                .collect(Collectors.toList());

        teamMembers.addAll(softwareEngineers);

        List<EmployeeDetails> testAnalysts = allEmployees.stream()
                .filter(e -> e.getEmployeeRole().equals("Test Analyst"))
                .limit(projectRequirements.getNoOfTestAnalystsRequired())
                .collect(Collectors.toList());

        teamMembers.addAll(testAnalysts);


        for (EmployeeDetails teamMember : teamMembers) {
            System.out.println(teamMember.getFirstName() + " " + teamMember.getLastName() + " - "
                    + teamMember.getEmployeeRole());
        }

        return teamMembers;

    }

//    private List<EmployeeDetails> getEmployeeByType(List<EmployeeDetails> allEmployees, String employeeTitle,
//                                                    int noOfBusinessAnalystsRequired) {
//        return allEmployees.stream()
//                .filter(e -> e.getEmployeeRole().equals(employeeTitle))
//                .limit(noOfBusinessAnalystsRequired)
//                .collect(Collectors.toList());
//    }

//    public void chooseDifferentEmployeeIfFirstIsOnLeave() {
//
//        HolidayService holidayService = new HolidayService();
//
//
//
//    }


}
