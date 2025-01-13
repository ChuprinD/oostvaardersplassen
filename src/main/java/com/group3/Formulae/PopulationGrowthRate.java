package com.group3.Formulae;

public class PopulationGrowthRate {
    public static void main(String[] args) {
        // Example data for three different animals
        int[][] populations = {
            {1000, 1030, 1060}, // Cow
            {500, 520, 540},    // Horse
            {200, 210, 220}     // Deer
        };
        int[][] births = {
            {50, 55, 60},       // Cow
            {30, 32, 34},       // Horse
            {10, 11, 12}        // Deer
        };
        int[][] deaths = {
            {20, 25, 30},       // Cow
            {10, 12, 14},       // Horse
            {5, 6, 7}           // Deer
        };

        int numberOfAnimals = populations.length;

        for (int animal = 0; animal < numberOfAnimals; animal++) {
            double totalGrowthRate = 0;
            int years = populations[animal].length;

            for (int year = 0; year < years; year++) {
                int netGrowth = births[animal][year] - deaths[animal][year];
                double growthRate = (double) netGrowth / populations[animal][year];
                totalGrowthRate += growthRate;
            }

            double averageGrowthRate = totalGrowthRate / years;
            String[] animalNames = {"Cow", "Horse", "Deer"};
            System.out.printf("Average Growth Rate for %s: %.4f%%\n", animalNames[animal], averageGrowthRate * 100);
        }
    }
}