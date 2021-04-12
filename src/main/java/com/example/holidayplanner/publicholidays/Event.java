package com.example.holidayplanner.publicholidays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
public class Event {

    /*
    Gets the Title and Date of bank holidays from the Event field in the JSON when the GOV.uk API is called
    */

    private String title;

    private LocalDate date;


}
