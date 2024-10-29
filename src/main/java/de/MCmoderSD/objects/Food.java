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

        // Calculate Invalid Points
        ArrayList<Point> invalidPoints = new ArrayList<>();
        for (SnakePart part : snakeParts) invalidPoints.add(new Point(part.getX(), part.getY()));

        // Calculate Valid Spawn Points
        ArrayList<Point> validPoints = new ArrayList<>();
        for (var i = 0; i < FIELD_WIDTH; i++) for (var j = 0; j < FIELD_HEIGHT; j++) {
            Point point = new Point(i, j);
            if (!invalidPoints.contains(point)) validPoints.add(point);
        }

        // Return Random Point
        return validPoints.get((int) (Math.random() * validPoints.size()));
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