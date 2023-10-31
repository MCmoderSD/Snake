package de.MCmoderSD.objects;

import de.MCmoderSD.main.Config;

import java.awt.Rectangle;
public class SnakePiece extends Rectangle {

    // Constants
    protected final int fieldWidth;
    protected final int fieldHeight;
    protected final int scale;

    // Direction
    protected boolean left = true;
    protected boolean up = false;
    protected boolean right = false;
    protected boolean down = false;

    public SnakePiece(int x, int y, Config config) {
        super(x, y, config.getScale(), config.getScale());

        fieldWidth = config.getFieldWidth();
        fieldHeight = config.getFieldHeight();
        scale = config.getScale();
    }

    // Move
    protected void move() {
        if (left) {
            if (x - scale < 0) x = fieldWidth * scale - scale;
            else x = x - scale;
        }

        if (up) {
            if (y - scale < 0) y = fieldHeight * scale - scale;
            else y = y - scale;
        }

        if (right) {
            if (x + scale > fieldWidth * scale - scale) x = 0;
            else x = x + scale;
        }

        if (down) {
            if (y + scale > fieldHeight * scale - scale) y = 0;
            else y = y + scale;
        }
    }

    // Update Direction if not the opposite direction
    public void updateDirection(byte direction) {
        switch (direction) {
            case -1: break;

            case 0: {
                if (right) break;
                left = true;
                up = false;
                down = false;

                break;
            }

            case 1: {
                if (down) break;
                left = false;
                up = true;
                right = false;
                break;
            }

            case 2: {
                if (left) break;
                up = false;
                right = true;
                down = false;
                break;
            }

            case 3: {
                if (up) break;
                left = false;
                right = false;
                down = true;
            }
        }
    }
}
