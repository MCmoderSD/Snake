package de.MCmoderSD.objects;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import static de.MCmoderSD.main.Config.*;

public class BackgroundTile {

    // Attributes
    private final BufferedImage image;
    private final Color color;
    private final Color hitboxColor;
    private final int x;
    private final int y;

    // Constructor
    public BackgroundTile(int x, int y) {
        image = BACKGROUND_TILE;
        color = BACKGROUND_COLOR;
        hitboxColor = GRID_LAYOUT_COLOR;

        this.x = x;
        this.y = y;
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

    public Rectangle getBounds() {
        return new Rectangle(x * SCALE, y * SCALE, SCALE, SCALE);
    }
}
