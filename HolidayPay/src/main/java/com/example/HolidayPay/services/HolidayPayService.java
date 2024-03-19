package com.example.HolidayPay.services;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Arrays;
import java.util.List;

@Service
public class HolidayPayService {
    private static final double AVERAGE_DAYS_IN_MONTH = 29.3;

    private final List<MonthDay> holidays = Arrays.asList(
            MonthDay.of(1, 1), // Новый год
            MonthDay.of(1, 2), // Новый год
            MonthDay.of(1, 3), // Новый год
            MonthDay.of(1, 4), // Новый год
            MonthDay.of(1, 5), // Новый год
            MonthDay.of(1, 6), // Новый год
            MonthDay.of(1, 7), // Рождество Христово
            MonthDay.of(2, 23), // День защитника Отечества
            MonthDay.of(3, 8), // Международный женский день
            MonthDay.of(5, 1), // Праздник Весны и Труда
            MonthDay.of(5, 9), // День Победы
            MonthDay.of(6, 12), // День России
            MonthDay.of(11, 4) // День народного единства
    );

    public double getHolidayPay(int count, double salary) {
        double dailyPay = salary / AVERAGE_DAYS_IN_MONTH; // Средний заработок за день
        double result = count * dailyPay;
        return Math.round(result * 100.0) / 100.0; //Округление до 2х знаков после запятой
    }

    public int getWorkingDaysCount(LocalDate startDate, LocalDate endDate) {
        int count = 0;
        for (LocalDate date = startDate; date.isEqual(endDate) || date.isBefore(endDate); date = date.plusDays(1)) {
            if (!isWeekend(date) && !isHoliday(date)) {
                count++;
            }
        }
        return count;
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    private boolean isHoliday(LocalDate date) {
        MonthDay monthDay = MonthDay.from(date);
        return holidays.contains(monthDay);
    }
}

