package de.MCmoderSD.UI;

import de.MCmoderSD.core.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.HashSet;

public class InputHandler implements KeyListener {

    // Associations
    private final Game game;

    // Direction Key Mapping
    private final HashSet<Integer> leftKeys;
    private final HashSet<Integer> upKeys;
    private final HashSet<Integer> rightKeys;
    private final HashSet<Integer> downKeys;

    // Variables
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
        f3isPressed = false;


        // Init Direction Key Mapping
        leftKeys = new HashSet<>();
        upKeys = new HashSet<>();
        rightKeys = new HashSet<>();
        downKeys = new HashSet<>();

        // leftKeys
        leftKeys.add(KeyEvent.VK_LEFT);
        leftKeys.add(KeyEvent.VK_A);
        leftKeys.add(KeyEvent.VK_NUMPAD4);

        // upKeys
        upKeys.add(KeyEvent.VK_UP);
        upKeys.add(KeyEvent.VK_W);
        upKeys.add(KeyEvent.VK_NUMPAD8);

        // rightKeys
        rightKeys.add(KeyEvent.VK_RIGHT);
        rightKeys.add(KeyEvent.VK_D);
        rightKeys.add(KeyEvent.VK_NUMPAD6);

        // downKeys
        downKeys.add(KeyEvent.VK_DOWN);
        downKeys.add(KeyEvent.VK_S);
        downKeys.add(KeyEvent.VK_NUMPAD2);
        downKeys.add(KeyEvent.VK_NUMPAD5);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        // Variables
        var keycode = e.getKeyCode();
        boolean control = e.isControlDown();
        boolean alt = e.isAltDown();

        // F3 Pressed
        if (keycode == KeyEvent.VK_F3) f3isPressed = true;

        // Start
        if (keycode == KeyEvent.VK_ENTER) game.startGame();
        if (keycode == KeyEvent.VK_SPACE) game.startGame();

        // Exit
        if (control && keycode == KeyEvent.VK_C) System.exit(0);
        if (control && keycode == KeyEvent.VK_Q) System.exit(0);
        if (alt && keycode == KeyEvent.VK_Q) System.exit(0);
        if (alt && keycode == KeyEvent.VK_F4) System.exit(0);

        // Pause
        if (keycode == KeyEvent.VK_ESCAPE) game.togglePause();
        if (keycode == KeyEvent.VK_P) game.togglePause();

        // Debug
        if (f3isPressed && (keycode == KeyEvent.VK_F)) game.toggleFps();
        if (f3isPressed && (keycode == KeyEvent.VK_B)) game.toggleHitbox();
        if (f3isPressed && (keycode == KeyEvent.VK_G)) game.toggleGridLines();

        // Direction
        if (leftKeys.contains(keycode)) left = true;
        if (upKeys.contains(keycode)) up = true;
        if (rightKeys.contains(keycode)) right = true;
        if (downKeys.contains(keycode)) down = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {

        // Variables
        var keycode = e.getKeyCode();

        // F3 Pressed
        if (keycode == KeyEvent.VK_F3) f3isPressed = false;

        // Direction Reset
        if (leftKeys.contains(keycode)) left = false;
        if (upKeys.contains(keycode)) up = false;
        if (rightKeys.contains(keycode)) right = false;
        if (downKeys.contains(keycode)) down = false;
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