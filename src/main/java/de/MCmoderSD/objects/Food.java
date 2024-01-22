package de.MCmoderSD.objects;

import de.MCmoderSD.main.Config;
import de.MCmoderSD.utilities.Calculate;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("unused")
public class Food {

    // Attributes
    private final BufferedImage image;
    private final ImageIcon animation;
    private final String sound;
    private final Color color;
    private final Color hitboxColor;
    private final boolean isSpecial;
    private final boolean isOp;
    private final int x;
    private final int y;
    private final int scale;


    // Constructor
    public Food(ArrayList<SnakePiece> snakePieces) {

        isSpecial = Calculate.randomChance(Config.SPECIAL_FOOD_CHANCE);
        image = isSpecial ? Config.GOLD_FOOD : Config.FOOD;


        isOp = isSpecial && Calculate.randomChance(Config.SPECIAL_FOOD_CHANCE);
        animation = isOp ? Config.OP_FOOD_ANIMATION : null;

        color = isOp ? Config.OP_FOOD_COLOR : isSpecial ? Config.GOLD_FOOD_COLOR : Config.FOOD_COLOR;

        hitboxColor = Config.FOOD_HITBOX_COLOR;
        sound = Config.FOOD_SOUND;
        scale = Config.SCALE;

        Point spawnPoint = getValidSpawnPont(snakePieces);

        x = spawnPoint.x;
        y = spawnPoint.y;
    }

    // Methods
    private Point getValidSpawnPont(ArrayList<SnakePiece> snakePieces) {
        Random random = new Random();

        int x = random.nextInt(Config.FIELD_WIDTH);
        int y = random.nextInt(Config.FIELD_HEIGHT);

        for (SnakePiece snakePiece : snakePieces)
            if (snakePiece.getX() == x && snakePiece.getY() == y) return getValidSpawnPont(snakePieces);
        return new Point(x, y);
    }

    // Getter
    public BufferedImage getImage() {
        return image;
    }

    public ImageIcon getAnimation() {
        return animation;
    }

    public String getSound() {
        return sound;
    }

    public Color getColor() {
        return color;
    }

    public Color getHitboxColor() {
        return hitboxColor;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    public boolean isOp() {
        return isOp;
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
        return new Rectangle(x * scale, y * scale, scale, scale);
    }
}
