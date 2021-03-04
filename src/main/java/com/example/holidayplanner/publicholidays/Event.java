package com.example.holidayplanner.publicholidays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
public class Event {

    private String title;

    private LocalDate date;


}
