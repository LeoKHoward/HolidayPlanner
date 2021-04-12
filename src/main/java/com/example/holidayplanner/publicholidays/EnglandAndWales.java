package com.example.holidayplanner.publicholidays;

import lombok.Data;

import java.util.List;


@Data
public class EnglandAndWales {

    /*
    Gets the Division and Events from the JSON when the GOV.uk API is called
    */

    private String division;

    private List<Event> events;


}
