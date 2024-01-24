package de.MCmoderSD.objects;

import de.MCmoderSD.core.Game;
import de.MCmoderSD.main.Config;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

@SuppressWarnings("unused")
public class SnakePiece {

    // Associations
    protected final Game game;

    // Constants
    protected final Color color;
    protected final Color hitboxColor;
    protected final int fieldWidth;
    protected final int fieldHeight;
    protected final int scale;
    protected final boolean solidWalls;

    // Attributes
    protected BufferedImage image;
    protected ImageIcon animation;
    protected int x;
    protected int y;

    // Direction
    protected boolean left = true;
    protected boolean up = false;
    protected boolean right = false;
    protected boolean down = false;

    // Grow Constructor
    public SnakePiece(Game game, int x, int y) {
        this.game = game;

        color = Config.SNAKE_COLOR;
        hitboxColor = Config.SNAKE_HITBOX_COLOR;

        fieldWidth = Config.FIELD_WIDTH;
        fieldHeight = Config.FIELD_HEIGHT;
        scale = Config.SCALE;
        solidWalls = Config.SOLID_WALLS;

        image = Config.LEG_TILE;
        animation = Config.LEG_TILE_ANIMATION;

        this.x = x;
        this.y = y;
    }

    // Init Constructor
    public SnakePiece(Game game, int x, int y, BufferedImage image, ImageIcon animation) {
        this.game = game;

        color = Config.SNAKE_COLOR;
        hitboxColor = Config.SNAKE_HITBOX_COLOR;

        fieldWidth = Config.FIELD_WIDTH;
        fieldHeight = Config.FIELD_HEIGHT;
        scale = Config.SCALE;
        solidWalls = Config.SOLID_WALLS;

        this.image = image;
        this.animation = animation;

        this.x = x;
        this.y = y;
    }

    // Wall Hit Detected
    private void wallHitDetected() {
        if (solidWalls && !game.isUltActive()) game.gameOver();
        else {
            if (left) x = fieldWidth - 1;
            if (up) y = fieldHeight - 1;
            if (right) x = 0;
            if (down) y = 0;
        }
    }

    protected byte getDirection() {
        if (left) return 0;
        if (up) return 1;
        if (right) return 2;
        if (down) return 3;
        return -1;
    }

    // Move
    protected void move() {
        if (left) {
            if (x - 1 < 0) wallHitDetected();
            else x = x - 1;
        }

        if (up) {
            if (y - 1 < 0) wallHitDetected();
            else y = y - 1;
        }

        if (right) {
            if (x + 1 > fieldWidth - 1) wallHitDetected();
            else x = x + 1;
        }

        if (down) {
            if (y + 1 > fieldHeight - 1) wallHitDetected();
            else y = y + 1;
        }
    }

    // Update Direction if not the opposite direction
    public void updateDirection(byte direction) {
        switch (direction) {
            case -1:
                break;

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
    protected Game getGame() {
        return game;
    }

    public AffineTransform getTransform() {
        AffineTransform transform = new AffineTransform();
        transform.translate(x * scale, y * scale);

        int effectiveScale = scale / 2;

        switch (getDirection()) {
            case 0:
                transform.rotate(Math.toRadians(-90), effectiveScale, effectiveScale);
                break;
            case 1:
                transform.rotate(Math.toRadians(0), effectiveScale, effectiveScale);
                break;
            case 2:
                transform.rotate(Math.toRadians(90), effectiveScale, effectiveScale);
                break;
            case 3:
                transform.rotate(Math.toRadians(180), effectiveScale, effectiveScale);
                break;
        }

        return transform;
    }

    public BufferedImage getImage() {
        return image;
    }

    public ImageIcon getAnimation() {
        return animation;
    }

    public Color getColor() {
        return color;
    }

    public Color getHitboxColor() {
        return hitboxColor;
    }

    public int getScale() {
        return scale;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x * scale, y * scale, scale, scale);
    }

    // Setter
    public void overrideAssets(BufferedImage image, ImageIcon animation) {
        this.image = image;
        this.animation = animation;
    }
}