package de.MCmoderSD.objects;

import de.MCmoderSD.main.Config;

import java.awt.Color;
import java.awt.Rectangle;
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
    public BackgroundTile(int x, int y) {
        this.x = x;
        this.y = y;

        image = Config.BACKGROUND_TILE;
        color = Config.BACKGROUND_COLOR;
        hitboxColor = Config.GRID_LAYOUT_COLOR;
        scale = Config.SCALE;
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
}
