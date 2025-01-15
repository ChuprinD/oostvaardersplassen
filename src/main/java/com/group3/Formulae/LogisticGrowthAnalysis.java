package com.group3.Formulae;

import java.util.List;

import com.group3.database.DatabaseApp;


public class LogisticGrowthAnalysis {
    public static double calculateGrowthRate(String species) {
        List<Double> populations = DatabaseApp.getAnnualPopulation(species);
        List<Double> births = DatabaseApp.getAnnualBirths(species);
        List<Double> deaths = DatabaseApp.getAnnualMortality(species);

        int years = populations.size();
        double totalGrowthRate = 0;
        double growthRate = 0;
        for (int year = 0; year < years; year++) {
            double netGrowth = births.get(year) - deaths.get(year);
            if (populations.get(year) == 0) {
                growthRate = 0;
            } else {
                growthRate = (double) netGrowth / populations.get(year);
            }
            totalGrowthRate += growthRate;
        }
        double averageGrowthRate = (double) totalGrowthRate / years;
        return averageGrowthRate;
    }
    
}