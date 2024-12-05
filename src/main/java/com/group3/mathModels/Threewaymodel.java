package com.group3.mathModels;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.DormandPrince853Integrator;

public class Threewaymodel {
    
    public static void main(String[] args) {
        // Initial conditions: [Grass, Horses, Deer, Cattle, Wolves]
        double[] y = new double[] {40, 20, 30, 20, 3};
        double t0 = 0.0;
        double t1 = 200.0;

        // Integrator
        FirstOrderIntegrator integrator = new DormandPrince853Integrator(1.0e-8, 100.0, 1.0e-10, 1.0e-10);
        integrator.integrate(new CombinedEquations(), t0, y, t1, y);

        // Output results
        System.out.println("Grass: " + y[0]);
        System.out.println("Horses: " + y[1]);
        System.out.println("Deer: " + y[2]);
        System.out.println("Cattle: " + y[3]);
        System.out.println("Wolves: " + y[4]);
    }

    static class CombinedEquations implements FirstOrderDifferentialEquations {
        private final double alpha = 1.0;  // Growth rate of grass
        private final double beta = 0.1;   // Rate at which herbivores eat grass
        private final double delta = 0.075;  // Rate at which wolves reproduce based on herbivore consumption
        private final double gamma = 0.01;  // Rate at which wolves eat herbivores

        private final double r1 = 0.5;  // Growth rate of horses
        private final double r2 = 0.4;  // Growth rate of deer
        private final double r3 = 0.3;  // Growth rate of cattle

        private final double K1 = 100;  // Carrying capacity of horses
        private final double K2 = 80;   // Carrying capacity of deer
        private final double K3 = 60;   // Carrying capacity of cattle

        private final double a12 = 0.01;  // Competition coefficient of deer on horses
        private final double a13 = 0.02;  // Competition coefficient of cattle on horses
        private final double a21 = 0.01;  // Competition coefficient of horses on deer
        private final double a23 = 0.03;  // Competition coefficient of cattle on deer
        private final double a31 = 0.02;  // Competition coefficient of horses on cattle
        private final double a32 = 0.03;  // Competition coefficient of deer on cattle

        @Override
        public int getDimension() {
            return 5;
        }

        @Override
        public void computeDerivatives(double t, double[] y, double[] yDot) {
            double G = y[0];
            double H = y[1];
            double D = y[2];
            double C = y[3];
            double W = y[4];

            yDot[0] = alpha * G - beta * G * (H + D + C);  // dG/dt
            yDot[1] = r1 * H * (1 - (H + a12 * D + a13 * C) / K1) - gamma * H * W;  // dH/dt
            yDot[2] = r2 * D * (1 - (D + a21 * H + a23 * C) / K2) - gamma * D * W;  // dD/dt
            yDot[3] = r3 * C * (1 - (C + a31 * H + a32 * D) / K3) - gamma * C * W;  // dC/dt
            yDot[4] = delta * (H + D + C) * W - gamma * W;  // dW/dt
        }
    }
}
