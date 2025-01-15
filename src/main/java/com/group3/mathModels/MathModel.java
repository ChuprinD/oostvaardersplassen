package com.group3.mathModels;

import org.jfree.chart.ChartPanel;

import com.group3.Formulae.FormulaVariables;

import javafx.embed.swing.SwingNode;

public interface MathModel {
    public ChartPanel getGraph(double width, double height, FormulaVariables formulaVariables);
    public void getSecondGraph(double width, double height, FormulaVariables formulaVariables, SwingNode swingNode);
}
