package com.example.holidayplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URISyntaxException;


@SpringBootApplication
public class HolidayPlannerApplication {

  public static void main(String[] args) {
    System.out.println("You do not need to call PostStartupMethod it's magic - well annotations");
    System.out.println("You do need to run the Spring App");

    SpringApplication.run(HolidayPlannerApplication.class, args);
  }
}
