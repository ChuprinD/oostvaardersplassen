package com.group3.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DatabaseApp {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/oostvaardersplassen";
    private static final String USER = "root";
    private static final String PASS = "0525";   
    
    private static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static double getInitialPopulation(String species) {
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT Amount FROM animal WHERE Name = ? AND  Year = 2022")) {
            stmt.setString(1, species);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getDouble("Amount");
            }
        } catch (Exception e) {
            System.err.println("Error getting initial population for " + species + ": " + e.getMessage());
        }
        return 0.0; // Default value if query fails
    }

    public static double getInitialGrass(int year) {
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT Height_CM FROM grass WHERE Year = ?")) {

            stmt.setInt(1, year);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("Height_CM");
            }
        } catch (Exception e) {
            System.err.println("Error getting grass height for year " + year + ": " + e.getMessage());
        }
        return 0.0; // Default value if query fails
    }
    
    public static List<Double> getAnnualPopulation(String species) {
        List<Double> populationData = new ArrayList<>();
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT Amount FROM animal WHERE Name = ?")) {

            stmt.setString(1, species);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                populationData.add(rs.getDouble("Amount"));
            }
        } catch (Exception e) {
            System.err.println("Error getting annual population for " + species + ": " + e.getMessage());
        }
        return populationData; // Default value if query fails
    }

    public static List<Double> getAnnualBirths(String species) {
        List<Double> populationData = new ArrayList<>();
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT Amount FROM animal_birth WHERE Name = ?")) {

            stmt.setString(1, species);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                populationData.add(rs.getDouble("Amount"));
            }
        } catch (Exception e) {
            System.err.println("Error getting annual births for " + species + ": " + e.getMessage());
        }
        return populationData; // Default value if query fails
    }

    public static List<Double> getAnnualMortality(String species) {
        List<Double> populationData = new ArrayList<>();
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT Amount FROM animal_mortality WHERE Name = ?")) {

            stmt.setString(1, species);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                populationData.add(rs.getDouble("Amount"));
            }
        } catch (Exception e) {
            System.err.println("Error getting annual mortality for " + species + ": " + e.getMessage());
        }
        return populationData; // Default value if query fails
    }
    /*public static void testConnection() {
        try (Connection conn = getConnection()) {
            System.out.println("Database connection successful!");
        } catch (Exception e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
    }*/
}