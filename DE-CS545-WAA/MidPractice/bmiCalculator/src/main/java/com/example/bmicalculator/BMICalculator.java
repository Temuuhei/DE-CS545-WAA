package com.example.bmicalculator;

public class BMICalculator {

    public static double calculateBmi(double weight, double height) {
        double bmi = weight / (height * height);
        return bmi;
    }
}
