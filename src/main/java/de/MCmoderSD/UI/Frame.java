package de.MCmoderSD.UI;

import de.MCmoderSD.main.Config;
import de.MCmoderSD.utilities.Calculate;

import javax.swing.JFrame;

public class Frame extends JFrame {

    // Constructor
    public Frame(Config config) {

        // Init JFrame
        super(Config.TITLE);
        setResizable(Config.RESIZABLE);
        setIconImage(Config.ICON);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Init UI
        new UI(this, config);

        // Finalize and set visible
        setLocation(Calculate.centerOfJFrame(this));
        setVisible(true);
    }
}
