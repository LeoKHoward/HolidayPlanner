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

    private final HolidayService holidayService;

    public EmployeeService(HolidayService holidayService) {
        this.holidayService = holidayService;
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


    /*
    Doesnt iterate through every day in project, just the first one
    */
    private void selectBusinessAnalystsNotOnLeave(ProjectRequirements projectRequirements,
                                                  List<EmployeeDetails> allEmployees,
                                                  List<EmployeeDetails> projectTeamMembers,
                                                  List<LocalDate> daysInProject) {

        List<EmployeeDetails> businessAnalysts = allEmployees.stream()
                .filter(e -> e.getEmployeeRole().equals("Business Analyst"))
                .collect(Collectors.toList());


        if (projectRequirements.getNoOfBusinessAnalystsRequired() == 1) {
            for (LocalDate dayInProject : daysInProject) {
                if ((businessAnalysts.get(0).getDaysOnLeave().contains(dayInProject))
                        && (!businessAnalysts.get(1).getDaysOnLeave().contains(dayInProject))) {
                    businessAnalysts.remove(0);
                    System.out.println("You have enough Business Analysts required ("
                            + projectRequirements.getNoOfBusinessAnalystsRequired() + ") to proceed!");
                    break;
                } else if ((!businessAnalysts.get(0).getDaysOnLeave().contains(dayInProject)) &&
                        (businessAnalysts.get(1).getDaysOnLeave().contains(dayInProject))) {
                    businessAnalysts.remove(1);
                    System.out.println("You have enough Business Analysts required ("
                            + projectRequirements.getNoOfBusinessAnalystsRequired() + ") to proceed!");
                    break;
                } else if ((businessAnalysts.get(0).getDaysOnLeave().contains(dayInProject))
                        && (businessAnalysts.get(1).getDaysOnLeave().contains(dayInProject))) {
                    businessAnalysts.clear();
                    System.out.println("Both Business Analysts are on leave across the project dates");
                    break;
                } else {
                    businessAnalysts.remove(1);
                    System.out.println("You have enough Business Analysts required ("
                            + projectRequirements.getNoOfBusinessAnalystsRequired() + ") to proceed!");
                    break;
                }
            }

        } else if (projectRequirements.getNoOfBusinessAnalystsRequired() == 2) {
            for (LocalDate dayInProject : daysInProject) {
                if ((businessAnalysts.get(0).getDaysOnLeave().contains(dayInProject))
                        && (!businessAnalysts.get(1).getDaysOnLeave().contains(dayInProject))) {
                    System.out.println("The Business Analyst " + businessAnalysts.get(0).getFirstName()
                            + " " + businessAnalysts.get(0).getLastName() + " is on leave across the date of " +
                            "the project! You must have two available in order to begin work!");
                    businessAnalysts.clear();
                    break;
                } else if ((!businessAnalysts.get(0).getDaysOnLeave().contains(dayInProject))
                        && (businessAnalysts.get(1).getDaysOnLeave().contains(dayInProject))) {
                    System.out.println("The Business Analyst " + businessAnalysts.get(1).getFirstName()
                            + " " + businessAnalysts.get(1).getLastName() + " is on leave across the date of " +
                            "the project! You must have two available in order to begin work!");
                    businessAnalysts.clear();
                    break;
                } else if ((businessAnalysts.get(0).getDaysOnLeave().contains(dayInProject))
                        && (businessAnalysts.get(1).getDaysOnLeave().contains(dayInProject))) {
                    System.out.println("Both Business Analysts are on leave across the project dates!");
                    businessAnalysts.clear();
                } else {
                    System.out.println("You have enough Business Analysts required ("
                            + projectRequirements.getNoOfBusinessAnalystsRequired() + ") to proceed!");
                    break;
                }
            }
        }

        projectTeamMembers.addAll(businessAnalysts);

        if (businessAnalysts.size() != projectRequirements.getNoOfBusinessAnalystsRequired()) {
            System.out.println("You do not have enough Business Analysts free " +
                    "(" + projectRequirements.getNoOfBusinessAnalystsRequired() + ") to complete your project!");
        }
    }

    public void selectSoftwareEngineersNotOnLeave(ProjectRequirements projectRequirements,
                                                  List<EmployeeDetails> allEmployees,
                                                  List<EmployeeDetails> projectTeamMembers,
                                                  List<LocalDate> daysInProject) {

        List<EmployeeDetails> softwareEngineers = allEmployees.stream()
                .filter(e -> e.getEmployeeRole().equals("Software Engineer"))
                .collect(Collectors.toList());


        if (projectRequirements.getNoOfSoftwareEngineersRequired() == 1) {
            for (LocalDate dayInProject : daysInProject) {
                if ((softwareEngineers.get(0).getDaysOnLeave().contains(dayInProject))
                        && (!softwareEngineers.get(1).getDaysOnLeave().contains(dayInProject))) {
                    softwareEngineers.remove(0);
                    System.out.println("You have enough Software Engineers required ("
                            + projectRequirements.getNoOfSoftwareEngineersRequired() + ") to proceed!");
                    break;
                } else if ((!softwareEngineers.get(0).getDaysOnLeave().contains(dayInProject)) &&
                        (softwareEngineers.get(1).getDaysOnLeave().contains(dayInProject))) {
                    softwareEngineers.remove(1);
                    System.out.println("You have enough Software Engineers required ("
                            + projectRequirements.getNoOfSoftwareEngineersRequired() + ") to proceed!");
                    break;
                } else if ((softwareEngineers.get(0).getDaysOnLeave().contains(dayInProject))
                        && (softwareEngineers.get(1).getDaysOnLeave().contains(dayInProject))) {
                    softwareEngineers.clear();
                    System.out.println("Both Software Engineers are on leave across the project dates");
                    break;
                } else {
                    softwareEngineers.remove(1);
                    System.out.println("You have enough Software Engineers required ("
                            + projectRequirements.getNoOfSoftwareEngineersRequired() + ") to proceed!");
                    break;
                }
            }
        } else if (projectRequirements.getNoOfSoftwareEngineersRequired() == 2) {
            for (LocalDate dayInProject : daysInProject) {
                if ((softwareEngineers.get(0).getDaysOnLeave().contains(dayInProject))
                        && (!softwareEngineers.get(1).getDaysOnLeave().contains(dayInProject))) {
                    System.out.println("The Software Engineer " + softwareEngineers.get(0).getFirstName()
                            + " " + softwareEngineers.get(0).getLastName() + " is on leave across the date of " +
                            "the project! You must have two available in order to begin work!");
                    softwareEngineers.clear();
                    break;
                } else if ((!softwareEngineers.get(0).getDaysOnLeave().contains(dayInProject))
                        && (softwareEngineers.get(1).getDaysOnLeave().contains(dayInProject))) {
                    System.out.println("The Software Engineer " + softwareEngineers.get(1).getFirstName()
                            + " " + softwareEngineers.get(1).getLastName() + " is on leave across the date of " +
                            "the project! You must have two available in order to begin work!");
                    softwareEngineers.clear();
                    break;
                } else if ((softwareEngineers.get(0).getDaysOnLeave().contains(dayInProject))
                        && (softwareEngineers.get(1).getDaysOnLeave().contains(dayInProject))) {
                    System.out.println("Both Software Engineers are on leave across the project dates!");
                    softwareEngineers.clear();
                    break;
                } else {
                    System.out.println("You have enough Software Engineers required ("
                            + projectRequirements.getNoOfSoftwareEngineersRequired() + ") to proceed!");
                    break;
                }
            }
        }

        projectTeamMembers.addAll(softwareEngineers);

        if (softwareEngineers.size() != projectRequirements.getNoOfSoftwareEngineersRequired()) {
            System.out.println("You do not have enough Software Engineers free " +
                    "(" + projectRequirements.getNoOfSoftwareEngineersRequired() + ") to complete your project!");
        }

    }


    public void selectTestAnalystsNotOnLeave(ProjectRequirements projectRequirements,
                                             List<EmployeeDetails> allEmployees,
                                             List<EmployeeDetails> projectTeamMembers,
                                             List<LocalDate> daysInProject) {

        List<EmployeeDetails> testAnalysts = allEmployees.stream()
                .filter(e -> e.getEmployeeRole().equals("Test Analyst"))
                .collect(Collectors.toList());

        if (projectRequirements.getNoOfTestAnalystsRequired() == 1) {
            for (LocalDate dayInProject : daysInProject) {
                if ((testAnalysts.get(0).getDaysOnLeave().contains(dayInProject))
                        && (!testAnalysts.get(1).getDaysOnLeave().contains(dayInProject))) {
                    testAnalysts.remove(0);
                    System.out.println("You have enough Test Analysts required ("
                            + projectRequirements.getNoOfTestAnalystsRequired() + ") to proceed!");
                    break;
                } else if ((!testAnalysts.get(0).getDaysOnLeave().contains(dayInProject)) &&
                        (testAnalysts.get(1).getDaysOnLeave().contains(dayInProject))) {
                    testAnalysts.remove(1);
                    System.out.println("You have enough Test Analysts required ("
                            + projectRequirements.getNoOfTestAnalystsRequired() + ") to proceed!");
                    break;
                } else if ((testAnalysts.get(0).getDaysOnLeave().contains(dayInProject))
                        && (testAnalysts.get(1).getDaysOnLeave().contains(dayInProject))) {
                    testAnalysts.clear();
                    System.out.println("Both Test Analysts are on leave across the project dates");
                    break;
                } else {
                    testAnalysts.remove(1);
                    System.out.println("You have enough Test Analysts required ("
                            + projectRequirements.getNoOfTestAnalystsRequired() + ") to proceed!");
                    break;
                }
            }

        } else if (projectRequirements.getNoOfTestAnalystsRequired() == 2) {
            for (LocalDate dayInProject : daysInProject) {
                if ((testAnalysts.get(0).getDaysOnLeave().contains(dayInProject))
                        && (!testAnalysts.get(1).getDaysOnLeave().contains(dayInProject))) {
                    System.out.println("The Test Analyst " + testAnalysts.get(0).getFirstName()
                            + " " + testAnalysts.get(0).getLastName() + " is on leave across the date of " +
                            "the project! You must have two available in order to begin work!");
                    testAnalysts.clear();
                    break;
                } else if ((!testAnalysts.get(0).getDaysOnLeave().contains(dayInProject))
                        && (testAnalysts.get(1).getDaysOnLeave().contains(dayInProject))) {
                    System.out.println("The Test Analyst " + testAnalysts.get(1).getFirstName()
                            + " " + testAnalysts.get(1).getLastName() + " is on leave across the date of " +
                            "the project! You must have two available in order to begin work!");
                    testAnalysts.clear();
                    break;
                } else if ((testAnalysts.get(0).getDaysOnLeave().contains(dayInProject))
                        && (testAnalysts.get(1).getDaysOnLeave().contains(dayInProject))) {
                    System.out.println("Both Test Analysts are on leave across the project dates!");
                    testAnalysts.clear();
                } else {
                    System.out.println("You have enough Test Analysts required ("
                            + projectRequirements.getNoOfTestAnalystsRequired() + ") to proceed!");
                    break;
                }
            }
        }

        projectTeamMembers.addAll(testAnalysts);

        if (testAnalysts.size() != projectRequirements.getNoOfTestAnalystsRequired()) {
            System.out.println("You do not have enough Test Analysts free " +
                    "(" + projectRequirements.getNoOfTestAnalystsRequired() + ") to complete your project!");
        }

    }


}
