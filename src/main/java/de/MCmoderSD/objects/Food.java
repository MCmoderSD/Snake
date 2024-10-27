package de.MCmoderSD.objects;

import de.MCmoderSD.JavaAudioLibrary.AudioFile;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import static de.MCmoderSD.main.Config.*;

public class Food {

    // Attributes
    private final BufferedImage image;
    private final BufferedImage cover;
    private final ImageIcon animation;
    private final AudioFile sound;
    private final Color color;
    private final Color hitboxColor;
    private final boolean isSpecial;
    private final boolean isOp;
    private final int x;
    private final int y;

    // Constructor
    public Food(ArrayList<SnakePiece> snakePieces) {

        isSpecial = Math.random() <= SPECIAL_FOOD_CHANCE;
        image = isSpecial ? GOLD_FOOD : FOOD;
        sound = FOOD_SOUND.copy();


        isOp = isSpecial && Math.random() <= SPECIAL_FOOD_CHANCE;
        animation = isOp ? OP_FOOD_ANIMATION : null;
        cover = isOp ? BACKGROUND_COVER : null;

        color = isOp ? OP_FOOD_COLOR : isSpecial ? GOLD_FOOD_COLOR : FOOD_COLOR;
        hitboxColor = FOOD_HITBOX_COLOR;

        Point spawnPoint = getValidSpawnPont(snakePieces);
        x = spawnPoint.x;
        y = spawnPoint.y;
    }

    // Methods
    private Point getValidSpawnPont(ArrayList<SnakePiece> snakePieces) {
        Random random = new Random();

        int x = random.nextInt(FIELD_WIDTH);
        int y = random.nextInt(FIELD_HEIGHT);

        for (SnakePiece snakePiece : snakePieces)
            if (snakePiece.getX() == x && snakePiece.getY() == y) return getValidSpawnPont(snakePieces);
        return new Point(x, y);
    }

    // Getter
    public AudioFile getSound() {
        return sound;
    }

    public BufferedImage getImage() {
        return image;
    }

    public BufferedImage getCover() {
        return cover;
    }

    public ImageIcon getAnimation() {
        return animation;
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

    public Rectangle getBounds() {
        return new Rectangle(x * SCALE, y * SCALE, SCALE, SCALE);
    }
}
