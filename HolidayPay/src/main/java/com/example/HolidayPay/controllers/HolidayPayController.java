package com.example.HolidayPay.controllers;

import com.example.HolidayPay.services.HolidayPayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/calculate")
public class HolidayPayController {
    @GetMapping
    public double calculateVacation(@RequestParam double salary, @RequestParam String startDate, @RequestParam String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        HolidayPayService service = new HolidayPayService();
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be less than 0.");
        }
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }
        return service.getHolidayPay(start, end, salary);
    }
}
