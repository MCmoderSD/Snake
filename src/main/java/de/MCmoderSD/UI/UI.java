package de.MCmoderSD.UI;

import de.MCmoderSD.core.Game;
import de.MCmoderSD.main.Config;
import de.MCmoderSD.objects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


@SuppressWarnings("unused")
public class UI extends JPanel {

    // Associations
    private final Game game;
    private final Config config;
    private final InputHandler inputs;

    // UI Components
    private final Background background;
    private final JLabel scoreLabel;
    private final JLabel fpsLabel;
    private final JButton resetButton;

    // Attributes
    private boolean showFps;
    private boolean debug;
    private boolean hitbox;
    private boolean gridLines;

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

        showFps = false;
        debug = false;
        hitbox = false;
        gridLines = false;

        int width = config.getFieldWidth() * config.getScale();
        int height = config.getFieldHeight() * config.getScale();

        // Add InputHandler
        addKeyListener(inputs = new InputHandler(game, this));

        // UI Components
        Font defaultFont = new Font("Roboto", Font.PLAIN, config.getScale() / 2);

        background = new Background(config);

        // Score Label
        scoreLabel = new JLabel(config.getScore() + ": " + game.getScore());
        scoreLabel.setFont(defaultFont);
        scoreLabel.setForeground(config.getScoreColor());
        scoreLabel.setSize(config.getScale() * 3, config.getScale());
        scoreLabel.setLocation((config.getFieldWidth() - 3) * config.getScale(), 0);
        add(scoreLabel);

        // FPS Label
        fpsLabel = new JLabel();
        fpsLabel.setFont(defaultFont);
        fpsLabel.setForeground(config.getFpsColor());
        fpsLabel.setSize(config.getScale() * 3, config.getScale());
        fpsLabel.setLocation(config.getScale() / 4, 0);
        fpsLabel.setVisible(showFps);
        add(fpsLabel);

        int buttonWidth = width / 4;
        int buttonHeight = height / 6;

        // Reset Button
        resetButton = new JButton(config.getRestart());
        resetButton.setBorder(null);
        resetButton.setFocusable(false);
        resetButton.setSize(buttonWidth, buttonHeight);
        resetButton.setToolTipText(config.getRestart());
        resetButton.setFont(new Font("Roboto", Font.PLAIN, buttonHeight / 2));
        resetButton.setLocation((width - buttonWidth) / 2, (height - buttonHeight) / 2);
        resetButton.addActionListener(e -> game.reset());
        resetButton.setBackground(Color.white);
        resetButton.setForeground(config.getTextColor());
        resetButton.setVisible(false);
        add(resetButton);
    }

    // Render Engine
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        // Cast Graphics to Graphics2D
        Graphics2D g = (Graphics2D) graphics;

        // Temp Variables
        Snake snake = game.getSnake();
        ArrayList<SnakePiece> snakePieces = snake.getSnakePieces();
        Food food = game.getFood();

        // Background
        for (BackgroundTile tile : background.getBackgroundTilesList()) {
            g.setColor(tile.getColor());
            g.fill(tile.getBounds());
            g.drawImage(tile.getImage(), tile.getPositionX(), tile.getPositionY(), null);
        }

        // Draw Food
        g.setColor(food.getColor());
        g.drawImage(food.getImage(), food.getPositionX(), food.getPositionY(), null);

        // Draw Snake
        for (int i = snakePieces.size() - 1; i >= 0; i--) {
            SnakePiece snakePiece = snakePieces.get(i);
            BufferedImage image = snakePiece.getImage();
            ImageIcon animation = snakePiece.getAnimation();

            g.setColor(snakePiece.getColor());
            g.fill(snakePiece.getBounds());
            g.transform(snakePiece.getTransform());
            g.drawImage(image, 0, 0, null);

            if (game.isUltActive()) g.drawImage(animation.getImage(), 0, 0, null);

            g.setTransform(new AffineTransform()); // Reset Transform
        }

        // Debug

        // Draw Grid Lines
        if (gridLines || debug) {
            for (BackgroundTile tile : background.getBackgroundTilesList()) {
                g.setColor(tile.getHitboxColor());
                g.draw(tile.getBounds());
            }
        }

        // Draw Hitboxes
        if (debug) { // Debug Mode

            // Snake
            g.setColor(snake.getHitboxColor());
            for (SnakePiece snakePiece : snakePieces) g.draw(snakePiece.getSmallBounds());

            // Food
            g.setColor(snake.getHitboxColor());
            g.draw(food.getSmallBounds());

        } else if (hitbox) { // Hitbox Mode

            // Snake
            g.setColor(snake.getHitboxColor());
            for (SnakePiece snakePiece : snakePieces) g.draw(snakePiece.getBounds());

            // Food
            g.setColor(food.getHitboxColor());
            g.draw(food.getBounds());
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
        scoreLabel.setText(config.getScore() + ": " + score);
    }

    public void setFps(int fps) {
        fpsLabel.setText("FPS: " + fps);
    }

    public void toggleFps() {
        fpsLabel.setVisible(!fpsLabel.isVisible());
        showFps = !showFps;
    }

    public void setFps(boolean fps) {
        fpsLabel.setVisible(fps);
        showFps = fps;
    }

    public void toggleHitbox() {
        hitbox = !hitbox;
    }

    public void setHitbox(boolean hitbox) {
        this.hitbox = hitbox;
    }

    public void toggleGridLines() {
        gridLines = !gridLines;
    }

    public void setGridLines(boolean gridLines) {
        this.gridLines = gridLines;
    }

    public void toggleDebug() {
        debug = !debug;
        hitbox = !hitbox;
        gridLines = !gridLines;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
        hitbox = debug;
        gridLines = debug;
    }

    public void setResetButton(boolean visible) {
        resetButton.setVisible(visible);
    }
}