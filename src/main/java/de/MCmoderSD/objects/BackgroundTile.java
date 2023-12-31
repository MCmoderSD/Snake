package de.MCmoderSD.objects;

import de.MCmoderSD.main.Config;

import java.awt.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("unused")
public class BackgroundTile {

    // Attributes
    private final BufferedImage image;
    private final Color color;
    private final Color hitboxColor;
    private final int x;
    private final int y;
    private final int scale;

    // Constructor
    public BackgroundTile(Config config, int x, int y) {
        this.x = x;
        this.y = y;

        image = config.getBackgroundTile();
        color = config.getBackgroundColor();
        hitboxColor = config.getGridLayoutColor();
        scale = config.getScale();
    }

    // Getter
    public BufferedImage getImage() {
        return image;
    }

    public Color getColor() {
        return color;
    }

    public Color getHitboxColor() {
        return hitboxColor;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getScale() {
        return scale;
    }

    public int getPositionX() {
        return x * scale;
    }

    public int getPositionY() {
        return y * scale;
    }

    public Point getPosition() {
        return new Point(getPositionX(), getPositionY());
    }

    public Rectangle getBounds() {
        return new Rectangle(getPositionX(), getPositionY(), scale, scale);
    }
}
