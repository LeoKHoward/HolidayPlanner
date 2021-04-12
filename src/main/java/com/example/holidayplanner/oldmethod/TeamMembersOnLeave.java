package com.example.holidayplanner.oldmethod;

import com.example.holidayplanner.employee.EmployeeDetails;
import com.example.holidayplanner.services.HolidayService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TeamMembersOnLeave {

    private final HolidayService holidayService;

    public TeamMembersOnLeave(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    /*
    This method checks the selected team members annual leave dates from EmployeeSetUp Class
    against the business days in the project lifespan from method above
    */
    public void determineWhenEmployeeIsOnAnnualLeave(List<EmployeeDetails> projectTeamMembers) {

        /*
        Get the business days from project lifespan from method above
        */
        List<LocalDate> daysInProjectLifespan = holidayService.getAllDaysInProjectLifeSpan();

        /*
        Iterates through each team member in project from the list generated in EmployeeService Class
        */
        for (EmployeeDetails projectTeamMember : projectTeamMembers) {

            /*
            New list created to put all leave dates from selected project team members into one place
            */
            List<LocalDate> daysOnLeave = projectTeamMember.getDaysOnLeave();

            /*
            Iterates through each (business) day in project lifespan from the list created at start of method
            */
            for (LocalDate dayInProjectLifespan : daysInProjectLifespan) {

                /*
                Create a new list to put clashing days (leave/project) into one place
                */
                List<LocalDate> daysInProjectWhenTeamMemberOnLeave = new ArrayList<>();

                /*
                Iterate through each day in daysOnLeave list
                */
                for (LocalDate dayOnLeave : daysOnLeave) {

                    /*
                    If a dayOnLeave matches any day in the project lifespan...
                    */
                    if (dayOnLeave.equals(dayInProjectLifespan)) {

                        /*
                        ...then add that clashing day to the new list created above
                        */
                        daysInProjectWhenTeamMemberOnLeave.add(dayOnLeave);

                        /*
                        Print out the name of the team member on leave, their job and each day they are on leave
                        when the project is meant to be running
                        */
                        System.out.println("This person (" + projectTeamMember.getFirstName() + " "
                                + projectTeamMember.getLastName() + "), who is a "
                                + projectTeamMember.getEmployeeRole()
                                + ", is on annual leave across the project forecasted dates of "
                                + daysInProjectWhenTeamMemberOnLeave + "!");

                        break;


                    }

                }

            }

        }

    }


}
