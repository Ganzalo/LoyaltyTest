package ru.fedorov.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fedorov.model.AverageVoteCalculator;
import ru.fedorov.model.loyaltyplant.LPCalculatorImpl;

import java.util.Map;

@RestController
public class Controller {

    AverageVoteCalculator calculator = new LPCalculatorImpl();

    @RequestMapping("/")
    public String getGenres() {

        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Integer, String> entry: calculator.getGenres().entrySet()) {
            stringBuilder.append(entry.getKey()).append("\t").append(entry.getValue()).append("<br />");
        }
        return stringBuilder.toString();
    }
}
