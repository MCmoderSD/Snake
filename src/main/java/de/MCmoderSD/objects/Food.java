package de.MCmoderSD.objects;

import de.MCmoderSD.main.Config;
import de.MCmoderSD.utilities.Calculate;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("unused")
public class Food {

    // Associations
    private final Config config;

    // Attributes
    private final boolean isSpecial;
    private final BufferedImage image;
    private final String sound;
    private final Color color;
    private final Color hitboxColor;
    private final int x;
    private final int y;
    private final int scale;


    // Constructor
    public Food(Config config, ArrayList<SnakePiece> snakePieces) {
        this.config = config;

        isSpecial = Calculate.randomChance(config.getSpecialFoodChance());
        image = isSpecial ? config.getGoldFood() : config.getFood();
        color = isSpecial ? config.getGoldFoodColor() : config.getFoodColor();

        hitboxColor = config.getFoodHitboxColor();
        sound = config.getFoodSound();
        scale = config.getScale();

        Point spawnPoint = getValidSpawnPont(snakePieces);

        x = spawnPoint.x;
        y = spawnPoint.y;
    }

    // Methods
    private Point getValidSpawnPont(ArrayList<SnakePiece> snakePieces) {
        Random random = new Random();

        int x = random.nextInt(config.getFieldWidth());
        int y = random.nextInt(config.getFieldHeight());

        for (SnakePiece snakePiece : snakePieces)
            if (snakePiece.getX() == x && snakePiece.getY() == y) return getValidSpawnPont(snakePieces);
        return new Point(x, y);
    }

    // Getter
    public boolean isSpecial() {
        return isSpecial;
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getSound() {
        return sound;
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

    public Rectangle getBounds() {
        return new Rectangle(getPositionX(), getPositionY(), scale, scale);
    }

    public Rectangle getSmallBounds() {
        return new Rectangle(getPositionX() + 1, getPositionY() + 1, scale - 2, scale - 2);
    }

    public Point getPosition() {
        return new Point(getPositionX(), getPositionY());
    }

    public int getPositionX() {
        return x * scale;
    }

    public int getPositionY() {
        return y * scale;
    }

    public Color getColor() {
        return color;
    }

    public Color getHitboxColor() {
        return hitboxColor;
    }
}
