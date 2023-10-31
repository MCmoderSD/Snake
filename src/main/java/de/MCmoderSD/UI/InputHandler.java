package de.MCmoderSD.UI;

import de.MCmoderSD.core.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    // Associations
    private final Game game;

    // Attributes
    private boolean left;
    private boolean up;
    private boolean right;
    private boolean down;

    public InputHandler(Game game) {
        this.game = game;

        // Initialize Attributes
        left = true;
        up = false;
        right = false;
        down = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_C) System.exit(0); // Exit
       if (e.getKeyCode() == KeyEvent.VK_ESCAPE) game.togglePause(); // Pause

        // Direction
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) left = true;
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) up = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) right = true;
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) down = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {

        // Direction Reset
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) left = false;
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) up = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) right = false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) down = false;
    }

    // Getter
    public byte getDirection() {

        // Single Press
        if (left && !up && !right && !down) return 0;
        if (!left && up && !right && !down) return 1;
        if (!left && !up && right && !down) return 2;
        if (!left && !up && !right && down) return 3;

        // Triple Press
        if (left && up && !right && down) return 0;
        if (left && up && right && !down) return 1;
        if (!left && up && right && down) return 2;
        if (left && !up && right && down) return 3;

        // Else
        return -1;
    }
}
