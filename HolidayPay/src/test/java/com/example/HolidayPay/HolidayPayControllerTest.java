package com.example.HolidayPay;

import com.example.HolidayPay.controllers.HolidayPayController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HolidayPayControllerTest {
    HolidayPayController vacationController = new HolidayPayController();

    @Test(expected = IllegalArgumentException.class)
    public void testGetWorkingDaysCount_EndDateBeforeStartDate() {
        vacationController.calculateVacation(21000, "15.05.2023", "10.05.2023",null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeSalary() {
        vacationController.calculateVacation(-21000, "10.05.2023", "15.05.2023",null);
    }

    @Test
    public void testSalary() {
        double salary = vacationController.calculateVacation(29300, "15.05.2023", "28.05.2023",null);
        assertEquals(10000, salary, 0.00);
    }

    @Test
    public void testSalary2() {
        double salary = vacationController.calculateVacation(29300, null, null, 14);
        assertEquals(14000, salary, 0.00);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDateFormat() {
        vacationController.calculateWithDates(1000, "2022-01-01", "2022-01-10");
    }
}
