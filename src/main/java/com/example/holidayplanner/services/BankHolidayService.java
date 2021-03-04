package com.example.holidayplanner.services;

import com.example.holidayplanner.projectdetails.NewProjectDetails;
import com.example.holidayplanner.projectdetails.ProjectRequirements;
import com.example.holidayplanner.publicholidays.Event;
import com.example.holidayplanner.publicholidays.Root;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class BankHolidayService {

    private static final String PUBLIC_HOLIDAY_URL = "https://www.gov.uk/bank-holidays.json";
    private final LocalDate startOfYear = LocalDate.parse("2021-01-01");

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


        int businessDays = 0;
        while (businessDays < projectRequirements.getExpectedProjectLengthInDays()) {
            rangeOfDaysInProjectLifeSpan = rangeOfDaysInProjectLifeSpan.plusDays(1);
            if (!(rangeOfDaysInProjectLifeSpan.getDayOfWeek() == DayOfWeek.SATURDAY
                    || rangeOfDaysInProjectLifeSpan.getDayOfWeek() == DayOfWeek.SUNDAY
                    || isDateBankHol(rangeOfDaysInProjectLifeSpan, bankHols))) {
                ++businessDays;
            }
        }

        newProjectDetails.setProjectSuggestedEndDate(rangeOfDaysInProjectLifeSpan);

        List<String> bankHolsNames = getBankHolsNamesInProjectDateRange(bankHols,
                newProjectDetails.getProjectSuggestedStartDate(),
                newProjectDetails.getProjectSuggestedEndDate());

        System.out.println("\nStart Date: " + newProjectDetails.getProjectSuggestedStartDate()
                + "\nEnd date: " + newProjectDetails.getProjectSuggestedEndDate()
                + "\nAcross any Bank Holidays: " + bankHolsNames
                + "\n");
    }

    public boolean isDateBankHol(final LocalDate dateToCheck, List<Event> bankHolsList) {
        return bankHolsList.stream().anyMatch(bh -> bh.getDate().equals(dateToCheck));
    }

    public List<String> getBankHolsNamesInProjectDateRange(List<Event> bankHolsList, LocalDate startDate,
                                                           LocalDate endDate) {

        return bankHolsList.stream()
                .filter(bh -> bh.getDate().isAfter(startDate.minusDays(1))
                        && bh.getDate().isBefore(endDate.plusDays(1)))
                .map(Event::getTitle).collect(Collectors.toList());
    }
}
