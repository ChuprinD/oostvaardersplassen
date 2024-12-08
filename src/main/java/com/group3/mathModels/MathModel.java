/* 
package com.group3.mathModels;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;

public class MathModel implements FirstOrderDifferentialEquations {

    private final double rD;  // Growth rate of deer
    private final double rC;  // Growth rate of cattle
    private final double rH;  // Growth rate of horses

    private final double KD;  // Carrying capacity of deer
    private final double KC;  // Carrying capacity of cattle
    private final double KH;  // Carrying capacity of horses

    private final double aDC;  // Competition coefficient of cattle on deer
    private final double aDH;  // Competition coefficient of horses on deer
    private final double aCD;  // Competition coefficient of deer on cattle
    private final double aCH;  // Competition coefficient of horses on cattle
    private final double aHD;  // Competition coefficient of deer on horses
    private final double aHC;  // Competition coefficient of cattle on horses

    private final double alphaD;  // Predation rate of wolves on deer
    private final double alphaC;  // Predation rate of wolves on cattle
    private final double alphaH;  // Predation rate of wolves on horses

    public MathModel(double rD, double rC, double rH, double KD, double KC, double KH,
double aDC, double aDH, double aCD, double aCH, double aHD, double aHC,
double alphaD, double alphaC, double alphaH) {
        this.rD = rD;
        this.rC = rC;
        this.rH = rH;
        this.KD = KD;
        this.KC = KC;
        this.KH = KH;
        this.aDC = aDC;
        this.aDH = aDH;
        this.aCD = aCD;
        this.aCH = aCH;
        this.aHD = aHD;
        this.aHC = aHC;
        this.alphaD = alphaD;
        this.alphaC = alphaC;
        this.alphaH = alphaH;
        
    }

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

        yDot[0] = rD * D * (1 - (D + aDC * C + aDH * H) / KD) - alphaD * D * W;  // dD/dt
        yDot[1] = rC * C * (1 - (C + aCD * D + aCH * H) / KC) - alphaC * C * W;  // dC/dt
        yDot[2] = rH * H * (1 - (H + aHD * D + aHC * C) / KH) - alphaH * H * W;  // dH/dt
        yDot[3] = alphaD * D * W + alphaC * C * W + alphaH * H * W - 0.1 * W;  // dW/dt

    }
}*/