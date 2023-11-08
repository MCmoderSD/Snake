package de.MCmoderSD.objects;

import de.MCmoderSD.main.Config;
import de.MCmoderSD.utilities.Calculate;

import java.awt.Rectangle;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Food extends Rectangle {

    // Associations
    private final Config config;

    // Attributes
    private final boolean isSpecial;
    private final BufferedImage image;
    private final String sound;

    // Constructor
    public Food(Config config, ArrayList<SnakePiece> snakePieces) {
        this.config = config;

        isSpecial = Calculate.randomChance(config.getSpecialFoodChance());
        if (isSpecial) image = config.getGoldFood();
        else image = config.getFood();

        sound = config.getFoodSound();

        int scale = config.getScale();

        Point spawnPoint = getValidSpawnPont(snakePieces);

        x = spawnPoint.x;
        y = spawnPoint.y;

        x = x * scale;
        y = y * scale;

        width = scale;
        height = scale;
    }

    // Methods
    private Point getValidSpawnPont(ArrayList<SnakePiece> snakePieces) {
        Random random = new Random();

        int x = random.nextInt(config.getFieldWidth());
        int y = random.nextInt(config.getFieldHeight());

        for (Rectangle snakePiece : snakePieces) if (snakePiece.x == x && snakePiece.y == y) return getValidSpawnPont(snakePieces);
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
}
