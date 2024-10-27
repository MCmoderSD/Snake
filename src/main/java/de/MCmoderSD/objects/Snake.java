package de.MCmoderSD.objects;

import de.MCmoderSD.core.Game;
import de.MCmoderSD.main.Config;

import javax.swing.ImageIcon;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import java.util.ArrayList;

import static de.MCmoderSD.main.Config.*;
import static de.MCmoderSD.utilities.Util.GAME;

public class Snake extends SnakePart {

    // Associations
    private final Game game;

    // Attributes
    private final ArrayList<SnakePart> snakeParts;

    // Constructor
    public Snake(Game game, int x, int y) {

        // Init Initial Snake
        super(x, y, PartType.HEAD);                                              // Head
        SnakePart upperBody = new SnakePart(x + 1, y, PartType.UPPER_BODY);    // Upper Body
        SnakePart lowerBody = new SnakePart(x + 2, y, PartType.LOWER_BODY);    // Lower Body

        // Init Associations
        this.game = game;

        // Add to SnakeList
        snakeParts = new ArrayList<>();
        snakeParts.add(this);
        snakeParts.add(upperBody);
        snakeParts.add(lowerBody);
    }

    // Wall Hit Detected
    private void wallHitDetected() {
        if (SOLID_WALLS && !game.isUltActive()) System.out.printf("%s Wall hit detected%n", GAME);
        else {
            switch (direction) {
                case Direction.LEFT -> x = FIELD_WIDTH - 1;
                case Direction.UP -> y = FIELD_HEIGHT - 1;
                case Direction.RIGHT -> x = 0;
                case Direction.DOWN -> y = 0;
            }
        }
    }

    // Move
    public void move() {
        for (var i = snakeParts.size() - 1; i > 0; i--) {

            // Get Pieces
            SnakePart body = snakeParts.get(i - 1);
            SnakePart tail = snakeParts.get(i);

            // Move Body
            tail.x = body.x;
            tail.y = body.y;
            tail.direction = body.direction;
        }

        // Move Head
        switch (direction) {
            case Direction.LEFT -> {
                if (x - 1 < 0) wallHitDetected();
                else x = x - 1;
            }
            case Direction.UP -> {
                if (y - 1 < 0) wallHitDetected();
                else y = y - 1;
            }
            case Direction.RIGHT -> {
                if (x + 1 > FIELD_WIDTH - 1) wallHitDetected();
                else x = x + 1;
            }
            case Direction.DOWN -> {
                if (y + 1 > FIELD_HEIGHT - 1) wallHitDetected();
                else y = y + 1;
            }
        }
    }

    // Check Collision with itself
    public boolean checkCollision() {
        Rectangle head = snakeParts.getFirst().getBounds();
        for (var i = 1; i < snakeParts.size(); i++) if (snakeParts.get(i).getBounds().intersects(head)) return true;
        return false;
    }

    // Check Food Collision
    public boolean checkFood(Food food) {
        Rectangle foodHitbox = food.getBounds();
        for (SnakePart snakePart : snakeParts)
            if (snakePart.getBounds().intersects(foodHitbox)) return true;
        return false;
    }

    // Grow
    public void grow() {

        // Add Tail
        SnakePart tail = snakeParts.getLast();
        snakeParts.add(new SnakePart(tail.x, tail.y, PartType.FEET));

        // Rearrange Images
        var size = snakeParts.size();
        for (var i = 0; i < size; i++) {
            SnakePart snakePart = snakeParts.get(i);
            PartType type = PartType.LEG_TILE.getType(i, size);
            snakePart.image = type.getImage();
            snakePart.animation = type.getAnimation();
        }
    }

    // Getter
    public ArrayList<SnakePart> getSnakeParts() {
        return snakeParts;
    }

    // Enum
    public enum PartType {

        // Constants
        HEAD(Config.HEAD, HEAD_ANIMATION),
        UPPER_BODY(Config.UPPER_BODY, UPPER_BODY_ANIMATION),
        LOWER_BODY(Config.LOWER_BODY, LOWER_BODY_ANIMATION),
        LEG_TILE(Config.LEG_TILE, LEG_TILE_ANIMATION),
        LEG_TRANSITION(Config.LEG_TRANSITION, LEG_TRANSITION_ANIMATION),
        FEET(Config.FEET, FEET_ANIMATION);

        // Attributes
        private final BufferedImage image;
        private final ImageIcon animation;

        // Constructor
        PartType(BufferedImage image, ImageIcon animation) {
            this.image = image;
            this.animation = animation;
        }

        public BufferedImage getImage() {
            return image;
        }

        public ImageIcon getAnimation() {
            return animation;
        }

        public PartType getType(int index, int size) {
            PartType type = switch (index) {
                case 0 -> HEAD;
                case 1 -> UPPER_BODY;
                case 2 -> LOWER_BODY;
                default -> LEG_TILE;
            };
            if (index == size - 2 && size > 2) type = LEG_TRANSITION;
            if (index == size - 1 && size > 3) type = FEET;
            return type;
        }
    }
}