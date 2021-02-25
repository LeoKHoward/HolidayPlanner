package com.example.holidayplanner.publicholidays;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Root {

    @JsonProperty("england-and-wales")
    private EnglandAndWales englandAndWales;

}
