package de.MCmoderSD.UI;

import de.MCmoderSD.core.Game;
import de.MCmoderSD.main.Config;
import de.MCmoderSD.objects.Food;
import de.MCmoderSD.objects.Snake;
import de.MCmoderSD.objects.SnakePiece;

import java.awt.*;
import javax.swing.JPanel;
import java.util.ArrayList;

public class UI extends JPanel {

    // Associations
    private final Config config;
    private final Game game;
    private final InputHandler inputs;

    // Constructor
    public UI(Config config, Game game) {
        this.config = config;
        this.game = game;

        // Set UI Attributes
        setPreferredSize(config.getDimension());
        setFocusable(true);
        requestFocus();
        setDoubleBuffered(true);

        // Add InputHandler
        addKeyListener(inputs = new InputHandler(game));

        // Debug
        setBackground(new Color(49, 51, 56));
    }

    // Render Engine
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        // Cast Graphics to Graphics2D
        Graphics2D g = (Graphics2D) graphics;

        // Background
        for (int x = 0; x < config.getFieldWidth(); x++) for (int y = 0; y < config.getFieldHeight(); y++) g.drawImage(config.getBackgroundTile(), x * config.getScale(), y * config.getScale(), null);

        // Temp Variables
        Snake snake = game.getSnake();
        ArrayList<SnakePiece> snakePieces = snake.getSnakePieces();
        Food food = game.getFood();

        // Draw Food
        g.drawImage(config.getFood(), food.x, food.y, null);

        // Draw Snake Head
        g.drawImage(config.getSnakeHead(), snakePieces.get(0).x, snakePieces.get(0).y, null);

        // Draw Snake Body
        for (int i = 1; i < game.getSnake().getSnakePieces().size(); i++) g.drawImage(config.getSnakeBody(), snakePieces.get(i).x, snakePieces.get(i).y, null);
    }

    // Getter
    public InputHandler getInputs() {
        return inputs;
    }
}
