package com.example.holidayplanner.publicholidays;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URISyntaxException;
import java.util.List;

@Component
public class ThePostStartUpClass {

    private final EventsLoader eventsLoader;

    public ThePostStartUpClass(EventsLoader eventsLoader) {
        this.eventsLoader = eventsLoader;
    }

    @PostConstruct
    public void thisMethodWillRunPostConstruct() throws URISyntaxException {
        List<Event> eventList = eventsLoader.getBankHols();

        for ( Event b : eventList ) {
            System.out.println("Date: " + b.getDate() + "    Title: " + b.getTitle());
        }
    }
}
