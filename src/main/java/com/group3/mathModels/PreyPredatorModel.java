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

import javax.swing.SwingUtilities;

import java.awt.BasicStroke;
import java.awt.Color;

public class PreyPredatorModel implements MathModel {
    FormulaVariables formulaVariables;

    public XYSeriesCollection calculateData() {
        double t0 = 0.0;
        double t1 = 10.0;
        double dt = 0.1;
        int steps = (int) ((t1 - t0) / dt);

        double[] initialPopulations = { formulaVariables.getCattleInitialPopulation(),
                formulaVariables.getHorseInitialPopulation(), formulaVariables.getDeerInitialPopulation(),
                formulaVariables.getWolfInitialPopulation() };

        double[] seriesCattle = new double[steps];
        double[] seriesHorse = new double[steps];
        double[] seriesDeer = new double[steps];
        double[] seriesWolf = new double[steps];
        double[] seriesTime = new double[steps];

        seriesCattle[0] = formulaVariables.getCattleInitialPopulation();
        seriesHorse[0] = formulaVariables.getHorseInitialPopulation();
        seriesDeer[0] = formulaVariables.getDeerInitialPopulation();
        seriesWolf[0] = formulaVariables.getWolfInitialPopulation();
        seriesTime[0] = 0.0;

        for (int i = 1; i < steps; i++) {
            seriesTime[i] = i * dt;
        }

        FirstOrderIntegrator integrator = new DormandPrince853Integrator(1.0e-8, 100.0, 1.0e-10, 1.0e-10);
        double[] currentState = initialPopulations.clone();
        for (int i = 1; i < steps; i++) {
            integrator.integrate(new PredatorPreyEquations(formulaVariables), t0, currentState, t0 + dt, currentState);

            seriesCattle[i] = currentState[0];
            seriesHorse[i] = currentState[1];
            seriesDeer[i] = currentState[2];
            seriesWolf[i] = currentState[3];
            t0 += dt;
        }

        XYSeries deerSeries = new XYSeries("Deer");
        XYSeries cattleSeries = new XYSeries("Cattle");
        XYSeries horsesSeries = new XYSeries("Horses");
        XYSeries wolfSeries = new XYSeries("Wolves");

        for (int i = 0; i < steps; i++) {
            deerSeries.add(seriesTime[i], seriesDeer[i]);
            cattleSeries.add(seriesTime[i], seriesCattle[i]);
            horsesSeries.add(seriesTime[i], seriesHorse[i]);
            wolfSeries.add(seriesTime[i], seriesWolf[i]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(deerSeries);
        dataset.addSeries(cattleSeries);
        dataset.addSeries(horsesSeries);
        dataset.addSeries(wolfSeries);

        return dataset;
    }
    
    public XYSeriesCollection calculateSecondData() {
        double t0 = 0.0;
        double t1 = 100.0;
        double dt = 0.01;
        int steps = (int) ((t1 - t0) / dt);

        double[] initialPopulations = { formulaVariables.getCattleInitialPopulation(), formulaVariables.getHorseInitialPopulation(), formulaVariables.getDeerInitialPopulation(), formulaVariables.getWolfInitialPopulation() };
        
        double[] seriesCattle = new double[steps];
        double[] seriesHorse = new double[steps];
        double[] seriesDeer = new double[steps];
        double[] seriesWolf = new double[steps];
        double[] seriesTime = new double[steps];
        
        seriesCattle[0] = formulaVariables.getCattleInitialPopulation();
        seriesHorse[0] = formulaVariables.getHorseInitialPopulation();
        seriesDeer[0] = formulaVariables.getDeerInitialPopulation();
        seriesWolf[0] = formulaVariables.getWolfInitialPopulation();
        seriesTime[0] = 0.0;

        for (int i = 1; i < steps; i++) {
            seriesTime[i] = i * dt;
        }

        FirstOrderIntegrator integrator = new DormandPrince853Integrator(1.0e-8, 100.0, 1.0e-10, 1.0e-10);
        double[] currentState = initialPopulations.clone();
        for (int i = 1; i < steps; i++) {
            integrator.integrate(new PredatorPreyEquations(formulaVariables), t0, currentState, t0 + dt, currentState);

            seriesCattle[i] = currentState[0];
            seriesHorse[i] = currentState[1];
            seriesDeer[i] = currentState[2];
            seriesWolf[i] = currentState[3];
            t0 += dt;
        }
        
        XYSeries series = new XYSeries("Wolves");

        for (int i = 0; i < steps; i++) {
            series.add(seriesDeer[i] + seriesHorse[i] + seriesCattle[i], seriesWolf[i]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        return dataset;
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

    @Override
    public void getSecondGraph(double width, double height, FormulaVariables formulaVariables, SwingNode swingNode) {
        this.formulaVariables = formulaVariables;
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                "",
                "Herbivores",
                "Wolves",
                calculateSecondData(),
                PlotOrientation.VERTICAL,
                false, true, false);

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
        renderer.setSeriesPaint(0, Color.BLUE);

        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        renderer.setSeriesShapesVisible(0, false);
        plot.setRenderer(renderer);

        plot.getDomainAxis().setTickLabelFont(new Font("Arial", Font.PLAIN, 16));
        plot.getRangeAxis().setTickLabelFont(new Font("Arial", Font.PLAIN, 16));

        ChartPanel chartPanel = new ChartPanel(xylineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension((int) width, (int) height));

        SwingUtilities.invokeLater(() -> {
            swingNode.setContent(chartPanel);
        });
    }
    
    static class PredatorPreyEquations implements FirstOrderDifferentialEquations {
        FormulaVariables formulaVariables;
        @Override
        public int getDimension() {
            return 4;
        }

        public PredatorPreyEquations(FormulaVariables formulaVariables) {
            this.formulaVariables = formulaVariables;
        }

        @Override
        public void computeDerivatives(double t, double[] y, double[] yDot) {
            FormulaVariables formulaVariables = new FormulaVariables();
            double cattlePopulation = y[0];
            double horsePopulation = y[1];
            double deerPopulation = y[2];
            double wolfPopulation = y[3];

            yDot[0] = formulaVariables.getCattleGrowthRate() * cattlePopulation * (1 - (cattlePopulation + formulaVariables.getCompetitionHorseOnCattle() * horsePopulation + formulaVariables.getCompetitionDeerOnCattle() * deerPopulation) / formulaVariables.getCattleCarryingCapacity()) - formulaVariables.getPredationRateWolvesOnCattle() * wolfPopulation * cattlePopulation;
            yDot[1] = formulaVariables.getHorseGrowthRate() * horsePopulation * (1 - (horsePopulation + formulaVariables.getCompetitionCattleOnHorses() * cattlePopulation  + formulaVariables.getCompetitionDeerOnHorses() * deerPopulation) / formulaVariables.getHorseCarryingCapacity()) - formulaVariables.getPredationRateWolvesOnHorses() * wolfPopulation * horsePopulation;
            yDot[2] = formulaVariables.getDeerGrowthRate() * deerPopulation * (1 - (deerPopulation + formulaVariables.getCompetitionHorsesOnDeer() * horsePopulation + formulaVariables.getCompetitionCattleOnDeer() * cattlePopulation) / formulaVariables.getDeerCarryingCapacity()) - formulaVariables.getPredationRateWolvesOnDeer() * wolfPopulation * deerPopulation;
            yDot[3] = formulaVariables.getConversionEfficiencyCattleToWolves() * wolfPopulation * (formulaVariables.getPredationRateWolvesOnCattle() * cattlePopulation + formulaVariables.getPredationRateWolvesOnHorses() * horsePopulation + formulaVariables.getPredationRateWolvesOnDeer() * deerPopulation) - formulaVariables.getWolfDeathRate() * wolfPopulation;
            // dW/dt
        }
    }
}