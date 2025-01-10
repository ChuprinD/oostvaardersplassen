package com.group3.mathModels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormulaVariables {
    private double cattleInitialPopulation = 80; // Initial population of cattle
    private double horseInitialPopulation = 70; // Initial population of horses
    private double deerInitialPopulation = 50; // Initial population of deer
    private double wolfInitialPopulation = 50; // Initial population of wolves

    private double wolfDeathRate = 0.01;

    private double cattleGrowthRate = 0.1; // Intrinsic growth rate of cattle
    private double cattleCarryingCapacity = 200; // Carrying capacity for cattle

    private double horseGrowthRate = 0.2; // Intrinsic growth rate of horses
    private double horseCarryingCapacity = 250; // Carrying capacity for horses

    private double deerGrowthRate = 0.3; // Intrinsic growth rate of deer
    private double deerCarryingCapacity = 300; // Carrying capacity for deer

    private double wolfGrowthRate = 0.1; // Intrinsic growth rate of wolves
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
}
