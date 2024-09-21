package ru.nexnon.calculator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.HashSet;
import java.util.Set;

public class VacationPayCalculator {

    private static final Set<MonthDay> holidays = new HashSet<>();
    static {
        holidays.add(MonthDay.of(1, 1));
        holidays.add(MonthDay.of(1, 2));
        holidays.add(MonthDay.of(1, 3));
        holidays.add(MonthDay.of(1, 4));
        holidays.add(MonthDay.of(1, 5));
        holidays.add(MonthDay.of(1, 6));
        holidays.add(MonthDay.of(1, 7));
        holidays.add(MonthDay.of(1, 8));
        holidays.add(MonthDay.of(2, 23));
        holidays.add(MonthDay.of(3, 8));
        holidays.add(MonthDay.of(5, 1));
        holidays.add(MonthDay.of(5, 9));
        holidays.add(MonthDay.of(6, 12));
        holidays.add(MonthDay.of(11, 4));
    }

    private final double AVARAGE_DAYS_OF_MONTH = 29.3;

    private double averageSalary;
    private int vacationDays;

    private LocalDate startVacation;
    private LocalDate endVacation;

    public VacationPayCalculator(double averageSalary, int vacationDays) {
        this.averageSalary = averageSalary;
        this.vacationDays = vacationDays;
    }

    public VacationPayCalculator(double averageSalary, LocalDate startVacation, LocalDate endVacation) {
        this.averageSalary = averageSalary;
        this.startVacation = startVacation;
        this.endVacation = endVacation;
    }

    private void calculateVacationDays(){
        LocalDate currentDate = startVacation;
        int currentVacationDays = 0;
        while (!currentDate.isAfter(endVacation)){
            if(isVacationDay(currentDate)){
                currentVacationDays++;
            }
            currentDate = currentDate.plusDays(1);
        }
        vacationDays = currentVacationDays;
    }

    private boolean isVacationDay(LocalDate date){
        if(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY){
            return false;
        }
        MonthDay monthDay = MonthDay.from(date);
        return !holidays.contains(monthDay);
    }

    public double calculate(){
        if (vacationDays == 0){
            calculateVacationDays();
        }
        double result = averageSalary / AVARAGE_DAYS_OF_MONTH * vacationDays;
        return result;
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(double averageSalary) {
        this.averageSalary = averageSalary;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }
}
