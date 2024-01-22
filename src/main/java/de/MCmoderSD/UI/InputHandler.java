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
        int keycode = e.getKeyCode();

        // F3 Pressed
        if (keycode == KeyEvent.VK_F3) f3isPressed = true;

        // Start
        if (keycode == KeyEvent.VK_ENTER) game.start();
        if (keycode == KeyEvent.VK_SPACE) game.start();

        // Exit
        if (e.isControlDown() && keycode == KeyEvent.VK_C) System.exit(0);
        if (e.isControlDown() && keycode == KeyEvent.VK_Q) System.exit(0);
        if (e.isAltDown() && keycode == KeyEvent.VK_Q) System.exit(0);
        if (e.isAltDown() && keycode == KeyEvent.VK_F4) System.exit(0);

        // Pause
        if (keycode == KeyEvent.VK_ESCAPE) game.togglePause();
        if (keycode == KeyEvent.VK_P) game.togglePause();

        // Debug
        if (f3isPressed && (keycode == KeyEvent.VK_F)) game.toggleFps();
        if (f3isPressed && (keycode == KeyEvent.VK_B)) game.toggleHitbox();
        if (f3isPressed && (keycode == KeyEvent.VK_G)) game.toggleGridLines();

        // Direction
        if (keycode == KeyEvent.VK_LEFT || keycode == KeyEvent.VK_A) left = true;
        if (keycode == KeyEvent.VK_UP || keycode == KeyEvent.VK_W) up = true;
        if (keycode == KeyEvent.VK_RIGHT || keycode == KeyEvent.VK_D) right = true;
        if (keycode == KeyEvent.VK_DOWN || keycode == KeyEvent.VK_S) down = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keycode = e.getKeyCode();

        // F3 Pressed
        if (keycode == KeyEvent.VK_F3) f3isPressed = false;

        // Direction Reset
        if (keycode == KeyEvent.VK_LEFT || keycode == KeyEvent.VK_A) left = false;
        if (keycode == KeyEvent.VK_UP || keycode == KeyEvent.VK_W) up = false;
        if (keycode == KeyEvent.VK_RIGHT || keycode == KeyEvent.VK_D) right = false;
        if (keycode == KeyEvent.VK_DOWN || keycode == KeyEvent.VK_S) down = false;
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