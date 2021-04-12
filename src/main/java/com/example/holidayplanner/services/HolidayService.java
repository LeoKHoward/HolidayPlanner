package com.example.holidayplanner.services;

import com.example.holidayplanner.employee.EmployeeDetails;
import com.example.holidayplanner.employee.EmployeeSetUp;
import com.example.holidayplanner.projectdetails.NewProjectDetails;
import com.example.holidayplanner.projectdetails.ProjectRequirements;
import com.example.holidayplanner.publicholidays.Event;
import com.example.holidayplanner.publicholidays.Root;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Component
public class HolidayService {

    private static final String PUBLIC_HOLIDAY_URL = "https://www.gov.uk/bank-holidays.json";
    public final LocalDate startOfYear = LocalDate.parse("2021-01-01");

    NewProjectDetails newProjectDetails = new NewProjectDetails(null,
            null, null, null, null);

    public List<Event> getBankHols() {

        /*
        Call GOV.uk API to get JSON and pull out just England and Wales holidays
        */

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Root> responseEntity = restTemplate.getForEntity(PUBLIC_HOLIDAY_URL, Root.class);

        return Objects.requireNonNull(responseEntity.getBody()).getEnglandAndWales().getEvents();
    }


    public void workoutProjectEndDateAndIfItIncludesBankHols(ProjectRequirements projectRequirements,
                                                             List<Event> bankHols) {

        /*
        Set Project Start Date always to 01/01/2021
        */
        LocalDate rangeOfDaysInProjectLifeSpan = startOfYear;

        newProjectDetails.setProjectSuggestedStartDate(rangeOfDaysInProjectLifeSpan);


        /*
        Create new list for all the business days in the project
        */
        List<LocalDate> daysInProjectLifespan = new ArrayList<>();

        int businessDays = 0;

        while (businessDays < projectRequirements.getExpectedProjectLengthInDays()) {

            rangeOfDaysInProjectLifeSpan = rangeOfDaysInProjectLifeSpan.plusDays(1);

            /*
            If any day in project is a saturday/sunday/bank holiday then ignore it
            */
            if (!(rangeOfDaysInProjectLifeSpan.getDayOfWeek() == DayOfWeek.SATURDAY
                    || rangeOfDaysInProjectLifeSpan.getDayOfWeek() == DayOfWeek.SUNDAY
                    || isDateBankHol(rangeOfDaysInProjectLifeSpan, bankHols))) {
                ++businessDays;

                /*
                Add all business days to the list daysInProjectLifespan
                */
                daysInProjectLifespan.add(rangeOfDaysInProjectLifeSpan);

            }

        }


        /*
        Set Project Start Date for first possible business day (04/01/2021)
        */
        newProjectDetails.setProjectSuggestedStartDate(daysInProjectLifespan.get(0));

        /*
        Prints all the business days in project life
        */
        newProjectDetails.setAllDaysInProjectLifeSpan(daysInProjectLifespan);

        System.out.println(newProjectDetails.getAllDaysInProjectLifeSpan());

        /*
        Set project end date to value of rangeOfDaysInProjectLifeSpan which will now be the last
        business day in the length of project based on expected length set in ThePostStartUp Class
        */
        newProjectDetails.setProjectSuggestedEndDate(rangeOfDaysInProjectLifeSpan);

        /*
        Get the names of any bank holidays that occur in the duration of the project
        */
        List<String> bankHolsNames = getBankHolsNamesInProjectDateRange(bankHols,
                newProjectDetails.getProjectSuggestedStartDate(),
                newProjectDetails.getProjectSuggestedEndDate());


        /*
        Print out start date, end date and if any bank holidays occur
        */
        System.out.println("\nStart Date: " + newProjectDetails.getProjectSuggestedStartDate()
                + "\nEnd date: " + newProjectDetails.getProjectSuggestedEndDate()
                + "\nAcross any Bank Holidays: " + bankHolsNames
                + "\n");


    }

    /*
    This method checks the selected team members annual leave dates from EmployeeSetUp Class
    against the business days in the project lifespan from method above
    */
    public void determineWhenEmployeeIsOnAnnualLeave(List<EmployeeDetails> projectTeamMembers) {

        /*
        Get the business days from project lifespan from method above
        */
        List<LocalDate> daysInProjectLifespan = newProjectDetails.getAllDaysInProjectLifeSpan();

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


    /*
    Check if any day in project date range is a bank holiday
    */
    public boolean isDateBankHol(final LocalDate dateToCheck, List<Event> bankHolsList) {
        return bankHolsList.stream().anyMatch(bh -> bh.getDate().equals(dateToCheck));
    }


    /*
    Get the names of any bank holidays that occur in project date range
    */
    public List<String> getBankHolsNamesInProjectDateRange(List<Event> bankHolsNamesList, LocalDate startDate,
                                                           LocalDate endDate) {

        return bankHolsNamesList.stream()
                .filter(bh -> bh.getDate().isAfter(startDate.minusDays(1))
                        && bh.getDate().isBefore(endDate.plusDays(1)))
                .map(Event::getTitle).collect(Collectors.toList());
    }

}
