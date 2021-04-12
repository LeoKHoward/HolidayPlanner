package com.example.holidayplanner.publicholidays;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Root {

    /*
    Specifically gets bank holidays for England/Wales as Scottish/Irish holidays are sometimes different
    */

    @JsonProperty("england-and-wales")
    private EnglandAndWales englandAndWales;

}
