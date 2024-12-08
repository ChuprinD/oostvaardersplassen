// package com.group3.mathModels;
// import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
// import org.apache.commons.math3.ode.FirstOrderIntegrator;
// import org.apache.commons.math3.ode.nonstiff.DormandPrince853Integrator;
// import org.jfree.chart.ChartFactory;
// import org.jfree.chart.ChartPanel;
// import org.jfree.chart.JFreeChart;
// import org.jfree.chart.plot.PlotOrientation;
// import org.jfree.chart.plot.XYPlot;
// import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
// import org.jfree.data.xy.XYSeries;
// import org.jfree.data.xy.XYSeriesCollection;
// import org.jfree.ui.ApplicationFrame;
// import org.jfree.ui.RefineryUtilities;

// import javax.swing.*;
// import java.awt.*;

// public class Competitionmodel {


// public class CompetitionModelWithGrass extends ApplicationFrame {

//     private static double alpha = 1.0;  // Growth rate of grass
//     private static double beta = 0.1;   // Rate at which herbivores eat grass

//     private static double r1 = 0.5;  // Growth rate of horses
//     private static double r2 = 0.4;  // Growth rate of deer
//     private static double r3 = 0.3;  // Growth rate of cattle

//     private static double K1 = 100;  // Carrying capacity of horses
//     private static double K2 = 80;   // Carrying capacity of deer
//     private static double K3 = 60;   // Carrying capacity of cattle

//     private static double a12 = 0.01;  // Competition coefficient of deer on horses
//     private static double a13 = 0.02;  // Competition coefficient of cattle on horses
//     private static double a21 = 0.01;  // Competition coefficient of horses on deer
//     private static double a23 = 0.03;  // Competition coefficient of cattle on deer
//     private static double a31 = 0.02;  // Competition coefficient of horses on cattle
//     private static double a32 = 0.03;  // Competition coefficient of deer on cattle

//     private static double[] initialPopulations = {40, 20, 30, 20}; // Initial populations: [Grass, Horses, Deer, Cattle]

//     public CompetitionModelWithGrass(String title) {
//         super(title);
//         JFreeChart xylineChart = ChartFactory.createXYLineChart(
//                 "Competition Model with Grass: Grass-Horses-Deer-Cattle",
//                 "Time",
//                 "Population",
//                 createDataset(),
//                 PlotOrientation.VERTICAL,
//                 true, true, false);

//         XYPlot plot = xylineChart.getXYPlot();
//         XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
//         plot.setRenderer(renderer);

//         ChartPanel chartPanel = new ChartPanel(xylineChart);
//         chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
//         setContentPane(chartPanel);
//     }

//     private XYSeriesCollection createDataset() {
//         double t0 = 0.0;
//         double t1 = 200.0;
//         double dt = 0.1;
//         int steps = (int) ((t1 - t0) / dt);

//         double[][] results = new double[steps][4];
//         double[] time = new double[steps];

//         // Integrator
//         FirstOrderIntegrator integrator = new DormandPrince853Integrator(1.0e-8, 100.0, 1.0e-10, 1.0e-10);
//         for (int i = 0; i < steps; i++) {
//             time[i] = t0 + i * dt;
//             integrator.integrate(new CompetitionEquations(), t0, initialPopulations, time[i], initialPopulations);
//             results[i] = initialPopulations.clone();
//         }

//         XYSeries grassSeries = new XYSeries("Grass");
//         XYSeries horsesSeries = new XYSeries("Horses");
//         XYSeries deerSeries = new XYSeries("Deer");
//         XYSeries cattleSeries = new XYSeries("Cattle");

//         for (int i = 0; i < steps; i++) {
//             grassSeries.add(time[i], results[i][0]);
//             horsesSeries.add(time[i], results[i][1]);
//             deerSeries.add(time[i], results[i][2]);
//             cattleSeries.add(time[i], results[i][3]);
//         }

//         XYSeriesCollection dataset = new XYSeriesCollection();
//         dataset.addSeries(grassSeries);
//         dataset.addSeries(horsesSeries);
//         dataset.addSeries(deerSeries);
//         dataset.addSeries(cattleSeries);

//         return dataset;
//     }

//     public void main(String[] args) {
//         JTextField alphaField = new JTextField(Double.toString(alpha));
//         JTextField betaField = new JTextField(Double.toString(beta));

//         JTextField r1Field = new JTextField(Double.toString(r1));
//         JTextField r2Field = new JTextField(Double.toString(r2));
//         JTextField r3Field = new JTextField(Double.toString(r3));

//         JTextField grassInitField = new JTextField(Double.toString(initialPopulations[0]));
//         JTextField horsesInitField = new JTextField(Double.toString(initialPopulations[1]));
//         JTextField deerInitField = new JTextField(Double.toString(initialPopulations[2]));
//         JTextField cattleInitField = new JTextField(Double.toString(initialPopulations[3]));

//         JPanel panel = new JPanel(new GridLayout(8, 2));
//         panel.add(new JLabel("Growth rate of grass (alpha):"));
//         panel.add(alphaField);
//         panel.add(new JLabel("Rate at which herbivores eat grass (beta):"));
//         panel.add(betaField);

//         panel.add(new JLabel("Growth rate of horses (r1):"));
//         panel.add(r1Field);
//         panel.add(new JLabel("Growth rate of deer (r2):"));
//         panel.add(r2Field);
//         panel.add(new JLabel("Growth rate of cattle (r3):"));
//         panel.add(r3Field);

//         panel.add(new JLabel("Initial population of grass:"));
//         panel.add(grassInitField);
//         panel.add(new JLabel("Initial population of horses:"));
//         panel.add(horsesInitField);
//         panel.add(new JLabel("Initial population of deer:"));
//         panel.add(deerInitField);
//         panel.add(new JLabel("Initial population of cattle:"));
//         panel.add(cattleInitField);

//         int result = JOptionPane.showConfirmDialog(null, panel, "Enter Model Parameters", JOptionPane.OK_CANCEL_OPTION);
//         if (result == JOptionPane.OK_OPTION) {
//             alpha = Double.parseDouble(alphaField.getText());
//             beta = Double.parseDouble(betaField.getText());

//             r1 = Double.parseDouble(r1Field.getText());
//             r2 = Double.parseDouble(r2Field.getText());
//             r3 = Double.parseDouble(r3Field.getText());

//             initialPopulations[0] = Double.parseDouble(grassInitField.getText());
//             initialPopulations[1] = Double.parseDouble(horsesInitField.getText());
//             initialPopulations[2] = Double.parseDouble(deerInitField.getText());
//             initialPopulations[3] = Double.parseDouble(cattleInitField.getText());

//             Competitionmodel outer = new Competitionmodel();
//             CompetitionModelWithGrass chart = outer.new CompetitionModelWithGrass("Competition Model with Grass");
//             chart.pack();
//             RefineryUtilities.centerFrameOnScreen(chart);
//             chart.setVisible(true);
//         }
//     }
//     }

//     static class CompetitionEquations implements FirstOrderDifferentialEquations {
//         @Override
//         public int getDimension() {
//             return 4;
//         }

//         @Override
//         public void computeDerivatives(double t, double[] y, double[] yDot) {
//             double G = y[0];
//             double H = y[1];
//             double D = y[2];
//             double C = y[3];

//             yDot[0] = alpha * G - beta * G * (H + D + C);  // dG/dt
//             yDot[1] = r1 * H * (1 - (H + a12 * D + a13 * C) / K1);  // dH/dt
//             yDot[2] = r2 * D * (1 - (D + a21 * H + a23 * C) / K2);  // dD/dt
//             yDot[3] = r3 * C * (1 - (C + a31 * H + a32 * D) / K3);  // dC/dt
//         }
//     }
// }

