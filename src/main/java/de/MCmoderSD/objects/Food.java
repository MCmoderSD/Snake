package de.MCmoderSD.objects;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import java.util.ArrayList;

import static de.MCmoderSD.main.Config.*;

public class Food {

    // Attributes
    private final boolean isSpecial;
    private final boolean isOp;
    private final BufferedImage image;
    private final Color color;
    private final int x;
    private final int y;

    // Constructor
    public Food(ArrayList<SnakePart> snakeParts) {

        // Food Attributes
        isSpecial = Math.random() <= SPECIAL_FOOD_CHANCE;
        isOp = isSpecial && Math.random() <= SPECIAL_FOOD_CHANCE;

        // Food Image
        image = isSpecial ? GOLD_FOOD : FOOD;
        color = isOp ? OP_FOOD_COLOR : isSpecial ? GOLD_FOOD_COLOR : FOOD_COLOR;

        // Random Spawn Point
        Point spawnPoint = getValidSpawnPont(snakeParts);
        x = spawnPoint.x;
        y = spawnPoint.y;
    }

    // Methods
    private Point getValidSpawnPont(ArrayList<SnakePart> snakeParts) {

        // Random Spawn Point
        var x = (int) (Math.random() * FIELD_WIDTH);
        var y = (int) (Math.random() * FIELD_HEIGHT);

        // Check for Snake Collision
        for (SnakePart snakePart : snakeParts) if (snakePart.getX() == x && snakePart.getY() == y) return getValidSpawnPont(snakeParts);
        return new Point(x, y);
    }

    // Getter
    public boolean isSpecial() {
        return isSpecial;
    }

    public boolean isOp() {
        return isOp;
    }

    public BufferedImage getImage() {
        return image;
    }

    public Color getColor() {
        return color;
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