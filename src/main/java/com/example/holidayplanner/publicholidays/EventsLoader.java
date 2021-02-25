package com.example.holidayplanner.publicholidays;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.List;

@Component
public class EventsLoader {

    private static final String PUBLIC_HOLIDAY_URL = "https://www.gov.uk/bank-holidays.json";

    public List<Event> getBankHols() throws URISyntaxException {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Root> responseEntity = restTemplate.getForEntity(PUBLIC_HOLIDAY_URL, Root.class);

        return responseEntity.getBody().getEnglandAndWales().getEvents();
    }
}
