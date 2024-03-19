package com.example.HolidayPay.controllers;

import com.example.HolidayPay.services.HolidayPayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/calculate")
public class HolidayPayController {
    HolidayPayService service = new HolidayPayService();

    @GetMapping
    public double calculateVacation(
            @RequestParam double salary,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) Integer days) {
        try {
            if (salary < 0) {
                throw new IllegalArgumentException("Salary cannot be less than 0.");
            }

            if (startDate != null && endDate != null) {
                return calculateWithDates(salary, startDate, endDate);
            } else if (days != null) {
                return calculateWithoutDates(salary, days);
            } else {
                throw new IllegalArgumentException("Either startDate and endDate or days must be provided.");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Incorrect date format. Please use format dd.MM.yyyy.");
        }
    }

    public double calculateWithDates(double salary, String startDate, String endDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);

            if (end.isBefore(start)) {
                throw new IllegalArgumentException("End date cannot be before start date.");
            }

            int count = service.getWorkingDaysCount(start, end);
            return service.getHolidayPay(count, salary);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Incorrect date format. Please use format dd.MM.yyyy.");
        }
    }

    public double calculateWithoutDates(double salary, int days) {
        return service.getHolidayPay(days, salary);
    }
}
