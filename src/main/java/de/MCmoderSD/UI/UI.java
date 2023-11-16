package de.MCmoderSD.UI;

import de.MCmoderSD.core.Game;
import de.MCmoderSD.main.Config;
import de.MCmoderSD.objects.Food;
import de.MCmoderSD.objects.Snake;
import de.MCmoderSD.objects.SnakePiece;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
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
        Font defaultFont = new Font("Roboto", Font.PLAIN, config.getScale()/2);

        // Score Label
        scoreLabel = new JLabel(config.getScore() + ": " + game.getScore());
        scoreLabel.setFont(defaultFont);
        scoreLabel.setForeground(Color.YELLOW);
        scoreLabel.setSize(config.getScale() * 3, config.getScale());
        scoreLabel.setLocation((config.getFieldWidth() - 3) * config.getScale(), 0);
        add(scoreLabel);

        // FPS Label
        fpsLabel = new JLabel();
        fpsLabel.setFont(defaultFont);
        fpsLabel.setForeground(Color.YELLOW);
        fpsLabel.setSize(config.getScale() * 3, config.getScale());
        fpsLabel.setLocation(config.getScale()/4, 0);
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
        resetButton.setForeground(Color.black);
        resetButton.setVisible(false);
        add(resetButton);
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
        g.drawImage(food.getImage(), food.getPositionX(), food.getPositionY(), null);

        // Draw Snake
        for (int i = snakePieces.size()-1; i >= 0; i--) {
            SnakePiece snakePiece = snakePieces.get(i);
            BufferedImage image = snakePiece.getImage();
            ImageIcon animation = snakePiece.getAnimation();

            g.transform(snakePiece.getTransform());
            g.drawImage(image, 0, 0, null);

            if (game.isUltActive()) g.drawImage(animation.getImage(), 0, 0, null);

            g.setTransform(new AffineTransform()); // Reset Transform
        }

        // Debug

        // Draw Hitbox
        g.setColor(Color.RED);
        if (hitbox || debug) for (SnakePiece snakePiece : snakePieces) g.draw(snakePiece.getBounds());

        // Draw Grid Lines
        if (gridLines || debug) {
            g.setColor(Color.BLACK);
            for (int x = 0; x < config.getFieldWidth(); x++) g.drawLine(x * config.getScale(), 0, x * config.getScale(), config.getFieldHeight() * config.getScale());
            for (int y = 0; y < config.getFieldHeight(); y++) g.drawLine(0, y * config.getScale(), config.getFieldWidth() * config.getScale(), y * config.getScale());
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