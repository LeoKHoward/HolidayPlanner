package com.example.holidayplanner.oldmethod;

import java.util.Calendar;

public class PublicHoliday {


    public boolean isPublicHoliday(Calendar calendar) {
        // check if it is a weekend
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        }

        // check if New Year's Day
        if (calendar.get(Calendar.MONTH) == Calendar.JANUARY && calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            return true;
        }

        // check if Good Friday 2021 (Friday before Easter Sunday)
        if (calendar.get(Calendar.MONTH) == Calendar.APRIL && calendar.get(Calendar.DAY_OF_MONTH) == 2) {
            return true;
        }

        // check if Easter Monday 2021 (Monday after Easter Sunday)
        if (calendar.get(Calendar.MONTH) == Calendar.APRIL && calendar.get(Calendar.DAY_OF_MONTH) == 5) {
            return true;
        }

        // check if Early May Bank Holiday (first Monday in May)
        if (calendar.get(Calendar.MONTH) == Calendar.MAY && calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 1
                && calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            return true;
        }

        // check if Spring Bank Holiday (last Monday in May)
        if (calendar.get(Calendar.MONTH) == Calendar.MAY && calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY
                && calendar.get(Calendar.DAY_OF_MONTH) > (31 - 7)) {
            return true;
        }

        // check if Summer Bank Holiday (last Monday in August)
        if (calendar.get(Calendar.MONTH) == Calendar.AUGUST && calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY
                && calendar.get(Calendar.DAY_OF_MONTH) > (31 - 7)) {
            return true;
        }

        // check if Christmas Day
        if (calendar.get(Calendar.MONTH) == Calendar.DECEMBER && calendar.get(Calendar.DAY_OF_MONTH) == 25) {
            return true;
        }

        // check if Boxing Day
        return calendar.get(Calendar.MONTH) == Calendar.DECEMBER && calendar.get(Calendar.DAY_OF_MONTH) == 26;

        // if none of these it is a work day


    }


}

