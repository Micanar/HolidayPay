package com.example.HolidayPay;

import com.example.HolidayPay.services.HolidayPayService;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class HolidayPayServiceTest {
    HolidayPayService vacationService = new HolidayPayService();
    com.example.HolidayPay.controllers.HolidayPayController vacationController = new com.example.HolidayPay.controllers.HolidayPayController();

    @Test
    public void testGetWorkingDaysCount() {
        LocalDate startDate = LocalDate.of(2023, 5, 10);
        LocalDate endDate = LocalDate.of(2023, 5, 15);
        int expected = 4;
        int actual = vacationService.getWorkingDaysCount(startDate, endDate);
        assertEquals(expected, actual);
    }

    @Test
    public void testNewYearHoliday() {

        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 1, 10);
        int days = vacationService.getWorkingDaysCount(startDate, endDate);
        assertEquals(2, days);
    }

    @Test
    public void testWeekend() {
        LocalDate startDate = LocalDate.of(2023, 5, 15);
        LocalDate endDate = LocalDate.of(2023, 5, 28);
        int days = vacationService.getWorkingDaysCount(startDate, endDate);
        assertEquals(10, days);
    }
}

