package de.MCmoderSD.objects;

import de.MCmoderSD.core.Game;
import de.MCmoderSD.main.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

@SuppressWarnings("unused")
public class SnakePiece {

    // Associations
    protected final Game game;

    // Constants
    protected final int fieldWidth;
    protected final int fieldHeight;
    protected final int scale;
    protected final boolean solidWalls;
    protected final Color color;
    protected final Color hitboxColor;

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

    // Default Constructor
    public SnakePiece(int x, int y, Game game, Config config) {
        this.game = game;
        this.x = x;
        this.y = y;

        image = config.getLegTile();
        animation = config.getLegTileAnimation();
        fieldWidth = config.getFieldWidth();
        fieldHeight = config.getFieldHeight();
        scale = config.getScale();
        solidWalls = config.isSolidWalls();
        color = config.getSnakeColor();
        hitboxColor = config.getSnakeHitboxColor();
    }

    // Constructor with Image
    public SnakePiece(int x, int y, BufferedImage image, ImageIcon animation, Game game, Config config) {
        this.game = game;
        this.image = image;
        this.animation = animation;
        this.x = x;
        this.y = y;

        fieldWidth = config.getFieldWidth();
        fieldHeight = config.getFieldHeight();
        scale = config.getScale();
        solidWalls = config.isSolidWalls();
        color = config.getSnakeColor();
        hitboxColor = config.getSnakeHitboxColor();
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

    public byte getDirection() {
        if (left) return 0;
        if (up) return 1;
        if (right) return 2;
        if (down) return 3;
        return -1;
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

    public int getScale() {
        return scale;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPositionX() {
        return x * scale;
    }

    public int getPositionY() {
        return y * scale;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public ImageIcon getAnimation() {
        return animation;
    }

    public void setAnimation(ImageIcon animation) {
        this.animation = animation;
    }

    public Point getPosition() {
        return new Point(getPositionX(), getPositionY());
    }

    public Rectangle getBounds() {
        return new Rectangle(getPositionX(), getPositionY(), scale, scale);
    }

    public Rectangle getSmallBounds() {
        return new Rectangle(getPositionX() + 1, getPositionY() + 1, scale - 2, scale - 2);
    }

    public Color getColor() {
        return color;
    }

    public Color getHitboxColor() {
        return hitboxColor;
    }

    // Setter
    public void setAssets(BufferedImage image, ImageIcon animation) {
        this.image = image;
        this.animation = animation;
    }
}
