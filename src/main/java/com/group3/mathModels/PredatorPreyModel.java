package com.group3.mathModels;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.DormandPrince853Integrator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class PredatorPreyModel extends ApplicationFrame {

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

    private static final double[] initialPopulations = {30, 20, 20, 3}; // Initial populations: [Deer, Cattle, Horses, Wolves]

    public PredatorPreyModel(String title) {
        super(title);
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
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private XYSeriesCollection createDataset() {
        double t0 = 0.0;
        double t1 = 200.0;
        double dt = 0.1;
        int steps = (int) ((t1 - t0) / dt);

        double[][] results = new double[steps][4];
        double[] time = new double[steps];

        // Integrator
        FirstOrderIntegrator integrator = new DormandPrince853Integrator(1.0e-8, 100.0, 1.0e-10, 1.0e-10);
        for (int i = 0; i < steps; i++) {
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

    public static void main(String[] args) {
        JTextField rDField = new JTextField(Double.toString(rD));
        JTextField rCField = new JTextField(Double.toString(rC));
        JTextField rHField = new JTextField(Double.toString(rH));

        JTextField KDField = new JTextField(Double.toString(KD));
        JTextField KCField = new JTextField(Double.toString(KC));
        JTextField KHField = new JTextField(Double.toString(KH));

        JTextField deerInitField = new JTextField(Double.toString(initialPopulations[0]));
        JTextField cattleInitField = new JTextField(Double.toString(initialPopulations[1]));
        JTextField horsesInitField = new JTextField(Double.toString(initialPopulations[2]));
        JTextField wolvesInitField = new JTextField(Double.toString(initialPopulations[3]));

        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.add(new JLabel("Growth rate of deer (rD):"));
        panel.add(rDField);
        panel.add(new JLabel("Growth rate of cattle (rC):"));
        panel.add(rCField);
        panel.add(new JLabel("Growth rate of horses (rH):"));
        panel.add(rHField);

        panel.add(new JLabel("Carrying capacity of deer (KD):"));
        panel.add(KDField);
        panel.add(new JLabel("Carrying capacity of cattle (KC):"));
        panel.add(KCField);
        panel.add(new JLabel("Carrying capacity of horses (KH):"));
        panel.add(KHField);

        panel.add(new JLabel("Initial population of deer:"));
        panel.add(deerInitField);
        panel.add(new JLabel("Initial population of cattle:"));
        panel.add(cattleInitField);
        panel.add(new JLabel("Initial population of horses:"));
        panel.add(horsesInitField);
        panel.add(new JLabel("Initial population of wolves:"));
        panel.add(wolvesInitField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Enter Model Parameters", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            rD = Double.parseDouble(rDField.getText());
            rC = Double.parseDouble(rCField.getText());
            rH = Double.parseDouble(rHField.getText());

            KD = Double.parseDouble(KDField.getText());
            KC = Double.parseDouble(KCField.getText());
            KH = Double.parseDouble(KHField.getText());

            initialPopulations[0] = Double.parseDouble(deerInitField.getText());
            initialPopulations[1] = Double.parseDouble(cattleInitField.getText());
            initialPopulations[2] = Double.parseDouble(horsesInitField.getText());
            initialPopulations[3] = Double.parseDouble(wolvesInitField.getText());

            PredatorPreyModel chart = new PredatorPreyModel("Predator-Prey Model");
            chart.pack();
            UIUtils.centerFrameOnScreen(chart);
            chart.setVisible(true);
        }
    }

    static class PredatorPreyEquations implements FirstOrderDifferentialEquations {
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