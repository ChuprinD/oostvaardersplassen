package com.group3.mathModels;

import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.DormandPrince853Integrator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graphs {

    private final double[] initialPopulations = {30, 20, 20, 3};
    
    public ChartPanel getPreyPredator(double width, double height) {
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                "Predator-Prey Model: Deer-Cattle-Horses-Wolves",
                "Time",
                "Population",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        XYPlot plot = xylineChart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        plot.setRenderer(renderer);
        ChartPanel chartPanel = new ChartPanel(xylineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension((int) width, (int) height));

        return chartPanel;
    }
    
    private XYSeriesCollection createDataset() {
        double t0 = 0.0;
        double t1 = 10.0;
        double dt = 0.1;
        int steps = (int) ((t1 - t0) / dt);

        double[][] results = new double[steps][4];
        double[] time = new double[steps];

        // Integrator
        FirstOrderIntegrator integrator = new DormandPrince853Integrator(1.0e-8, 100.0, 1.0e-10, 1.0e-10);
        for (int i = 1; i < steps; i++) {
            time[i] = t0 + i * dt;
            integrator.integrate(new PredatorPreyEquations(), t0, initialPopulations, time[i], initialPopulations);
            results[i] = initialPopulations.clone();
        }

        XYSeries deerSeries = new XYSeries("Deer");
        XYSeries cattleSeries = new XYSeries("Cattle");
        XYSeries horsesSeries = new XYSeries("Horses");
        XYSeries wolvesSeries = new XYSeries("Wolves");

        for (int i = 0; i < steps; i++) {
            deerSeries.add(time[i], results[i][0]);
            cattleSeries.add(time[i], results[i][1]);
            horsesSeries.add(time[i], results[i][2]);
            wolvesSeries.add(time[i], results[i][3]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(deerSeries);
        dataset.addSeries(cattleSeries);
        dataset.addSeries(horsesSeries);
        dataset.addSeries(wolvesSeries);

        return dataset;
    }

    static class PredatorPreyEquations implements FirstOrderDifferentialEquations {

        private static double rD = 0.4;  // Growth rate of deer
        private static double rC = 0.3;  // Growth rate of cattle
        private static double rH = 0.5;  // Growth rate of horses
    
        private static double KD = 80;   // Carrying capacity of deer
        private static double KC = 60;   // Carrying capacity of cattle
        private static double KH = 100;  // Carrying capacity of horses
    
        private static final double A_DC = 0.01;  // Competition coefficient of cattle on deer
        private static final double A_DH = 0.01;  // Competition coefficient of horses on deer
        private static final double A_CD = 0.01;  // Competition coefficient of deer on cattle
        private static final double A_CH = 0.02;  // Competition coefficient of horses on cattle
        private static final double A_HD = 0.01;  // Competition coefficient of deer on horses
        private static final double A_HC = 0.02;  // Competition coefficient of cattle on horses
    
        private static final double ALPHA_D = 0.01;  // Predation rate of wolves on deer
        private static final double ALPHA_C = 0.01;  // Predation rate of wolves on cattle
        private static final double ALPHA_H = 0.01;  // Predation rate of wolves on horses
        @Override
        public int getDimension() {
            return 4;
        }

        @Override
        public void computeDerivatives(double t, double[] y, double[] yDot) {
            double D = y[0];
            double C = y[1];
            double H = y[2];
            double W = y[3];

            yDot[0] = rD * D * (1 - (D + A_DC * C + A_DH * H) / KD) - ALPHA_D * D * W;  // dD/dt
            yDot[1] = rC * C * (1 - (C + A_CD * D + A_CH * H) / KC) - ALPHA_C * C * W;  // dC/dt
            yDot[2] = rH * H * (1 - (H + A_HD * D + A_HC * C) / KH) - ALPHA_H * H * W;  // dH/dt
            yDot[3] = ALPHA_D * D * W + ALPHA_C * C * W + ALPHA_H * H * W - 0.1 * W;  // dW/dt
        }
    }
}
