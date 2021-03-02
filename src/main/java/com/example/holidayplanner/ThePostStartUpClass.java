package com.example.holidayplanner;

import com.example.holidayplanner.employee.EmployeeDetails;
import com.example.holidayplanner.projectdetails.ProjectRequirements;
import com.example.holidayplanner.publicholidays.Event;
import com.example.holidayplanner.services.EmployeeSetUp;
import com.example.holidayplanner.services.EventsLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Component
public class ThePostStartUpClass {

    private final EventsLoader eventsLoader;
    private final EmployeeSetUp employeeSetUp;


    public ThePostStartUpClass(EventsLoader eventsLoader, EmployeeSetUp employeeSetUp) {
        this.eventsLoader = eventsLoader;
        this.employeeSetUp = employeeSetUp;
    }

    @PostConstruct
    public void thisMethodWillRunPostConstruct() {
        List<Event> bankHols = showLoadingOfBankHols();
        List<EmployeeDetails> allEmployees = showSettingUpEmployees();

        ProjectRequirements projectOne = new ProjectRequirements(1,
                1, 1, 60);
        assignPeopleToProject(projectOne, bankHols, allEmployees);

        figureOutBestStartDateBasedOnTeamMembersAnnualLeave();


    }

    private List<Event> showLoadingOfBankHols() {
        List<Event> eventList = eventsLoader.getBankHols();

        for (Event b : eventList) {
            System.out.println("Date: " + b.getDate() + "    Title: " + b.getTitle());
        }

        return eventList;
    }

    public List<EmployeeDetails> showSettingUpEmployees() {
        List<EmployeeDetails> employees = employeeSetUp.setAllEmployeeDetails();

        for (EmployeeDetails employee : employees) {
            System.out.println(employee);
        }

        return employees;


    }

    List<EmployeeDetails> teamMembers = new ArrayList<>();


    public List<EmployeeDetails> assignPeopleToProject(ProjectRequirements projectRequirements,
                                                       List<Event> bankHols, List<EmployeeDetails> allEmployees) {

//        List<EmployeeDetails> teamMembers = new ArrayList<>();


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


        return teamMembers;


    }

    public void figureOutBestStartDateBasedOnTeamMembersAnnualLeave() {
        LocalDate startOfYear = LocalDate.parse("2021-01-01");
        LocalDate endOfYear = LocalDate.parse("2021-12-31");

        List<LocalDate> daysInYearList = Stream.iterate(startOfYear, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(startOfYear, endOfYear) + 1).collect(Collectors.toList());


        for (EmployeeDetails teamMember : teamMembers) {
            long daysBetween = ChronoUnit.DAYS.between(teamMember.getAnnualLeaveStartDate(),
                    teamMember.getAnnualLeaveEndDate()) + 1;

            List<LocalDate> totalDates =
                    LongStream.iterate(0, i -> i + 1)
                            .limit(daysBetween).mapToObj(i -> teamMember.getAnnualLeaveStartDate()
                            .plusDays(i))
                            .collect(Collectors.toList());


            for (LocalDate dayInYearList : daysInYearList) {
                if (totalDates.contains(dayInYearList)) {
                    System.out.println("This person (" + teamMember.getFirstName() + " "
                            + teamMember.getLastName() + ") is on annual leave between the dates of "
                            + dayInYearList + "..." + teamMember.getAnnualLeaveEndDate() + "!");

                }

            }

        }
    }
}
