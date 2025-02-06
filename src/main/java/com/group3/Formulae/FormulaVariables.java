package com.group3.Formulae;

import com.group3.database.DatabaseApp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FormulaVariables {
    private final double maxCattlePopulation = 2000; // Maximum population of cattle
    private final double maxHorsePopulation = 2000; // Maximum population of horses
    private final double maxDeerPopulation = 4000; // Maximum population of deer
    private final double maxWolfPopulation = 2000; // Maximum population of wolves
    private final double maxGrassBiomass = 10000000; // Maximum biomass of grass
    private static final double oostvaardersplassenArea = 56000000;
    private static final double grassDensity = 0.2;

    private static double cattleInitialPopulation = DatabaseApp.getInitialPopulation("Heck cattle"); // Initial population of cattle
    private static double horseInitialPopulation = DatabaseApp.getInitialPopulation("Konik horses"); // Initial population of horses
    private static double deerInitialPopulation = DatabaseApp.getInitialPopulation("Red deer"); // Initial population of deer
    private double wolfInitialPopulation = 10; // Initial population of wolves
    private static double grassInitialBiomass = (double) DatabaseApp.getInitialGrass(2022) * oostvaardersplassenArea * grassDensity / 100; // Initial biomass of grass

    private double wolfDeathRate = 0.5;
    private double wolfCarryingCapacity = 100;

    private static double cattleGrowthRate = LogisticGrowthAnalysis.calculateGrowthRate("Heck cattle"); // Intrinsic growth rate of cattle
    private double cattleCarryingCapacity = 460.24958512963246; // Carrying capacity for cattle
    private double cattleDeathRate = 0.03;

    private static double horseGrowthRate = LogisticGrowthAnalysis.calculateGrowthRate("Konik horses"); // Intrinsic growth rate of horses
    private double horseCarryingCapacity = 1197.367917470851; // Carrying capacity for horses
    private double horseDeathRate = 0.04;

    private static double deerGrowthRate = LogisticGrowthAnalysis.calculateGrowthRate("Red deer"); // Intrinsic growth rate of deer
    private double deerCarryingCapacity = 3285.7267847569196; // Carrying capacity for deer
    private double deerDeathRate = 0.1;

    private double grassGrowthRate = 1; // Intrinsic growth rate of grass
    private static double grassCarryingCapacity = (double) 22.97 * oostvaardersplassenArea * grassDensity / 100; // Carrying capacity for grass

    private double competitionHorseOnCattle = 0.4; // Competition coefficient horses on cattle
    private double competitionDeerOnCattle = 0.2; // Competition coefficient deer on cattle

    private double competitionCattleOnHorses = 0.5; // Competition coefficient cattle on horses
    private double competitionDeerOnHorses = 0.3; // Competition coefficient deer on horses

    private double competitionHorsesOnDeer = 0.3; // Competition coefficient horses on deer
    private double competitionCattleOnDeer = 0.3; // Competition coefficient cattle on deer

    
    private static double predationRateWolvesOnCattle = (double) 1 / cattleInitialPopulation; // Predation rate of wolves on cattle
    private static double predationRateWolvesOnHorses = (double) 1 / horseInitialPopulation; // Predation rate of wolves on horses
    private static double predationRateWolvesOnDeer = (double) 17 / deerInitialPopulation; // Predation rate of wolves on deer
  /*   
    private static double predationRateWolvesOnCattle = 0; // Predation rate of wolves on cattle
    private static double predationRateWolvesOnHorses = 0; // Predation rate of wolves on horses
    private static double predationRateWolvesOnDeer = 0;
*/
    private double conversionEfficiencyCattleToWolves = (double) 4 / 20; // Conversion efficiency of cattle into wolf offspring
    private double conversionEfficiencyHorsesToWolves = (double) 4 / 20; // Conversion efficiency of horses into wolf offspring
    private double conversionEfficiencyDeerToWolves = (double) 4 / 20; // Conversion efficiency of deer into wolf offspring

   
    private static double grassConsumptionRateCattle = (double) 5165 / grassInitialBiomass; // Consumption rate of grass by cattle
    private static double grassConsumptionRateHorses = (double) 3285 / grassInitialBiomass; // Consumption rate of grass by horses
    private static double grassConsumptionRateDeer = (double) 1825 / grassInitialBiomass; // Consumption rate of grass by deer
  /*    
    private static double grassConsumptionRateCattle = 0; // Consumption rate of grass by cattle
    private static double grassConsumptionRateHorses = 0; // Consumption rate of grass by horses
    private static double grassConsumptionRateDeer = 0; 
*/
    private double conversionEfficiencyGrassToCattle = (double) 1 / 5165;
    private double conversionEfficiencyGrassToHorse = (double) 1 / 3285;
    private double conversionEfficiencyGrassToDeer = (double) 1 / 1825;
    
    public double getCattleInitialPopulation() {
        return cattleInitialPopulation;
    }

    public double getHorseInitialPopulation() {
        return horseInitialPopulation;
    }

    public double getDeerInitialPopulation() {
        return deerInitialPopulation;
    }

    public double getGrassInitialBiomass() {
        return grassInitialBiomass;
    }

    public double getCattleGrowthRate() {
        return cattleGrowthRate;
    }

    public double getHorseGrowthRate() {
        return horseGrowthRate;
    }

    public double getDeerGrowthRate() {
        return deerGrowthRate;
    }

    public double getGrassCarryingCapacity() {
        return grassCarryingCapacity;
    }

    public double getGrassConsumptionRateCattle() {
        return grassConsumptionRateCattle;
    }

    public double getGrassConsumptionRateHorses() {
        return grassConsumptionRateHorses;
    }

    public double getGrassConsumptionRateDeer() {
        return grassConsumptionRateDeer;
    }

    public double getPredationRateWolvesOnCattle() {
        return predationRateWolvesOnCattle;
    }

    public double getPredationRateWolvesOnHorses() {
        return predationRateWolvesOnHorses;
    }

    public double getPredationRateWolvesOnDeer() {
        return predationRateWolvesOnDeer;
    }

    public double getGrassDensity() {
        return grassDensity;
    }

    public void setCattleInitialPopulation(double cattleInitialPopulation) {
        FormulaVariables.cattleInitialPopulation = cattleInitialPopulation;
    }

    public void setHorseInitialPopulation(double horseInitialPopulation) {
        FormulaVariables.horseInitialPopulation = horseInitialPopulation;
    }

    public void setDeerInitialPopulation(double deerInitialPopulation) {
        FormulaVariables.deerInitialPopulation = deerInitialPopulation;
    }

    public void setGrassInitialBiomass(double grassInitialBiomass) {
        FormulaVariables.grassInitialBiomass = grassInitialBiomass;
    }

    public void setDefaultInitialPopulations() {
        FormulaVariables.cattleInitialPopulation = DatabaseApp.getInitialPopulation("Heck cattle"); // Initial population of cattle
        FormulaVariables.horseInitialPopulation = DatabaseApp.getInitialPopulation("Konik horses"); // Initial population of horses
        FormulaVariables.deerInitialPopulation = DatabaseApp.getInitialPopulation("Red deer"); // Initial population of deer
        FormulaVariables.grassInitialBiomass = (double) DatabaseApp.getInitialGrass(2022) * oostvaardersplassenArea * grassDensity / 100; // Initial biomass of grass
    }
}
