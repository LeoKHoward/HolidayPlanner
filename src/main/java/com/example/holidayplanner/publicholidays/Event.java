package com.example.holidayplanner.publicholidays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class Event {

    private String title;

    private String date;


}
