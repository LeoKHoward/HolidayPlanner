package com.example.holidayplanner.services;

import com.example.holidayplanner.publicholidays.Event;
import com.example.holidayplanner.publicholidays.Root;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class HolidayServiceTest {

    private static final String TEST_PUBLIC_HOLIDAY_URL = "https://www.gov.uk/bank-holidays.json";

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private HolidayService testHolidayService = new HolidayService();

    @Test
    void testGetBankHolsJsonFromUrl() {

        Root testRoot = new Root();

        Mockito.when(restTemplate.getForEntity(TEST_PUBLIC_HOLIDAY_URL, Root.class))
          .thenReturn(new ResponseEntity(testRoot, HttpStatus.OK));




    }

    @Test
    void workoutProjectEndDateAndIfItIncludesBankHols() {
    }

    @Test
    void isDateBankHol() {
    }

    @Test
    void getBankHolsNamesInProjectDateRange() {
    }
}