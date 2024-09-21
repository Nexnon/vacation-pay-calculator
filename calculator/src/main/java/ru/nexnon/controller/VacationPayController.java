package ru.nexnon.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nexnon.calculator.VacationPayCalculator;

import java.time.LocalDate;

@RestController
public class VacationPayController {

    @GetMapping("/calculacte")
    public String calculate(
            @RequestParam(value = "salary") double salary,
            @RequestParam(value = "days", required = false) Integer vacationDays,
            @RequestParam(value = "startVacation", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startVacation,
            @RequestParam(value = "endVacation", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endVacation){

        if (vacationDays == null && startVacation != null && endVacation != null){
            VacationPayCalculator calculator;
            if(startVacation.isBefore(endVacation)){
                calculator = new VacationPayCalculator(salary, startVacation, endVacation);
            } else{
                calculator = new VacationPayCalculator(salary, endVacation, startVacation);
            }
            return String.format("%.2f", calculator.calculate());
        } else if (vacationDays != null && startVacation == null && endVacation == null ) {
            VacationPayCalculator calculator = new VacationPayCalculator(salary, vacationDays);
            return String.format("%.2f",calculator.calculate());
        } else {
            return "0";
        }

    }
}
