package com.group3.mathModels;

public class LotkaVolterra {

    // Parameters
    private static final double cattleGrowthRate = 0.1; // Intrinsic growth rate of cattle
    private static final double cattleCarryingCapacity = 1000; // Carrying capacity for cattle
    private static final double horseGrowthRate = 0.1; // Intrinsic growth rate of horses
    private static final double horseCarryingCapacity = 1000; // Carrying capacity for horses
    private static final double deerGrowthRate = 0.1; // Intrinsic growth rate of deer
    private static final double deerCarryingCapacity = 1000; // Carrying capacity for deer
    private static final double wolfGrowthRate = 0.1; // Intrinsic growth rate of wolves
    private static final double wolfCarryingCapacity = 1000; // Carrying capacity for wolves
    private static final double grassGrowthRate = 0.1; // Intrinsic growth rate of grass
    private static final double grassCarryingCapacity = 1000; // Carrying capacity for grass
    private static final double competitionHorseOnCattle = 0.1; // Competition coefficient horses on cattle
    private static final double competitionDeerOnCattle = 0.1; // Competition coefficient deer on cattle
    private static final double competitionCattleOnHorses = 0.1; // Competition coefficient cattle on horses
    private static final double competitionDeerOnHorses = 0.1; // Competition coefficient deer on horses
    private static final double competitionHorsesOnDeer = 0.1; // Competition coefficient horses on deer
    private static final double competitionCattleOnDeer = 0.1; // Competition coefficient cattle on deer
    private static final double predationRateWolvesOnCattle = 0.01; // Predation rate of wolves on cattle
    private static final double predationRateWolvesOnHorses = 0.01; // Predation rate of wolves on horses
    private static final double predationRateWolvesOnDeer = 0.01; // Predation rate of wolves on deer
    private static final double conversionEfficiencyCattleToWolves = 0.1; // Conversion efficiency of cattle into wolf offspring
    private static final double conversionEfficiencyHorsesToWolves = 0.1; // Conversion efficiency of horses into wolf offspring
    private static final double conversionEfficiencyDeerToWolves = 0.1; // Conversion efficiency of deer into wolf offspring
    private static final double grassConsumptionRateCattle = 0.01; // Consumption rate of grass by cattle
    private static final double grassConsumptionRateHorses = 0.01; // Consumption rate of grass by horses
    private static final double grassConsumptionRateDeer = 0.01; // Consumption rate of grass by deer

    // Initial populations
    private static double cattlePopulation = 500;
    private static double horsePopulation = 500;
    private static double deerPopulation = 500;
    private static double wolfPopulation = 50;
    private static double grassBiomass = 1000;

    // Time step for the simulation
    private static final double timeStep = 0.1;

    public static void main(String[] args) {
        int steps = 1000;
        double[][] results = new double[steps][5];

        for (int i = 0; i < steps; i++) {
            results[i][0] = cattlePopulation;
            results[i][1] = horsePopulation;
            results[i][2] = deerPopulation;
            results[i][3] = wolfPopulation;
            results[i][4] = grassBiomass;

            // Update populations
            double deltaCattle = cattleGrowthRate * cattlePopulation * (1 - (cattlePopulation + competitionHorseOnCattle * horsePopulation + competitionDeerOnCattle * deerPopulation) / cattleCarryingCapacity) - predationRateWolvesOnCattle * wolfPopulation * cattlePopulation + grassConsumptionRateCattle * cattlePopulation * grassBiomass;
            double deltaHorses = horseGrowthRate * horsePopulation * (1 - (horsePopulation + competitionCattleOnHorses * cattlePopulation + competitionDeerOnHorses * deerPopulation) / horseCarryingCapacity) - predationRateWolvesOnHorses * wolfPopulation * horsePopulation + grassConsumptionRateHorses * horsePopulation * grassBiomass;
            double deltaDeer = deerGrowthRate * deerPopulation * (1 - (deerPopulation + competitionHorsesOnDeer * horsePopulation + competitionCattleOnDeer * cattlePopulation) / deerCarryingCapacity) - predationRateWolvesOnDeer * wolfPopulation * deerPopulation + grassConsumptionRateDeer * deerPopulation * grassBiomass;
            double deltaWolves = wolfGrowthRate * wolfPopulation * (conversionEfficiencyCattleToWolves * cattlePopulation + conversionEfficiencyHorsesToWolves * horsePopulation + conversionEfficiencyDeerToWolves * deerPopulation - wolfCarryingCapacity);
            double deltaGrass = grassGrowthRate * grassBiomass * (1 - grassBiomass / grassCarryingCapacity) - (grassConsumptionRateCattle * cattlePopulation + grassConsumptionRateHorses * horsePopulation + grassConsumptionRateDeer * deerPopulation) * grassBiomass;

            cattlePopulation += deltaCattle * timeStep;
            horsePopulation += deltaHorses * timeStep;
            deerPopulation += deltaDeer * timeStep;
            wolfPopulation += deltaWolves * timeStep;
            grassBiomass += deltaGrass * timeStep;
        }

    }

    public static double getCattleGrowthRate() {
        return cattleGrowthRate;
    }
}