package com.incubytes.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class StringCalculatorController {

    @PostMapping("/add")
    public int add(@RequestBody String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        // Check for custom delimiter
        Matcher matcher = Pattern.compile("//(\\[.*\\])\n(.+)").matcher(numbers);
        if (matcher.matches()) {
            String delimiter = matcher.group(1);
            numbers = matcher.group(2);
            numbers = numbers.replaceAll(delimiter, ",");
        }

        // Split the numbers based on comma or newline
        String[] numbersArray = numbers.split(",|\n");

        // Convert string numbers to integers
        List<Integer> numbersList = new ArrayList<>();
        for (String num : numbersArray) {
            numbersList.add(Integer.parseInt(num));
        }

        // Handle negative numbers
        List<Integer> negatives = new ArrayList<>();
        for (int num : numbersList) {
            if (num < 0) {
                negatives.add(num);
            }
        }
        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negative numbers not allowed: " + negatives);
        }

        // Filter numbers greater than 1000
        numbersList.removeIf(num -> num > 1000);

        // Return the sum of the numbers
        return numbersList.stream().mapToInt(Integer::intValue).sum();
    }
}
