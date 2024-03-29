package de.MCmoderSD.utilities;

import de.MCmoderSD.main.Main;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.InputStream;

@SuppressWarnings("unused")
public class Calculate {

    // Center JFrame
    public static Point centerOfJFrame(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Screen Size
        int x = ((screenSize.width - frame.getWidth()) / 2);
        int y = ((screenSize.height - frame.getHeight()) / 2);
        return new Point(x, y);
    }

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
}