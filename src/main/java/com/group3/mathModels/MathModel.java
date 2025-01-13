package com.group3.mathModels;

import org.jfree.chart.ChartPanel;

import com.group3.Formulae.FormulaVariables;

public interface MathModel {
    public ChartPanel getGraph(double width, double height, FormulaVariables formulaVariables);
}
