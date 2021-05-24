package com.example.holidayplanner.services;

import com.example.holidayplanner.employee.EmployeeDetails;
import com.example.holidayplanner.projectdetails.NewProjectDetails;
import com.example.holidayplanner.projectdetails.ProjectRequirements;
import com.example.holidayplanner.publicholidays.Event;
import com.example.holidayplanner.publicholidays.Root;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class HolidayService {

    private static final String PUBLIC_HOLIDAY_URL = "https://www.gov.uk/bank-holidays.json";
    public LocalDate startOfYear = LocalDate.parse("2021-01-01");


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


        System.out.println(getAllDaysInProjectLifeSpan());

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

    public List<LocalDate> getAllDaysInProjectLifeSpan() {
        return newProjectDetails.getAllDaysInProjectLifeSpan();
    }

    public void changeStartDateIfTeamMembersNotAvailable(List<EmployeeDetails> projectTeamMembers) {

        LocalDate addOneDay = startOfYear.plusDays(1);

        for (EmployeeDetails projectTeamMember : projectTeamMembers) {
            if ((!projectTeamMember.getEmployeeRole().contains("Business")) ||
                    (!projectTeamMember.getEmployeeRole().contains("Software")) ||
                    (!projectTeamMember.getEmployeeRole().contains("Test"))) {
                startOfYear.plusDays(1);
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
