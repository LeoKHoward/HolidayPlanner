package com.example.holidayplanner.services;

import com.example.holidayplanner.employee.EmployeeDetails;
import com.example.holidayplanner.projectdetails.ProjectRequirements;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeService {

    public static HolidayService holidayService;

    public EmployeeService(HolidayService holidayService) {
        EmployeeService.holidayService = holidayService;
    }

    /*
    This class gets the required number of BAs/SEs/TAs specified in ThePostStartUp Class
    then filters through all available employees in company and moves required numbers
    into a new list to be used as project team members
    */
    public void assignPeopleToProject(ProjectRequirements projectRequirements,
                                      List<EmployeeDetails> allEmployees) {




        /*
        Create new list to put forward selected employees for the new project
        */
        List<EmployeeDetails> projectTeamMembers = new ArrayList<>();

        List<LocalDate> daysInProject = holidayService.getAllDaysInProjectLifeSpan();



        /*
        Filters all BAs to list, gets required number from ThePostStartUp Class and then adds them to project
        team members list
        */
        selectBusinessAnalystsNotOnLeave(projectRequirements, allEmployees, projectTeamMembers, daysInProject);



        /*
        Filters all Software Engineers to list, gets required number from ThePostStartUp Class and then
        adds them to project team members list
        */
        selectSoftwareEngineersNotOnLeave(projectRequirements, allEmployees, projectTeamMembers, daysInProject);




        /*
        Filters all Test Analysts to list, gets required number from ThePostStartUp Class and then
        adds them to project team members list
        */
        selectTestAnalystsNotOnLeave(projectRequirements, allEmployees, projectTeamMembers, daysInProject);


        /*
        Prints out all team members in the project team member list who have now been assigned
        */
        for (EmployeeDetails projectTeamMember : projectTeamMembers) {
            System.out.println(projectTeamMember.getFirstName() + " " + projectTeamMember.getLastName() + " - "
                    + projectTeamMember.getEmployeeRole());
        }


    }


    private void selectBusinessAnalystsNotOnLeave(ProjectRequirements projectRequirements,
                                                  List<EmployeeDetails> allEmployees,
                                                  List<EmployeeDetails> projectTeamMembers,
                                                  List<LocalDate> daysInProject) {

        List<EmployeeDetails> businessAnalysts = allEmployees.stream()
                .filter(e -> e.getEmployeeRole().equals("Business Analyst"))
                .collect(Collectors.toList());

        List<EmployeeDetails> potentialBusinessAnalysts = addPotentialTeamMembersToList(businessAnalysts);


        for (LocalDate dayInProject : daysInProject) {
            if (projectRequirements.getNoOfBusinessAnalystsRequired() == 1) {

                if (bothTeamMembersOfSameRoleOnLeaveAcrossProjectDates(businessAnalysts, dayInProject)) {
                    potentialBusinessAnalysts.clear();
                    System.out.println("Both Business Analysts are on leave across the project dates");
                    break;

                } else
                    teamMemberOneOnLeaveAndTwoIsNotWhereRequiredNumberIsOne(businessAnalysts,
                            potentialBusinessAnalysts, dayInProject);

            } else if (projectRequirements.getNoOfBusinessAnalystsRequired() == 2) {

                if (bothTeamMembersOfSameRoleOnLeaveAcrossProjectDates(businessAnalysts, dayInProject)) {
                    System.out.println("Both Business Analysts are on leave across the project dates!");
                    potentialBusinessAnalysts.clear();
                    break;

                } else if (teamRoleMemberOneOnLeaveButTwoIsNot(businessAnalysts, dayInProject)) {
                    System.out.println("The Business Analyst " + businessAnalysts.get(0).getFirstName()
                            + " " + businessAnalysts.get(0).getLastName() + " is on leave across the date of " +
                            "the project! You must have two available in order to begin work!");
                    potentialBusinessAnalysts.clear();
                    break;

                } else if (teamRoleMemberTwoOnLeaveButOneIsNot(businessAnalysts, dayInProject)) {
                    System.out.println("The Business Analyst " + businessAnalysts.get(1).getFirstName()
                            + " " + businessAnalysts.get(1).getLastName() + " is on leave across the date of " +
                            "the project! You must have two available in order to begin work!");
                    potentialBusinessAnalysts.clear();
                    break;

                }
            }
        }

        if (projectRequirements.getNoOfBusinessAnalystsRequired() == 1 && potentialBusinessAnalysts.size() == 2) {
            potentialBusinessAnalysts.remove(1);
        }

        if (projectRequirements.getNoOfBusinessAnalystsRequired() == 2 && potentialBusinessAnalysts.size() == 2) {
            System.out.println("You have enough Business Analysts required ("
                    + projectRequirements.getNoOfBusinessAnalystsRequired() + ") to proceed!");
        }

        projectTeamMembers.addAll(potentialBusinessAnalysts);

        if (potentialBusinessAnalysts.size() != projectRequirements.getNoOfBusinessAnalystsRequired()) {
            System.out.println("You do not have enough Business Analysts free " +
                    "(" + projectRequirements.getNoOfBusinessAnalystsRequired() + ") to complete your project!");
        }

    }


    private void selectSoftwareEngineersNotOnLeave(ProjectRequirements projectRequirements,
                                                   List<EmployeeDetails> allEmployees,
                                                   List<EmployeeDetails> projectTeamMembers,
                                                   List<LocalDate> daysInProject) {

        List<EmployeeDetails> softwareEngineers = allEmployees.stream()
                .filter(e -> e.getEmployeeRole().equals("Software Engineer"))
                .collect(Collectors.toList());

        List<EmployeeDetails> potentialSoftwareEngineers = addPotentialTeamMembersToList(softwareEngineers);


        for (LocalDate dayInProject : daysInProject) {
            if (projectRequirements.getNoOfSoftwareEngineersRequired() == 1) {

                if (bothTeamMembersOfSameRoleOnLeaveAcrossProjectDates(softwareEngineers, dayInProject)) {
                    potentialSoftwareEngineers.clear();
                    System.out.println("Both Software Engineers are on leave across the project dates");
                    break;

                } else
                    teamMemberOneOnLeaveAndTwoIsNotWhereRequiredNumberIsOne(softwareEngineers,
                            potentialSoftwareEngineers, dayInProject);


            } else if (projectRequirements.getNoOfSoftwareEngineersRequired() == 2) {

                if (bothTeamMembersOfSameRoleOnLeaveAcrossProjectDates(softwareEngineers, dayInProject)) {
                    System.out.println("Both Software Engineers are on leave across the project dates!");
                    potentialSoftwareEngineers.clear();
                    break;

                } else if (teamRoleMemberOneOnLeaveButTwoIsNot(softwareEngineers, dayInProject)) {
                    System.out.println("The Software Engineer " + softwareEngineers.get(0).getFirstName()
                            + " " + softwareEngineers.get(0).getLastName() + " is on leave across the date of " +
                            "the project! You must have two available in order to begin work!");
                    potentialSoftwareEngineers.clear();
                    break;

                } else if (teamRoleMemberTwoOnLeaveButOneIsNot(softwareEngineers, dayInProject)) {
                    System.out.println("The Software Engineer " + softwareEngineers.get(1).getFirstName()
                            + " " + softwareEngineers.get(1).getLastName() + " is on leave across the date of " +
                            "the project! You must have two available in order to begin work!");
                    potentialSoftwareEngineers.clear();
                    break;

                }
            }
        }

        if (projectRequirements.getNoOfSoftwareEngineersRequired() == 1 && potentialSoftwareEngineers.size() == 2) {
            potentialSoftwareEngineers.remove(1);
        }

        if (projectRequirements.getNoOfSoftwareEngineersRequired() == 2 && potentialSoftwareEngineers.size() == 2) {
            System.out.println("You have enough Software Engineers required ("
                    + projectRequirements.getNoOfSoftwareEngineersRequired() + ") to proceed!");
        }

        projectTeamMembers.addAll(potentialSoftwareEngineers);

        if (potentialSoftwareEngineers.size() != projectRequirements.getNoOfSoftwareEngineersRequired()) {
            System.out.println("You do not have enough Software Engineers free " +
                    "(" + projectRequirements.getNoOfSoftwareEngineersRequired() + ") to complete your project!");
        }
    }


    private void selectTestAnalystsNotOnLeave(ProjectRequirements projectRequirements,
                                              List<EmployeeDetails> allEmployees,
                                              List<EmployeeDetails> projectTeamMembers,
                                              List<LocalDate> daysInProject) {

        List<EmployeeDetails> testAnalysts = allEmployees.stream()
                .filter(e -> e.getEmployeeRole().equals("Test Analyst"))
                .collect(Collectors.toList());

        List<EmployeeDetails> potentialTestAnalysts = addPotentialTeamMembersToList(testAnalysts);

        for (LocalDate dayInProject : daysInProject) {
            if (projectRequirements.getNoOfTestAnalystsRequired() == 1) {

                if (bothTeamMembersOfSameRoleOnLeaveAcrossProjectDates(testAnalysts, dayInProject)) {
                    potentialTestAnalysts.clear();
                    System.out.println("Both Test Analysts are on leave across the project dates");
                    break;

                } else {
                    teamMemberOneOnLeaveAndTwoIsNotWhereRequiredNumberIsOne(testAnalysts,
                            potentialTestAnalysts, dayInProject);
                }

            } else if (projectRequirements.getNoOfTestAnalystsRequired() == 2) {

                if (bothTeamMembersOfSameRoleOnLeaveAcrossProjectDates(testAnalysts, dayInProject)) {
                    System.out.println("Both Test Analysts are on leave across the project dates!");
                    potentialTestAnalysts.clear();
                    break;

                } else if (teamRoleMemberOneOnLeaveButTwoIsNot(testAnalysts, dayInProject)) {
                    System.out.println("The Test Analyst " + testAnalysts.get(0).getFirstName()
                            + " " + testAnalysts.get(0).getLastName() + " is on leave across the date of " +
                            "the project! You must have two available in order to begin work!");
                    potentialTestAnalysts.clear();
                    break;

                } else if (teamRoleMemberTwoOnLeaveButOneIsNot(testAnalysts, dayInProject)) {
                    System.out.println("The Test Analyst " + testAnalysts.get(1).getFirstName()
                            + " " + testAnalysts.get(1).getLastName() + " is on leave across the date of " +
                            "the project! You must have two available in order to begin work!");
                    potentialTestAnalysts.clear();
                    break;

                }
            }
        }

        if (projectRequirements.getNoOfTestAnalystsRequired() == 1 && potentialTestAnalysts.size() == 2) {
            potentialTestAnalysts.remove(1);
        }

        if (projectRequirements.getNoOfTestAnalystsRequired() == 2 && potentialTestAnalysts.size() == 2) {
            System.out.println("You have enough Test Analysts required ("
                    + projectRequirements.getNoOfTestAnalystsRequired() + ") to proceed!");
        }

        projectTeamMembers.addAll(potentialTestAnalysts);

        if (potentialTestAnalysts.size() != projectRequirements.getNoOfTestAnalystsRequired()) {
            System.out.println("You do not have enough Test Analysts free " +
                    "(" + projectRequirements.getNoOfTestAnalystsRequired() + ") to complete your project!");
        }
    }

    private List<EmployeeDetails> addPotentialTeamMembersToList(List<EmployeeDetails> teamMemberRole) {
        List<EmployeeDetails> potentialTeamMemberRole = new ArrayList<>();
        potentialTeamMemberRole.add(teamMemberRole.get(0));
        potentialTeamMemberRole.add(teamMemberRole.get(1));
        return potentialTeamMemberRole;
    }

    private void teamMemberOneOnLeaveAndTwoIsNotWhereRequiredNumberIsOne(List<EmployeeDetails> teamMemberRole,
                                                                         List<EmployeeDetails> potentialTeamMemberRole,
                                                                         LocalDate dayInProject) {

        if (teamRoleMemberOneOnLeaveButTwoIsNot(teamMemberRole, dayInProject)
                && potentialTeamMemberRole.contains(teamMemberRole.get(0))) {
            potentialTeamMemberRole.remove(0);


        } else if (teamRoleMemberTwoOnLeaveButOneIsNot(teamMemberRole, dayInProject)
                && potentialTeamMemberRole.contains(teamMemberRole.get(1))) {
            int index = potentialTeamMemberRole.size() - 1;
            potentialTeamMemberRole.remove(index);
        }
    }

    private boolean teamRoleMemberOneOnLeaveButTwoIsNot(List<EmployeeDetails> teamMemberRole, LocalDate dayInProject) {
        return (teamMemberRole.get(0).getDaysOnLeave().contains(dayInProject))
                && (!teamMemberRole.get(1).getDaysOnLeave().contains(dayInProject));
    }

    private boolean teamRoleMemberTwoOnLeaveButOneIsNot(List<EmployeeDetails> teamMemberRole, LocalDate dayInProject) {
        return (!teamMemberRole.get(0).getDaysOnLeave().contains(dayInProject))
                && (teamMemberRole.get(1).getDaysOnLeave().contains(dayInProject));
    }

    private boolean bothTeamMembersOfSameRoleOnLeaveAcrossProjectDates(List<EmployeeDetails> teamMemberRole,
                                                                       LocalDate dayInProject) {
        return (teamMemberRole.get(0).getDaysOnLeave().contains(dayInProject))
                && (teamMemberRole.get(1).getDaysOnLeave().contains(dayInProject));
    }


}
