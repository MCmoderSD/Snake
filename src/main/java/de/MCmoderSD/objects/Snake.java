package de.MCmoderSD.objects;

import de.MCmoderSD.core.Game;
import de.MCmoderSD.main.Config;

import java.util.ArrayList;

public class Snake extends SnakePiece {

    // Attributes
    private final ArrayList<SnakePiece> snakePieces = new ArrayList<>();

    // Constructor
    public Snake(Game game, Config config) {
        super(0, 0, game, config);

        x = (config.getFieldWidth() / 2) * config.getScale();
        y = (config.getFieldHeight() / 2) * config.getScale();

        // Initial Snake
        snakePieces.add(this); // Head
        snakePieces.add(new SnakePiece(x + scale, y, game, config)); // Body
        snakePieces.add(new SnakePiece(x + 2 * scale, y, game, config)); // Body
    }


    // Methods

    // Move
    public void moveSnake() {
        for (int i = snakePieces.size() - 1; i > 0; i--) {
            snakePieces.get(i).x = snakePieces.get(i - 1).x;
            snakePieces.get(i).y = snakePieces.get(i - 1).y;
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
    }

    // Getter
    public ArrayList<SnakePiece> getSnakePieces() {
        return snakePieces;
    }
}
