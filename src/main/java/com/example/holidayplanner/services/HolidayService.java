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

    public List<Event> getBankHols() {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Root> responseEntity = restTemplate.getForEntity(PUBLIC_HOLIDAY_URL, Root.class);

        return Objects.requireNonNull(responseEntity.getBody()).getEnglandAndWales().getEvents();
    }

    public void workoutProjectEndDateAndIfItIncludesBankHols(ProjectRequirements projectRequirements,
                                                             List<Event> bankHols) {

        NewProjectDetails newProjectDetails = new NewProjectDetails(null,
                null, null, null);


        newProjectDetails.setProjectSuggestedStartDate(startOfYear);

        LocalDate rangeOfDaysInProjectLifeSpan = LocalDate.of(2021, 1, 1);

        List<LocalDate> daysInProjectLifespan = new ArrayList<>();

        int businessDays = 0;
        while (businessDays < projectRequirements.getExpectedProjectLengthInDays()) {
            rangeOfDaysInProjectLifeSpan = rangeOfDaysInProjectLifeSpan.plusDays(1);
            if (!(rangeOfDaysInProjectLifeSpan.getDayOfWeek() == DayOfWeek.SATURDAY
                    || rangeOfDaysInProjectLifeSpan.getDayOfWeek() == DayOfWeek.SUNDAY
                    || isDateBankHol(rangeOfDaysInProjectLifeSpan, bankHols))) {
                ++businessDays;
                daysInProjectLifespan.add(rangeOfDaysInProjectLifeSpan);

            }

        }

        newProjectDetails.setProjectSuggestedStartDate(daysInProjectLifespan.get(0));

        /*
        The above sets the first project day to a day that isn't a weekend or bank holiday
         */



//        System.out.println(daysInProjectLifespan);


        newProjectDetails.setProjectSuggestedEndDate(rangeOfDaysInProjectLifeSpan);

        List<String> bankHolsNames = getBankHolsNamesInProjectDateRange(bankHols,
                newProjectDetails.getProjectSuggestedStartDate(),
                newProjectDetails.getProjectSuggestedEndDate());


        System.out.println("\nStart Date: " + newProjectDetails.getProjectSuggestedStartDate()
                + "\nEnd date: " + newProjectDetails.getProjectSuggestedEndDate()
                + "\nAcross any Bank Holidays: " + bankHolsNames
                + "\n");


    }

    public void findOutIfEmployeeOnAnnualLeaveDuringProjectLifeSpan(List<EmployeeDetails> teamMembers,
                                                                     List<LocalDate> daysInProjectLifespan) {

        for (EmployeeDetails teamMember : teamMembers) {
            long daysBetween = ChronoUnit.DAYS.between(teamMember.getAnnualLeaveStartDate(),
                    teamMember.getAnnualLeaveEndDate()) + 1;

            List<LocalDate> employeeAnnualLeaveDates =
                    LongStream.iterate(0, i -> i + 1)
                            .limit(daysBetween).mapToObj(i -> teamMember.getAnnualLeaveStartDate()
                            .plusDays(i))
                            .collect(Collectors.toList());


            determineWhenEmployeeIsOnAnnualLeave(daysInProjectLifespan, teamMember, employeeAnnualLeaveDates);
        }
    }

    private void determineWhenEmployeeIsOnAnnualLeave(List<LocalDate> daysInProjectLifespan,
                                                      EmployeeDetails teamMember,
                                                      List<LocalDate> employeeAnnualLeaveDates) {

        for (LocalDate dayInProjectLifespan : daysInProjectLifespan) {
            if (employeeAnnualLeaveDates.contains(dayInProjectLifespan)) {
                System.out.println("This person (" + teamMember.getFirstName() + " "
                        + teamMember.getLastName() + ") who is a " + teamMember.getEmployeeRole()
                        + " is on annual leave between the dates of "
                        + dayInProjectLifespan + "..." + teamMember.getAnnualLeaveEndDate() + "!");

                break;

            }

            while (employeeAnnualLeaveDates.contains(dayInProjectLifespan)) {
                teamMember.setOnAnnualLeave(true);
            }


//            Need to specify for each employee or else all flags set to true for all team members?

            System.out.println(teamMember.isOnAnnualLeave());
        }
    }

    public boolean isDateBankHol(final LocalDate dateToCheck, List<Event> bankHolsList) {
        return bankHolsList.stream().anyMatch(bh -> bh.getDate().equals(dateToCheck));
    }

    public List<String> getBankHolsNamesInProjectDateRange(List<Event> bankHolsNamesList, LocalDate startDate,
                                                           LocalDate endDate) {

        return bankHolsNamesList.stream()
                .filter(bh -> bh.getDate().isAfter(startDate.minusDays(1))
                        && bh.getDate().isBefore(endDate.plusDays(1)))
                .map(Event::getTitle).collect(Collectors.toList());
    }

}
