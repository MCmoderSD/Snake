package de.MCmoderSD.UI;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.Toolkit;

import static de.MCmoderSD.main.Config.*;
import static de.MCmoderSD.utilities.Calculate.divide;

public class Frame extends JFrame {

    // Constructor
    public Frame() {

        // Init JFrame
        super(TITLE);
        setIconImage(ICON);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Init UI
        new UI(this);

        // Finalize and set visible
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Screen Size
        setLocation(divide(screenSize.width - getWidth(), 2), divide(screenSize.height - getHeight(), 2));
        setVisible(true);
    }
}