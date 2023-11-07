package de.MCmoderSD.objects;

import de.MCmoderSD.main.Config;
import de.MCmoderSD.utilities.Calculate;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Food extends Rectangle {

    // Attributes
    private final boolean isSpecial;
    private final BufferedImage image;
    private final String sound;

    // Constructor
    public Food(Config config, ArrayList<SnakePiece> snakePieces) {
        Random random = new Random();

        int scale = config.getScale();
        int fieldWidth = config.getFieldWidth();
        int fieldHeight = config.getFieldHeight();

        x = random.nextInt(fieldWidth);
        y = random.nextInt(fieldHeight);

        x = x * scale;
        y = y * scale;

        width = scale;
        height = scale;

        if (snakePieces.size() < fieldWidth * fieldHeight && !validSpawn(snakePieces)) new Food(config, snakePieces);

        isSpecial = Calculate.randomChance(config.getSpecialFoodChance());
        if (isSpecial) image = config.getGoldFood();
        else image = config.getFood();

        if (isSpecial) sound = config.getUltSound();
        else sound = config.getFoodSound();
    }

    // Methods
    private boolean validSpawn(ArrayList<SnakePiece> snakePieces) {
        for (SnakePiece snakePiece : snakePieces) if (snakePiece.x == x && snakePiece.y == y) return false;
        return true;
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
