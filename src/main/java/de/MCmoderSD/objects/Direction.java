package de.MCmoderSD.objects;

import java.awt.geom.AffineTransform;

public enum Direction {

    // Constants
    LEFT(0), UP(1), RIGHT(2), DOWN(3);

    // Attributes
    private final double radian;

    // Constructor
    Direction(int direction) {
        switch (direction) {
            case 0 -> radian = Math.toRadians(-90);
            case 2 -> radian = Math.toRadians(90);
            case 3 -> radian = Math.toRadians(180);
            default -> radian = Math.toRadians(0);
        }
    }

    // Getter
    public AffineTransform getAffineTransform(int x, int y, int scale) {

        // Util effective scale
        var effectiveScale = (double) scale / 2;

        // Create Affine Transform
        AffineTransform transform = new AffineTransform();

        // Translate, Rotate
        transform.translate(x, y);
        transform.rotate(radian, effectiveScale, effectiveScale);

        // Return
        return transform;
    }

    public boolean isOpposite(Direction direction) {
        return switch (this) {
            case LEFT -> direction == RIGHT;
            case UP -> direction == DOWN;
            case RIGHT -> direction == LEFT;
            case DOWN -> direction == UP;
        };
    }
}