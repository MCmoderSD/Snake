package de.MCmoderSD.objects;

import de.MCmoderSD.core.Game;
import de.MCmoderSD.main.Config;

import java.util.ArrayList;

public class Snake extends SnakePiece {

    // Associations
    private final Config config;

    // Attributes
    private final ArrayList<SnakePiece> snakePieces = new ArrayList<>();

    // Constructor
    public Snake(Game game, Config config) {
        super(0, 0, config.getHead(), game, config);

        this.config = config;

        x = (config.getFieldWidth() / 2) * config.getScale() - 3;
        y = (config.getFieldHeight() / 2) * config.getScale();

        // Initial Snake
        snakePieces.add(this); // Head
        snakePieces.add(new SnakePiece(x + scale, y, config.getUpperBody(),game, config)); // Upper Body
        snakePieces.add(new SnakePiece(x + 2 * scale, y, config.getLowerBody(), game, config)); // Lower Body
    }


    // Methods

    // Recolor Snake
    private void rearangeImages() {
        if (snakePieces.size() == 5) snakePieces.get(snakePieces.size()-1).setImage(config.getLegTransition());
        if (snakePieces.size() > 5) {
            snakePieces.get(snakePieces.size()-2).setImage(config.getLegTransition());
            snakePieces.get(snakePieces.size()-1).setImage(config.getFeet());
        }

        for (int i = 4; i < snakePieces.size() - 2; i++) snakePieces.get(i).setImage(config.getLegTile());
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
        for (int i = 1; i < snakePieces.size(); i++) if (snakePieces.get(0).intersects(snakePieces.get(i))) return true;
        return false;
    }

    // Check Food
    public boolean checkFood(Food food) {
        for (SnakePiece snakePiece : snakePieces) if (snakePiece.intersects(food)) return true;
        return false;
    }

    // Grow
    public void grow(Config config) {
        snakePieces.add(new SnakePiece(snakePieces.get(snakePieces.size() - 1).x, snakePieces.get(snakePieces.size() - 1).y, game, config));
        rearangeImages();

        // Debug
        System.out.println(snakePieces.get(1).getDirection());
    }

    // Getter
    public ArrayList<SnakePiece> getSnakePieces() {
        return snakePieces;
    }
}
