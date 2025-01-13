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
    private final double maxGrassBiomass = 2000; // Maximum biomass of grass

    private static double cattleInitialPopulation = DatabaseApp.getInitialPopulation("Heck cattle"); // Initial population of cattle
    private static double horseInitialPopulation = DatabaseApp.getInitialPopulation("Konik horses"); // Initial population of horses
    private static double deerInitialPopulation = DatabaseApp.getInitialPopulation("Red deer"); // Initial population of deer
    private double wolfInitialPopulation = 10; // Initial population of wolves
    private static double grassInitialBiomass = DatabaseApp.getInitialGrass(2022) * 56000000 * 0.2 / 100; // Initial biomass of grass

    private double wolfDeathRate = 0.06;

    private static double cattleGrowthRate = PopulationGrowthRate.calculateGrowthRate("Heck cattle"); // Intrinsic growth rate of cattle
    private double cattleCarryingCapacity = 474.5348; // Carrying capacity for cattle

    private static double horseGrowthRate = PopulationGrowthRate.calculateGrowthRate("Konik horses"); // Intrinsic growth rate of horses
    private double horseCarryingCapacity = 1177.2487; // Carrying capacity for horses

    private static double deerGrowthRate = PopulationGrowthRate.calculateGrowthRate("Red deer"); // Intrinsic growth rate of deer
    private double deerCarryingCapacity = 3273.6363; // Carrying capacity for deer

    private double grassGrowthRate = 0.5; // Intrinsic growth rate of grass
    private static double grassCarryingCapacity = DatabaseApp.getInitialGrass(2022) * 56000000 * 0.2 / 100; // Carrying capacity for grass

    private double competitionHorseOnCattle = 0.2; // Competition coefficient horses on cattle
    private double competitionDeerOnCattle = 0.4; // Competition coefficient deer on cattle

    private double competitionCattleOnHorses = 0.3; // Competition coefficient cattle on horses
    private double competitionDeerOnHorses = 0.5; // Competition coefficient deer on horses

    private double competitionHorsesOnDeer = 0.6; // Competition coefficient horses on deer
    private double competitionCattleOnDeer = 0.1; // Competition coefficient cattle on deer

    private double predationRateWolvesOnCattle = 0.012; // Predation rate of wolves on cattle
    private double predationRateWolvesOnHorses = 0.06; // Predation rate of wolves on horses
    private double predationRateWolvesOnDeer = 0.18; // Predation rate of wolves on deer

    private double conversionEfficiencyCattleToWolves = 0.07; // Conversion efficiency of cattle into wolf offspring
    private double conversionEfficiencyHorsesToWolves = 0.07; // Conversion efficiency of horses into wolf offspring
    private double conversionEfficiencyDeerToWolves = 0.07; // Conversion efficiency of deer into wolf offspring

    private double grassConsumptionRateCattle = 5.165; // Consumption rate of grass by cattle
    private double grassConsumptionRateHorses = 3.285; // Consumption rate of grass by horses
    private double grassConsumptionRateDeer = 1825; // Consumption rate of grass by deer
    
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
