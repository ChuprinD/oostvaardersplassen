package com.group3.mathModels;

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
    private final double maxGrassBiomass = 2000; // Maximum biomass of grass

    private static double cattleInitialPopulation = DatabaseApp.getInitialPopulation("Heck cattle"); // Initial population of cattle
    private static double horseInitialPopulation = DatabaseApp.getInitialPopulation("Konik horses"); // Initial population of horses
    private static double deerInitialPopulation = DatabaseApp.getInitialPopulation("Red deer"); // Initial population of deer
    private double wolfInitialPopulation = 50; // Initial population of wolves
    private static double grassInitialBiomass = DatabaseApp.getInitialGrass(2022); // Initial biomass of grass

    private double wolfDeathRate = 0.01;

    private double cattleGrowthRate = 0.1; // Intrinsic growth rate of cattle
    private double cattleCarryingCapacity = 200; // Carrying capacity for cattle

    private double horseGrowthRate = 0.2; // Intrinsic growth rate of horses
    private double horseCarryingCapacity = 250; // Carrying capacity for horses

    private double deerGrowthRate = 0.3; // Intrinsic growth rate of deer
    private double deerCarryingCapacity = 300; // Carrying capacity for deer

    private double wolfGrowthRate = 2; // Intrinsic growth rate of wolves
    private double wolfCarryingCapacity = 200; // Carrying capacity for wolves

    private double grassGrowthRate = 0.1; // Intrinsic growth rate of grass
    private double grassCarryingCapacity = 1000; // Carrying capacity for grass

    private double competitionHorseOnCattle = 0.2; // Competition coefficient horses on cattle
    private double competitionDeerOnCattle = 0.4; // Competition coefficient deer on cattle

    private double competitionCattleOnHorses = 0.3; // Competition coefficient cattle on horses
    private double competitionDeerOnHorses = 0.5; // Competition coefficient deer on horses

    private double competitionHorsesOnDeer = 0.6; // Competition coefficient horses on deer
    private double competitionCattleOnDeer = 0.1; // Competition coefficient cattle on deer

    private double predationRateWolvesOnCattle = 0.08; // Predation rate of wolves on cattle
    private double predationRateWolvesOnHorses = 0.05; // Predation rate of wolves on horses
    private double predationRateWolvesOnDeer = 0.07; // Predation rate of wolves on deer

    private double conversionEfficiencyCattleToWolves = 0.2; // Conversion efficiency of cattle into wolf offspring
    private double conversionEfficiencyHorsesToWolves = 0.3; // Conversion efficiency of horses into wolf offspring
    private double conversionEfficiencyDeerToWolves = 0.4; // Conversion efficiency of deer into wolf offspring

    private double grassConsumptionRateCattle = 0.01; // Consumption rate of grass by cattle
    private double grassConsumptionRateHorses = 0.01; // Consumption rate of grass by horses
    private double grassConsumptionRateDeer = 0.01; // Consumption rate of grass by deer
    
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
        FormulaVariables.grassInitialBiomass = DatabaseApp.getInitialGrass(2022); // Initial biomass of grass
    }
}
