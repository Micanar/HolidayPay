package com.example.HolidayPay;

import com.example.HolidayPay.controllers.HolidayPayController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HolidayPayControllerTest {
    HolidayPayController vacationController = new HolidayPayController();

    @Test(expected = IllegalArgumentException.class)
    public void testGetWorkingDaysCount_EndDateBeforeStartDate() {
        vacationController.calculateVacation(21000, "15.05.2023", "10.05.2023");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeSalary() {
        vacationController.calculateVacation(-21000, "10.05.2023", "15.05.2023");
    }

    @Test
    public void testSalary() {
        double salary = vacationController.calculateVacation(21000, "15.05.2023", "28.05.2023");
        assertEquals(10000, salary, 0.00);
    }
}
