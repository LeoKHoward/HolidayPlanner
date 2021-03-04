package com.example.holidayplanner.services;


import com.example.holidayplanner.employee.EmployeeDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Component
public class ProjectService {

    private final LocalDate startOfYear = LocalDate.parse("2021-01-01");
    private final LocalDate endOfYear = LocalDate.parse("2021-12-31");

    public void figureOutBestStartDateBasedOnTeamMembersAnnualLeave(List<EmployeeDetails> teamMembers) {

        List<LocalDate> daysInYearList = Stream.iterate(startOfYear, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(startOfYear, endOfYear) + 1).collect(Collectors.toList());


        for (EmployeeDetails teamMember : teamMembers) {
            long daysBetween = ChronoUnit.DAYS.between(teamMember.getAnnualLeaveStartDate(),
                    teamMember.getAnnualLeaveEndDate()) + 1;

            List<LocalDate> employeeAnnualLeaveDates =
                    LongStream.iterate(0, i -> i + 1)
                            .limit(daysBetween).mapToObj(i -> teamMember.getAnnualLeaveStartDate()
                            .plusDays(i))
                            .collect(Collectors.toList());


            for (LocalDate dayInYearList : daysInYearList) {
                if (employeeAnnualLeaveDates.contains(dayInYearList)) {
                    System.out.println("This person (" + teamMember.getFirstName() + " "
                            + teamMember.getLastName() + ") who is a " + teamMember.getEmployeeRole()
                            + " is on annual leave between the dates of "
                            + dayInYearList + "..." + teamMember.getAnnualLeaveEndDate() + "!");

                    break;

                }
                while (employeeAnnualLeaveDates.contains(dayInYearList)) {
                    teamMember.setOnAnnualLeave(true);
                }
            }
        }
    }



}
