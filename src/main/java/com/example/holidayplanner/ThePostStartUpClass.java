package com.example.holidayplanner;

import com.example.holidayplanner.employee.EmployeeDetails;
import com.example.holidayplanner.publicholidays.Event;
import com.example.holidayplanner.services.EmployeeSetUp;
import com.example.holidayplanner.services.EventsLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URISyntaxException;
import java.util.List;

@Component
public class ThePostStartUpClass {

    private final EventsLoader eventsLoader;
    private final EmployeeSetUp employeeSetUp;


    public ThePostStartUpClass(EventsLoader eventsLoader, EmployeeSetUp employeeSetUp) {
        this.eventsLoader = eventsLoader;
        this.employeeSetUp = employeeSetUp;
    }

    @PostConstruct
    public void thisMethodWillRunPostConstruct() throws URISyntaxException {
        showLoadingOfBankHols();
        showSettingUpEmployees();
    }

    private void showSettingUpEmployees() {
        List<EmployeeDetails> employees = employeeSetUp.setAllEmployeeDetails();

        for( EmployeeDetails employee :employees ) {
            System.out.println(employee);
        }
    }

    private void showLoadingOfBankHols() throws URISyntaxException {
        List<Event> eventList = eventsLoader.getBankHols();

        for ( Event b : eventList ) {
            System.out.println("Date: " + b.getDate() + "    Title: " + b.getTitle());
        }
    }
}
