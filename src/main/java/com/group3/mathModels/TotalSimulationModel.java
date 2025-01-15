package com.group3.mathModels;

import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.DormandPrince853Integrator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.ui.VerticalAlignment;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.group3.Formulae.FormulaVariables;
import javafx.embed.swing.SwingNode;

import java.awt.Font;

import java.awt.BasicStroke;
import java.awt.Color;

public class TotalSimulationModel implements MathModel {
    FormulaVariables formulaVariables;

    public XYSeriesCollection calculateData() {
        double t0 = 0.0;
        double t1 = 30.0;
        double dt = 0.1;
        int steps = (int) ((t1 - t0) / dt);

        double[] initialPopulations = { formulaVariables.getCattleInitialPopulation(),
                formulaVariables.getHorseInitialPopulation(), formulaVariables.getDeerInitialPopulation(),
                formulaVariables.getWolfInitialPopulation(), formulaVariables.getGrassInitialBiomass() };

        double[] seriesCattle = new double[steps];
        double[] seriesHorse = new double[steps];
        double[] seriesDeer = new double[steps];
        double[] seriesWolf = new double[steps];
        double[] seriesGrass = new double[steps];
        double[] seriesTime = new double[steps];

        seriesCattle[0] = formulaVariables.getCattleInitialPopulation();
        seriesHorse[0] = formulaVariables.getHorseInitialPopulation();
        seriesDeer[0] = formulaVariables.getDeerInitialPopulation();
        seriesWolf[0] = formulaVariables.getWolfInitialPopulation();
        seriesGrass[0] = formulaVariables.getGrassInitialBiomass();
        seriesTime[0] = 0.0;

        for (int i = 1; i < steps; i++) {
            seriesTime[i] = i * dt;
        }

        FirstOrderIntegrator integrator = new DormandPrince853Integrator(1.0e-8, 100.0, 1.0e-10, 1.0e-10);

        double[] currentState = initialPopulations.clone();

        //for (int i = 0; i < 5; i++) {
        //    System.out.println(currentState[i]);
        //}
        //System.out.println(formulaVariables.getGrassConsumptionRateCattle());
        //System.out.println("First" + (formulaVariables.getGrassGrowthRate() - (formulaVariables.getGrassConsumptionRateCattle() * currentState[0] + formulaVariables.getGrassConsumptionRateHorses() * currentState[1] + formulaVariables.getGrassConsumptionRateDeer() * currentState[2])) * currentState[4]);
        //System.out.println(formulaVariables.getConversionEfficiencyGrassToDeer());
        /*System.out.println("( " + -formulaVariables.getDeerDeathRate() + " + "
                + formulaVariables.getConversionEfficiencyGrassToDeer() + " * " + currentState[4] + " - "
                + formulaVariables.getPredationRateWolvesOnDeer() + " * " + currentState[3] + " )* " + currentState[2]
                + " = "
                + (-formulaVariables.getDeerDeathRate()
                        + formulaVariables.getConversionEfficiencyGrassToDeer() * currentState[4]
                        - formulaVariables.getPredationRateWolvesOnDeer() * currentState[3]) * currentState[2]);*/

        for (int i = 1; i < steps; i++) {
            integrator.integrate(new PredatorPreyEquations(formulaVariables), t0, currentState, seriesTime[i],
                    currentState);

            seriesCattle[i] = currentState[0];
            seriesHorse[i] = currentState[1];
            seriesDeer[i] = currentState[2];
            seriesWolf[i] = currentState[3];
            seriesGrass[i] = currentState[4];
            t0 += dt;
        }

        XYSeries deerSeries = new XYSeries("Deer");
        XYSeries cattleSeries = new XYSeries("Cattle");
        XYSeries horsesSeries = new XYSeries("Horses");
        XYSeries wolfSeries = new XYSeries("Wolves");
        XYSeries grassSeries = new XYSeries("Grass");

        for (int i = 0; i < steps; i++) {
            deerSeries.add(seriesTime[i], seriesDeer[i]);
            cattleSeries.add(seriesTime[i], seriesCattle[i]);
            horsesSeries.add(seriesTime[i], seriesHorse[i]);
            wolfSeries.add(seriesTime[i], seriesWolf[i]);
            grassSeries.add(seriesTime[i], seriesGrass[i]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(deerSeries);
        dataset.addSeries(cattleSeries);
        dataset.addSeries(horsesSeries);
        dataset.addSeries(wolfSeries);
        //dataset.addSeries(grassSeries);

        return dataset;
    }
    
    public double[][] calculateSecondData() {
        double t0 = 0.0;
        double t1 = 30.0;
        double dt = 0.1;
        int steps = (int) ((t1 - t0) / dt);

        double[] initialPopulations = { formulaVariables.getCattleInitialPopulation(), formulaVariables.getHorseInitialPopulation(), formulaVariables.getDeerInitialPopulation(), formulaVariables.getWolfInitialPopulation(), formulaVariables.getGrassInitialBiomass() };
        
        double[] seriesCattle = new double[steps];
        double[] seriesHorse = new double[steps];
        double[] seriesDeer = new double[steps];
        double[] seriesWolf = new double[steps];
        double[] seriesGrass = new double[steps];
        double[] seriesTime = new double[steps];
        
        seriesCattle[0] = formulaVariables.getCattleInitialPopulation();
        seriesHorse[0] = formulaVariables.getHorseInitialPopulation();
        seriesDeer[0] = formulaVariables.getDeerInitialPopulation();
        seriesWolf[0] = formulaVariables.getWolfInitialPopulation();
        seriesGrass[0] = formulaVariables.getGrassInitialBiomass();
        seriesTime[0] = 0.0;

        for (int i = 1; i < steps; i++) {
            seriesTime[i] = i * dt;
        }

        FirstOrderIntegrator integrator = new DormandPrince853Integrator(1.0e-8, 100.0, 1.0e-10, 1.0e-10);
        
        double[] currentState = initialPopulations.clone();

        //for (int i = 0; i < 5; i++) {
        //    System.out.println(currentState[i]);
        //}
        //System.out.println(formulaVariables.getGrassConsumptionRateCattle());
        //System.out.println("First" + (formulaVariables.getGrassGrowthRate() - (formulaVariables.getGrassConsumptionRateCattle() * currentState[0] + formulaVariables.getGrassConsumptionRateHorses() * currentState[1] + formulaVariables.getGrassConsumptionRateDeer() * currentState[2])) * currentState[4]);
        //System.out.println(formulaVariables.getConversionEfficiencyGrassToDeer()); 
        //System.out.println("( " + -formulaVariables.getDeerDeathRate() + " + " + formulaVariables.getConversionEfficiencyGrassToDeer() + " * " + currentState[4] + " - " + formulaVariables.getPredationRateWolvesOnDeer() + " * " + currentState[3] + " )* " + currentState[2] + " = " + (-formulaVariables.getDeerDeathRate() + formulaVariables.getConversionEfficiencyGrassToDeer() * currentState[4] - formulaVariables.getPredationRateWolvesOnDeer() * currentState[3] ) *  currentState[2]);
        
        for (int i = 1; i < steps; i++) {
            integrator.integrate(new PredatorPreyEquations(formulaVariables), t0, currentState, seriesTime[i],
                    currentState);
            seriesCattle[i] = currentState[0];
            seriesHorse[i] = currentState[1];
            seriesDeer[i] = currentState[2];
            seriesWolf[i] = currentState[3];
            seriesGrass[i] = currentState[4];
            t0 += dt;
        }
        
        double[][] combinedArray = new double[steps][3];

        for (int i = 0; i < steps; i++) {
            combinedArray[i][0] = seriesCattle[i] + seriesHorse[i] + seriesDeer[i]; 
            combinedArray[i][1] = seriesWolf[i]; 
            combinedArray[i][2] = seriesGrass[i]; 
        }

        return combinedArray;
    }

    @Override
    public ChartPanel getGraph(double width, double height, FormulaVariables formulaVariables) {
        this.formulaVariables = formulaVariables;
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                "",
                "Time",
                "Population",
                calculateData(),
                PlotOrientation.VERTICAL,
                true, true, false);

        xylineChart.setPadding(new RectangleInsets(20, 10, 10, 10));

        XYPlot plot = xylineChart.getXYPlot();
        plot.setDomainGridlinesVisible(false);
        plot.setRangeGridlinesVisible(false);
        plot.setOutlinePaint(null);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

        ValueAxis xAxis = plot.getDomainAxis();
        ValueAxis yAxis = plot.getRangeAxis();
        xAxis.setLabelFont(new Font("Arial", Font.BOLD, 18));
        yAxis.setLabelFont(new Font("Arial", Font.BOLD, 18));

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesPaint(2, Color.GREEN);
        renderer.setSeriesPaint(3, Color.ORANGE);
        renderer.setSeriesPaint(4, Color.BLACK);

        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));
        renderer.setSeriesStroke(2, new BasicStroke(2.0f));
        renderer.setSeriesStroke(3, new BasicStroke(2.0f));
        renderer.setSeriesStroke(4, new BasicStroke(2.0f));

        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);
        renderer.setSeriesShapesVisible(2, false);
        renderer.setSeriesShapesVisible(3, false);
        renderer.setSeriesShapesVisible(4, false);
        plot.setRenderer(renderer);

        LegendTitle legend = xylineChart.getLegend();
        legend.setPosition(RectangleEdge.RIGHT);
        legend.setBackgroundPaint(new Color(255, 255, 255, 200));
        legend.setFrame(new org.jfree.chart.block.BlockBorder(Color.LIGHT_GRAY));
        legend.setItemFont(new Font("Arial", Font.PLAIN, 16));
        legend.setPadding(10, 10, 10, 10);
        legend.setMargin(new RectangleInsets(0, 10, 10, 0));
        legend.setVerticalAlignment(VerticalAlignment.TOP);

        plot.getDomainAxis().setTickLabelFont(new Font("Arial", Font.PLAIN, 16));
        plot.getRangeAxis().setTickLabelFont(new Font("Arial", Font.PLAIN, 16));

        ChartPanel chartPanel = new ChartPanel(xylineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension((int) width, (int) height));

        return chartPanel;
    }

    @Override
    public void getSecondGraph(double width, double height, FormulaVariables formulaVariables, SwingNode swingNode) {
    }
    
    static class PredatorPreyEquations implements FirstOrderDifferentialEquations {
        FormulaVariables formulaVariables;

        private static int steps = 0;
        
        @Override
        public int getDimension() {
            return 5;
        }

        public PredatorPreyEquations(FormulaVariables formulaVariables) {
            this.formulaVariables = formulaVariables;
        }

        @Override
        public void computeDerivatives(double t, double[] y, double[] yDot) {
            double cattlePopulation = y[0];
            double horsePopulation = y[1];
            double deerPopulation = y[2];
            double wolfPopulation = y[3];
            double grassBiomass = y[4];
            
            yDot[0] = (-formulaVariables.getCattleDeathRate()
                    + (formulaVariables.getConversionEfficiencyGrassToCattle() * grassBiomass)
                            / (1.0 + 0.533 * grassBiomass)
                    - formulaVariables.getPredationRateWolvesOnCattle() * wolfPopulation
                            / (1.0 + 0.925 * cattlePopulation))
                    * cattlePopulation;
            
            yDot[1] = (-formulaVariables.getHorseDeathRate()
                    + (formulaVariables.getConversionEfficiencyGrassToHorse() * grassBiomass)
                            / (1.0 + 0.783 * grassBiomass)
                    - formulaVariables.getPredationRateWolvesOnHorses() * wolfPopulation
                            / (1.0 + 0.895 * horsePopulation))
                    * horsePopulation;
            
            yDot[2] = (-formulaVariables.getDeerDeathRate()
                    + (formulaVariables.getConversionEfficiencyGrassToDeer() * grassBiomass)
                            / (1.0 + 0.575 * grassBiomass)
                    - formulaVariables.getPredationRateWolvesOnDeer() * wolfPopulation
                            / (1.0 + 0.866 * deerPopulation))
                    * deerPopulation;
            
            yDot[3] = (-formulaVariables.getWolfDeathRate() +
                    (formulaVariables.getConversionEfficiencyCattleToWolves() * cattlePopulation
                            / (1.0 + 0.925 * cattlePopulation)
                    + formulaVariables.getConversionEfficiencyHorsesToWolves() * horsePopulation
                            / (1.0 + 0.895 * horsePopulation)
                    + formulaVariables.getConversionEfficiencyDeerToWolves() * deerPopulation
                            / (1.0 + 0.866 * deerPopulation)))
                    * wolfPopulation;
            
            yDot[4] = (formulaVariables.getGrassGrowthRate() * (1 - grassBiomass /  formulaVariables.getGrassCarryingCapacity()) 
                    - ((formulaVariables.getGrassConsumptionRateCattle() * cattlePopulation) / (1.0 + 0.533 * grassBiomass)
                            + (formulaVariables.getGrassConsumptionRateHorses() * horsePopulation) / (1.0 + 0.783 * grassBiomass)
                            + (formulaVariables.getGrassConsumptionRateDeer() * deerPopulation) / (1.0 + 0.575 * grassBiomass))) 
                    * grassBiomass;
            /* 
            double handlingTimeWolvesOnCattle = (2.0 / (24.0 * 365.0)) / 20.0;
            double handlingTimeWolvesOnHorse = (1.5 / (24.0 * 365.0)) / 20.0;
            double handlingTimeWolvesOnDeer = (1.0 / (24.0 * 365.0)) / 20.0;

            double handlingTimeCattleOnGrass = 0.75 / 5165.0;
            double handlingTimeHorseOnGrass = 0.7 / 3285.0;
            double handlingTimeDeerOnGrass = 0.6 / 2000.0;

            double firstPartYDot0 = -formulaVariables.getCattleDeathRate();
            double secondPartYDot0 = ((1.0 /handlingTimeCattleOnGrass) * grassBiomass)
                    / (formulaVariables.getConversionEfficiencyGrassToCattle() * (grassBiomass + grassBiomass));
            double thirdPartYDot0 = ((1.0 / handlingTimeWolvesOnCattle) * wolfPopulation)
                    / (formulaVariables.getPredationRateWolvesOnCattle() * (cattlePopulation + cattlePopulation));
            yDot[0] = (firstPartYDot0 + secondPartYDot0 - thirdPartYDot0) * cattlePopulation;

            double firstPartYDot1 = -formulaVariables.getHorseDeathRate();  
            double secondPartYDot1 = ((1.0 / handlingTimeHorseOnGrass) * grassBiomass)
                    / (formulaVariables.getConversionEfficiencyGrassToHorse() * (grassBiomass + grassBiomass));
            double thirdPartYDot1 = ((1.0 / handlingTimeWolvesOnHorse) * wolfPopulation)
                    / (formulaVariables.getPredationRateWolvesOnHorses() * (horsePopulation + horsePopulation));
            yDot[1] = (firstPartYDot1 + secondPartYDot1 - thirdPartYDot1) * horsePopulation;

            double firstPartYDot2 = -formulaVariables.getDeerDeathRate();
            double secondPartYDot2 = ((1.0 / handlingTimeDeerOnGrass) * grassBiomass)
                    / (formulaVariables.getConversionEfficiencyGrassToDeer() * (grassBiomass + grassBiomass));
            double thirdPartYDot2 = ((1.0 / handlingTimeWolvesOnDeer) * wolfPopulation)
                    / (formulaVariables.getPredationRateWolvesOnDeer() * (deerPopulation + deerPopulation));
            yDot[2] = (firstPartYDot2 + secondPartYDot2 - thirdPartYDot2) * deerPopulation;

            double firstPartYDot3 = -formulaVariables.getWolfDeathRate();
            double cattlePopulationPartYDot3 = ((1.0 / handlingTimeWolvesOnCattle) * cattlePopulation)
                    / (cattlePopulation + cattlePopulation);
            double horsePopulationPartYDot3 = ((1.0 / handlingTimeWolvesOnHorse) * horsePopulation)
                    / (horsePopulation + horsePopulation);
            double deerPopulationPartYDot3 = ((1.0 / handlingTimeWolvesOnDeer) * deerPopulation)
                    / (deerPopulation + deerPopulation);
            yDot[3] = (firstPartYDot3 + (cattlePopulationPartYDot3 + horsePopulationPartYDot3 + deerPopulationPartYDot3)) * wolfPopulation;
            
            double firstPartYDot4 = formulaVariables.getGrassGrowthRate()
                    * (1 - grassBiomass / formulaVariables.getGrassCarryingCapacity());
            double cattlePopulationPartYDot4 = ((1.0 / handlingTimeCattleOnGrass) * cattlePopulation)
                    / (formulaVariables.getGrassConsumptionRateCattle() * (grassBiomass + grassBiomass));
            double horsePopulationPartYDot4 = ((1.0 / handlingTimeHorseOnGrass) * horsePopulation)
                    / (formulaVariables.getGrassConsumptionRateHorses() * (grassBiomass + grassBiomass));
            double deerPopulationPartYDot4 = ((1.0 / handlingTimeDeerOnGrass) * (deerPopulation))
                    / (formulaVariables.getGrassConsumptionRateDeer() * (grassBiomass + grassBiomass));
            yDot[4] = (firstPartYDot4 - (cattlePopulationPartYDot4 + horsePopulationPartYDot4 + deerPopulationPartYDot4)) * grassBiomass;
            */
            /*if (steps++ < 30) {
                System.out.println(-formulaVariables.getDeerDeathRate() + " + " + formulaVariables.getConversionEfficiencyGrassToDeer() + " * " + grassBiomass + " - " + formulaVariables.getPredationRateWolvesOnDeer() + " * " + wolfPopulation + " ) * " +deerPopulation + " = " + yDot[2]);
            } */// dW/dt
        }
    }
}