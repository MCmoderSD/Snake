package de.MCmoderSD.objects;

import javax.swing.ImageIcon;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import static de.MCmoderSD.main.Config.*;

public class SnakePart {

    // Attributes
    protected BufferedImage image;
    protected ImageIcon animation;
    protected Direction direction;
    protected int x;
    protected int y;

    // Init Constructor
    public SnakePart(int x, int y, Snake.PartType type) {

        // Init Attributes
        image = type.getImage();
        animation = type.getAnimation();
        direction = Direction.LEFT;

        // Set Position
        this.x = x;
        this.y = y;
    }

    // Setter
    public void setDirection(Direction direction) {
        if (direction == null) return;
        if (!this.direction.isOpposite(direction))this.direction = direction;
    }

    // Getter
    public BufferedImage getImage() {
        return image;
    }

    public ImageIcon getAnimation() {
        return animation;
    }

    public AffineTransform getTransform() {
        if (direction == null) return new AffineTransform();
        return direction.getAffineTransform(x * SCALE, y * SCALE, SCALE);
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