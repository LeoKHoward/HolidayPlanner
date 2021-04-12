package com.example.holidayplanner.services;

import com.example.holidayplanner.employee.EmployeeDetails;
import com.example.holidayplanner.projectdetails.ProjectRequirements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeService {

    /*
    This class gets the required number of BAs/SEs/TAs specified in ThePostStartUp Class
    then filters through all available employees in company and moves required numbers
    into a new list to be used as project team members
    */
    public List<EmployeeDetails> assignPeopleToProject(ProjectRequirements projectRequirements,
                                                       List<EmployeeDetails> allEmployees) {

        /*
        Create new list to put forward selected employees for the new project
        */
        List<EmployeeDetails> projectTeamMembers = new ArrayList<>();


        /*
        If one employee of a role type is on annual leave across the project dates then need to get next employee

        If two are required for a role and one or both is unavailable then project start date needs to move
        */


        /*
        Filters all BAs to list, gets required number from PostStartUpClass and then adds them to project
        team members list
        */
        List<EmployeeDetails> businessAnalysts = allEmployees.stream()
                .filter(e -> e.getEmployeeRole().equals("Business Analyst"))
                .limit(projectRequirements.getNoOfBusinessAnalystsRequired())
                .collect(Collectors.toList());

        projectTeamMembers.addAll(businessAnalysts);


        /*
        Filters all Software Engineers to list, gets required number from PostStartUpClass and then
        adds them to project team members list
        */
        List<EmployeeDetails> softwareEngineers = allEmployees.stream()
                .filter(e -> e.getEmployeeRole().equals("Software Engineer"))
                .limit(projectRequirements.getNoOfSoftwareEngineersRequired())
                .collect(Collectors.toList());

        projectTeamMembers.addAll(softwareEngineers);


        /*
        Filters all Test Analysts to list, gets required number from PostStartUpClass and then
        adds them to project team members list
        */
        List<EmployeeDetails> testAnalysts = allEmployees.stream()
                .filter(e -> e.getEmployeeRole().equals("Test Analyst"))
                .limit(projectRequirements.getNoOfTestAnalystsRequired())
                .collect(Collectors.toList());

        projectTeamMembers.addAll(testAnalysts);


        /*
        Prints out all team members in the project team member list who have now been assigned
        */
        for (EmployeeDetails projectTeamMember : projectTeamMembers) {
            System.out.println(projectTeamMember.getFirstName() + " " + projectTeamMember.getLastName() + " - "
                    + projectTeamMember.getEmployeeRole());
        }

        return projectTeamMembers;


    }


}
