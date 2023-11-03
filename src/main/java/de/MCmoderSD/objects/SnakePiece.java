package de.MCmoderSD.objects;

import de.MCmoderSD.core.Game;
import de.MCmoderSD.main.Config;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SnakePiece extends Rectangle {

    // Attributes
    protected final Game game;
    protected final BufferedImage image;

    // Constants
    protected final int fieldWidth;
    protected final int fieldHeight;
    protected final int scale;
    protected final boolean solidWalls;

    // Direction
    protected boolean left = true;
    protected boolean up = false;
    protected boolean right = false;
    protected boolean down = false;

    // Default Constructor
    public SnakePiece(int x, int y, Game game, Config config) {
        super(x, y, config.getScale(), config.getScale());

        this.game = game;
        image = config.getSnakeBody();
        fieldWidth = config.getFieldWidth();
        fieldHeight = config.getFieldHeight();
        scale = config.getScale();
        solidWalls = config.isSolidWalls();
    }

    // Constructor with Image
    public SnakePiece(int x, int y, BufferedImage image, Game game, Config config) {
        super(x, y, config.getScale(), config.getScale());

        this.game = game;
        this.image = image;
        fieldWidth = config.getFieldWidth();
        fieldHeight = config.getFieldHeight();
        scale = config.getScale();
        solidWalls = config.isSolidWalls();
    }

    // Wall Hit Detected
    public void wallHitDetected() {
        if (solidWalls) {
            // ToDo Game Over
            System.out.println("Game Over");
            game.gameOver();

        } else {
            if (left) x = fieldWidth * scale - scale;
            if (up) y = fieldHeight * scale - scale;
            if (right) x = 0;
            if (down) y = 0;
        }
    }

    // Move
    protected void move() {
        if (left) {
            if (x - scale < 0) wallHitDetected();
            else x = x - scale;
        }

        if (up) {
            if (y - scale < 0) wallHitDetected();
            else y = y - scale;
        }

        if (right) {
            if (x + scale > fieldWidth * scale - scale) wallHitDetected();
            else x = x + scale;
        }

        if (down) {
            if (y + scale > fieldHeight * scale - scale) wallHitDetected();
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

    // Getter
    public BufferedImage getImage() {
        return image;
    }
}
