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

//        figureOutBestStartDateBasedOnTeamMembersAnnualLeave();
//
//        for (EmployeeDetails teamMember : teamMembers) {
//            if (teamMember.getEmployeeRole().contains("Business") && teamMember.isOnAnnualLeave()) {
//                teamMembers.remove(teamMember);
//            }
//        }

        for (EmployeeDetails teamMember : teamMembers) {
            System.out.println(teamMember.getFirstName() + " " + teamMember.getLastName() + " - "
                    + teamMember.getEmployeeRole());
        }

        return teamMembers;

    }


}
