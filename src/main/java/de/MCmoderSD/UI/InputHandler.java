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
    private boolean f3isPressed;

    public InputHandler(Game game) {

        // Init Associations
        this.game = game;

        // Initialize Attributes
        left = false;
        up = false;
        right = false;
        down = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        // F3 Pressed
        if (e.getKeyCode() == KeyEvent.VK_F3) f3isPressed = true;

        // Start
        if (e.getKeyCode() == KeyEvent.VK_ENTER) game.start();
        if (e.getKeyCode() == KeyEvent.VK_SPACE) game.start();

        // Exit
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_C) System.exit(0);
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Q) System.exit(0);

        // Pause
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) game.togglePause();
        if (e.getKeyCode() == KeyEvent.VK_P) game.togglePause();

        // Debug
        if (f3isPressed && (e.getKeyCode() == KeyEvent.VK_F)) game.toggleFps();
        if (f3isPressed && (e.getKeyCode() == KeyEvent.VK_B)) game.toggleHitbox();
        if (f3isPressed && (e.getKeyCode() == KeyEvent.VK_G)) game.toggleGridLines();


        // Direction
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) left = true;
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) up = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) right = true;
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) down = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {

        // F3 Pressed
        if (e.getKeyCode() == KeyEvent.VK_F3) f3isPressed = false;

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