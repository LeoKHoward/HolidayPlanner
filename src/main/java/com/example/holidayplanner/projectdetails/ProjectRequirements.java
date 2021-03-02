package com.example.holidayplanner.projectdetails;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProjectRequirements {

    private int noOfBusinessAnalystsRequired;
    private int noOfSoftwareEngineersRequired;
    private int noOfTestAnalystsRequired;
    private int expectedProjectLengthInDays;


}
