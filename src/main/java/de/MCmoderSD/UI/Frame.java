package de.MCmoderSD.UI;

import de.MCmoderSD.core.Game;
import de.MCmoderSD.main.Config;
import de.MCmoderSD.utilities.Calculate;

import javax.swing.*;

public class Frame extends JFrame {

    // Attributes
    private final UI ui;

    // Constructor
    public Frame(Config config, Game game) {
        super(config.getTitle());
        setResizable(config.isResizable());
        setIconImage(config.getIcon());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create and add UI
        ui = new UI(config, game);
        add(ui);

        // Finalize and set visible
        pack();
        setLocation(Calculate.centerOfJFrame(this));
        setVisible(true);
    }

    // Getter
    public InputHandler getInputs() {
        return ui.getInputs();
    }

    public UI getUI() {
        return ui;
    }
}
