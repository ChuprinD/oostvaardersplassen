package utils;

public class Util {
    public static int getRegularFontSize(double screenWidth, double screenHeight) {
        int baseFontSize = 15;
        int fontSize = (int) (baseFontSize + Math.min(screenWidth, screenHeight) * 0.01);
        return fontSize;
    }
    public static int getTitleFontSize(double screenWidth, double screenHeight) {
        int baseFontSize = 25; 
        int fontSize = (int) (baseFontSize + Math.min(screenWidth, screenHeight) * 0.01);
        return fontSize;
    }
}
