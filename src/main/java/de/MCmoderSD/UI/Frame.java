package de.MCmoderSD.UI;

import de.MCmoderSD.core.Game;
import de.MCmoderSD.main.Config;
import de.MCmoderSD.utilities.Calculate;

import javax.swing.JFrame;

public class Frame extends JFrame {

    // Attributes
    private final UI UI;

    // Constructor
    public Frame(Config config, Game game) {
        super(config.getTitle());
        setResizable(config.isResizable());
        setIconImage(config.getIcon());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create and add UI
        UI = new UI(config, game);
        add(UI);

        // Finalize and set visible
        pack();
        setLocation(Calculate.centerFrame(this));
        setVisible(true);
    }

    // Getter
    public InputHandler getInputs() {
        return UI.getInputs();
    }

    // Setter
    public void refresh() {
        UI.repaint();
    }
}
