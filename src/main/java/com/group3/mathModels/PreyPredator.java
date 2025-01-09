package com.group3.mathModels;

import java.util.ArrayList;

import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
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

import java.awt.Font;
import java.awt.BasicStroke;
import java.awt.Color;

public class PreyPredator implements MathModel {

    public XYSeriesCollection calculateData(){
        double numberSteps = 1000;
        double timeStep = 0.1;

        double deltaCattle;
        ArrayList<Double> seriesCattle = new ArrayList<Double>();
        seriesCattle.add(cattlePopulation);

        double deltaHorse;
        ArrayList<Double> seriesHorse = new ArrayList<Double>();
        seriesHorse.add(horsePopulation);

        double deltaDeer;
        ArrayList<Double>  seriesDeer = new ArrayList<Double>();
        seriesDeer.add(deerPopulation);

        double deltaWolf;
        ArrayList<Double> seriesWolf = new ArrayList<Double>();
        seriesWolf.add(wolfPopulation);

        ArrayList<Double> seriesTime = new ArrayList<Double>();
        seriesTime.add(0.0);

        for (int i = 1; i < numberSteps; i++) {
            seriesTime.add(seriesTime.get(0) + i * timeStep);
            deltaCattle = cattleGrowthRate * cattlePopulation
                    * (1 - (cattlePopulation + competitionHorseOnCattle * seriesHorse.get(i - 1)
                            + competitionDeerOnCattle * seriesDeer.get(i - 1)) / cattleCarryingCapacity)
                    - predationRateWolvesOnCattle * wolfPopulation * cattlePopulation;

            deltaHorse = horseGrowthRate * horsePopulation
                    * (1 - (horsePopulation + competitionCattleOnHorses * seriesCattle.get(i - 1)
                            + competitionDeerOnHorses * seriesDeer.get(i - 1)) / horseCarryingCapacity) - predationRateWolvesOnHorses * wolfPopulation * horsePopulation;

            deltaDeer = deerGrowthRate * deerPopulation
                    * (1 - (deerPopulation + competitionHorsesOnDeer * seriesHorse.get(i - 1)
                            + competitionCattleOnDeer * seriesCattle.get(i - 1)) / deerCarryingCapacity) - predationRateWolvesOnDeer * wolfPopulation * deerPopulation;

            deltaWolf = wolfGrowthRate * wolfPopulation * (conversionEfficiencyCattleToWolves * seriesCattle.get(i - 1) + conversionEfficiencyHorsesToWolves * seriesHorse.get(i - 1) + conversionEfficiencyDeerToWolves * seriesDeer.get(i - 1) - wolfCarryingCapacity) - wolfDeathRate * wolfPopulation;
            
            seriesCattle.add(deltaCattle * timeStep + seriesCattle.get(i - 1));
            seriesHorse.add(deltaHorse * timeStep + seriesHorse.get(i - 1));
            seriesDeer.add(deltaDeer * timeStep + seriesDeer.get(i - 1));
            seriesDeer.add(deltaWolf * timeStep + seriesWolf.get(i - 1));
        }
        
        XYSeries deerSeries = new XYSeries("Deer");
        XYSeries cattleSeries = new XYSeries("Cattle");
        XYSeries horsesSeries = new XYSeries("Horses");
        XYSeries wolfSeries = new XYSeries("Wolves");

        for (int i = 0; i < numberSteps; i++) {
            deerSeries.add(seriesTime.get(i), seriesDeer.get(i));
            cattleSeries.add(seriesTime.get(i), seriesCattle.get(i));
            horsesSeries.add(seriesTime.get(i), seriesHorse.get(i));
            wolfSeries.add(seriesTime.get(i), seriesWolf.get(i));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(deerSeries);
        dataset.addSeries(cattleSeries);
        dataset.addSeries(horsesSeries);
        dataset.addSeries(wolfSeries);

        return dataset;
    }

    @Override
    public ChartPanel getGraph(double width, double height) {
        //Predator-Prey Model: Deer-Cattle-Horses-Wolves
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

        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));
        renderer.setSeriesStroke(2, new BasicStroke(2.0f));
        renderer.setSeriesStroke(3, new BasicStroke(2.0f));

        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);
        renderer.setSeriesShapesVisible(2, false);
        renderer.setSeriesShapesVisible(3, false);
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
    
    static class PredatorPreyEquations implements FirstOrderDifferentialEquations {
        private static double wolfDeathRate = 0.01;

        private static final double cattleGrowthRate = 0.2; // Intrinsic growth rate of cattle
        private static final double cattleCarryingCapacity = 1000; // Carrying capacity for cattle
        private static final double horseGrowthRate = 0.1; // Intrinsic growth rate of horses
        private static final double horseCarryingCapacity = 1000; // Carrying capacity for horses
        private static final double deerGrowthRate = 0.1; // Intrinsic growth rate of deer
        private static final double deerCarryingCapacity = 1000; // Carrying capacity for deer
        private static final double wolfGrowthRate = 0.1; // Intrinsic growth rate of wolves
        private static final double wolfCarryingCapacity = 1000; // Carrying capacity for wolves

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
        @Override
        public int getDimension() {
            return 4;
        }

        @Override
        public void computeDerivatives(double t, double[] y, double[] yDot) {
            double deerPopulation = y[0];
            double cattlePopulation = y[1];
            double horsePopulation = y[2];
            double wolfPopulation = y[3];

            yDot[0] = cattleGrowthRate * cattlePopulation * (1 - (cattlePopulation + competitionHorseOnCattle * horsePopulation + competitionDeerOnCattle * deerPopulation) / cattleCarryingCapacity) - predationRateWolvesOnCattle * wolfPopulation * cattlePopulation;
            yDot[1] = rC * C * (1 - (C + A_CD * D + A_CH * H) / KC) - ALPHA_C * C * W;  // dC/dt
            yDot[2] = rH * H * (1 - (H + A_HD * D + A_HC * C) / KH) - ALPHA_H * H * W;  // dH/dt
            yDot[3] = ALPHA_D * D * W + ALPHA_C * C * W + ALPHA_H * H * W - 0.1 * W;  // dW/dt
        }
    }
}