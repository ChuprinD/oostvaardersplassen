package com.group3.utils;

import javafx.scene.text.Font;

public class Util {

    private static Font voltaireFont;

    static {
        voltaireFont = Font.loadFont(Util.class.getResourceAsStream("/fonts/Voltaire-Regular.ttf"), 20);
        System.out.println(Util.class.getResource("/fonts/Voltaire-Regular.ttf"));

    }

    public static Font getVoltaireFont(double size) {
        return Font.font(voltaireFont.getFamily(), size);
    }

    public static int getRegularFontSize(double screenWidth, double screenHeight) {
        int baseFontSize = 20;
        int fontSize = (int) (baseFontSize + Math.min(screenWidth, screenHeight) * 0.01);
        return fontSize;
    }
    
    public static int getTitleFontSize(double screenWidth, double screenHeight) {
        int baseFontSize = 32;
        int fontSize = (int) (baseFontSize + Math.min(screenWidth, screenHeight) * 0.01);
        return fontSize;
    }
    
    public static int getButtonFontSize(double screenWidth, double screenHeight) {
        int baseFontSize = 10;
        int fontSize = (int) (baseFontSize + Math.min(screenWidth, screenHeight) * 0.01);
        return fontSize;
    }
}
