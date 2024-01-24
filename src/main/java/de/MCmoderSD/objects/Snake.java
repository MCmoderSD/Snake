package de.MCmoderSD.objects;

import de.MCmoderSD.core.Game;
import de.MCmoderSD.main.Config;

import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Snake extends SnakePiece {

    // Attributes
    private final ArrayList<SnakePiece> snakePieces;
    //private final Game game;

    // Constructor
    public Snake(Game game, int x, int y, BufferedImage image, ImageIcon animation) {
        super(game, x, y, image, animation);
        snakePieces = new ArrayList<>();

        // Initial Snake
        snakePieces.add(this); // Head
        snakePieces.add(new SnakePiece(game, x + 1, y, Config.UPPER_BODY, Config.UPPER_BODY_ANIMATION)); // Upper Body
        snakePieces.add(new SnakePiece(game, x + 2, y, Config.LOWER_BODY, Config.LOWER_BODY_ANIMATION)); // Lower Body
    }

    // Rearrange Snake
    private void rearrangeImages() {
        if (snakePieces.size() == 4)
            snakePieces.get(snakePieces.size() - 1).setAssets(Config.LEG_TRANSITION, Config.LEG_TRANSITION_ANIMATION);
        if (snakePieces.size() > 4) {
            snakePieces.get(snakePieces.size() - 2).setAssets(Config.LEG_TRANSITION, Config.LEG_TRANSITION_ANIMATION);
            snakePieces.get(snakePieces.size() - 1).setAssets(Config.FEET, Config.FEET_ANIMATION);
        }

        for (int i = 3; i < snakePieces.size() - 2; i++)
            snakePieces.get(i).setAssets(Config.LEG_TILE, Config.LEG_TILE_ANIMATION);
    }

    // Move
    public void moveSnake() {
        for (int i = snakePieces.size() - 1; i > 0; i--) {
            snakePieces.get(i).x = snakePieces.get(i - 1).x;
            snakePieces.get(i).y = snakePieces.get(i - 1).y;
            snakePieces.get(i).updateDirection(snakePieces.get(i - 1).getDirection());
        }

        move();
    }

    // Check Collision
    public boolean checkCollision() {
        for (int i = 1; i < snakePieces.size(); i++)
            if (snakePieces.get(i).getBounds().intersects(snakePieces.get(0).getBounds())) return true;
        return false;
    }

    // Check Food
    public boolean checkFood(Food food) {
        for (SnakePiece snakePiece : snakePieces) if (snakePiece.getBounds().intersects(food.getBounds())) return true;
        return false;
    }

    // Grow
    public void grow() {
        snakePieces.add(new SnakePiece(game, snakePieces.get(snakePieces.size() - 1).x, snakePieces.get(snakePieces.size() - 1).y));
        rearrangeImages();
    }

    // Getter
    public ArrayList<SnakePiece> getSnakePieces() {
        return snakePieces;
    }
}
