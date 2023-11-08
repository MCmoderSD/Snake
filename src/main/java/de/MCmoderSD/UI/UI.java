package de.MCmoderSD.UI;

import de.MCmoderSD.core.Game;
import de.MCmoderSD.main.Config;
import de.MCmoderSD.objects.Food;
import de.MCmoderSD.objects.Snake;
import de.MCmoderSD.objects.SnakePiece;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class UI extends JPanel {

    // Associations
    private final Game game;
    private final Config config;
    private final InputHandler inputs;

    // UI Components
    private final JLabel scoreLabel;

    // Constructor
    public UI(Config config, Game game) {
        this.game = game;
        this.config = config;

        // Set UI Attributes
        setPreferredSize(config.getDimension());
        setFocusable(true);
        requestFocus();
        setDoubleBuffered(true);
        setLayout(null);

        // Add InputHandler
        addKeyListener(inputs = new InputHandler(game));

        scoreLabel = new JLabel("Score: " + game.getScore());
        scoreLabel.setFont(new Font("Roboto", Font.PLAIN, config.getScale()/2));
        scoreLabel.setForeground(Color.YELLOW);
        scoreLabel.setSize(config.getScale() * 3, config.getScale());
        scoreLabel.setLocation((config.getFieldWidth() - 3) * config.getScale(), 0);
        add(scoreLabel);
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
        g.drawImage(food.getImage(), food.x, food.y, null);

        // Draw Snake
        for (int i = snakePieces.size()-1; i >= 0; i--) {
            SnakePiece snakePiece = snakePieces.get(i);

            g.setTransform(snakePiece.getTransform());
            g.drawImage(snakePiece.getImage(), 0, 0, null);
            g.setTransform(new AffineTransform());
        }

        // Draw UI Components
        paintComponents(g);
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