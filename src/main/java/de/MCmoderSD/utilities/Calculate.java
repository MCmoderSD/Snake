package de.MCmoderSD.utilities;

import de.MCmoderSD.main.Main;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.InputStream;

@SuppressWarnings("unused")
public class Calculate {

    // Calculate Tickrate
    public static int tickrate(int tps) {
        return 1000000000 / tps;
    }

    // Random Chance
    public static boolean randomChance(double probability) {
        return Math.random() <= probability;
    }

    // File Checker
    public static boolean ifFileExists(String resourcePath) {
        InputStream inputStream = Main.class.getResourceAsStream(resourcePath);
        return inputStream != null;
    }

    public static int divide(int a, int b) {
        return Math.round((float) a / (float) b);
    }
}