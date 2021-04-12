package com.example.holidayplanner.projectdetails;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProjectRequirements {

    /*
    These are specified in ThePostStartUp Class
    */

    private int noOfBusinessAnalystsRequired;
    private int noOfSoftwareEngineersRequired;
    private int noOfTestAnalystsRequired;
    private int expectedProjectLengthInDays;


}
