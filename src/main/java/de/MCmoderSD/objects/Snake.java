package de.MCmoderSD.objects;

import de.MCmoderSD.core.Game;
import de.MCmoderSD.main.Config;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Snake extends SnakePiece {

    // Associations
    private final Config config;

    // Attributes
    private final ArrayList<SnakePiece> snakePieces;

    // Constructor
    public Snake(int x, int y, BufferedImage image, ImageIcon animation, Game game, Config config) {
        super(x, y, image, animation, game, config);
        this.config = config;
        snakePieces = new ArrayList<>();

        // Initial Snake
        snakePieces.add(this); // Head
        snakePieces.add(new SnakePiece(x + 1, y, config.getUpperBody(), config.getUpperBodyAnimation(), game, config)); // Upper Body
        snakePieces.add(new SnakePiece(x + 2, y, config.getLowerBody(), config.getLowerBodyAnimation(), game, config)); // Lower Body
    }


    // Methods

    // Recolor Snake
    private void rearrangeImages() {
        if (snakePieces.size() == 4)
            snakePieces.get(snakePieces.size() - 1).setAssets(config.getLegTransition(), config.getLegTransitionAnimation());
        if (snakePieces.size() > 4) {
            snakePieces.get(snakePieces.size() - 2).setImage(config.getLegTransition());
            snakePieces.get(snakePieces.size() - 1).setImage(config.getFeet());

            snakePieces.get(snakePieces.size() - 2).setAnimation(config.getLegTransitionAnimation());
            snakePieces.get(snakePieces.size() - 1).setAnimation(config.getFeetAnimation());
        }

        for (int i = 3; i < snakePieces.size() - 2; i++)
            snakePieces.get(i).setAssets(config.getLegTile(), config.getLegTileAnimation());
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
            if (snakePieces.get(0).getBounds().intersects(snakePieces.get(i).getBounds())) return true;
        return false;
    }

    // Check Food
    public boolean checkFood(Food food) {
        for (SnakePiece snakePiece : snakePieces) if (snakePiece.getBounds().intersects(food.getBounds())) return true;
        return false;
    }

    // Grow
    public void grow(Config config) {
        snakePieces.add(new SnakePiece(snakePieces.get(snakePieces.size() - 1).x, snakePieces.get(snakePieces.size() - 1).y, game, config));
        rearrangeImages();
    }

    // Getter
    public ArrayList<SnakePiece> getSnakePieces() {
        return snakePieces;
    }
}
