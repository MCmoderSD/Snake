package de.MCmoderSD.UI;

import de.MCmoderSD.core.Game;
import de.MCmoderSD.main.Config;
import de.MCmoderSD.objects.Food;
import de.MCmoderSD.objects.Snake;
import de.MCmoderSD.objects.SnakePiece;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class UI extends JPanel {

    // Associations
    private final Config config;
    private final Game game;
    private final InputHandler inputs;

    // UI Components
    private final JLabel scoreLabel;

    // Constructor
    public UI(Config config, Game game) {
        this.config = config;
        this.game = game;

        // Set UI Attributes
        setPreferredSize(config.getDimension());
        setFocusable(true);
        requestFocus();
        setDoubleBuffered(true);
        setLayout(null);

        // Add InputHandler
        addKeyListener(inputs = new InputHandler(game));

        // Init UI Components
        scoreLabel = new JLabel("Score: " + game.getScore());
        scoreLabel.setFont(new Font("Roboto", Font.PLAIN, config.getScale()/2));
        scoreLabel.setForeground(Color.YELLOW);
        scoreLabel.setSize(config.getScale() * 2, config.getScale());
        scoreLabel.setLocation((config.getFieldWidth() - 2) * config.getScale(), 0);
        add(scoreLabel);

        // Debug
        setBackground(new Color(36, 41, 46));
    }

    // Render Engine
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        // Cast Graphics to Graphics2D
        Graphics2D g = (Graphics2D) graphics;

        // Background ToDo fix Background
        //for (int x = 0; x < config.getFieldWidth(); x++) for (int y = 0; y < config.getFieldHeight(); y++) g.drawImage(config.getBackgroundTile(), x * config.getScale(), y * config.getScale(), null);

        // Temp Variables
        Snake snake = game.getSnake();
        ArrayList<SnakePiece> snakePieces = snake.getSnakePieces();
        Food food = game.getFood();

        // Draw Food
        g.drawImage(food.getImage(), food.x, food.y, null);

        // Draw Snake Head
        g.drawImage(snake.getImage(), snakePieces.get(0).x, snakePieces.get(0).y, null);

        // Draw Snake Body
        for (int i = 1; i < game.getSnake().getSnakePieces().size(); i++) g.drawImage(snakePieces.get(i).getImage(), snakePieces.get(i).x, snakePieces.get(i).y, null);
    }

    // Getter
    public InputHandler getInputs() {
        return inputs;
    }

    // Setter
    public void setScore(int score) {
        scoreLabel.setText("Score: " + score);
    }
}