package de.MCmoderSD.UI;

import de.MCmoderSD.core.Game;
import de.MCmoderSD.objects.Food;
import de.MCmoderSD.objects.Snake;
import de.MCmoderSD.objects.SnakePart;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.geom.AffineTransform;

import java.util.ArrayList;

import static de.MCmoderSD.main.Config.*;
import static de.MCmoderSD.utilities.Util.divide;
import static java.awt.Color.white;


@SuppressWarnings("unused")
public class UI extends JPanel {

    // Associations
    private final Game game;
    private final InputHandler inputs;

    // UI Components
    private final JLabel fpsLabel;
    private final JLabel tpsLabel;
    private final JLabel scoreLabel;
    private final JButton resetButton;

    // Constructor
    public UI(Frame frame) {

        // Set UI Attributes
        setPreferredSize(DIMENSION);
        setFocusable(true);
        setLayout(null);

        // Init Game
        game = new Game(this);
        inputs = new InputHandler(game);

        // Add InputHandler
        addKeyListener(inputs);

        // UI Components
        Font defaultFont = new Font("Roboto", Font.PLAIN, divide(SCALE, 2));

        // FPS Label
        fpsLabel = new JLabel();
        fpsLabel.setFont(defaultFont);
        fpsLabel.setForeground(FPS_COLOR);
        fpsLabel.setSize(SCALE * 3, SCALE);
        fpsLabel.setLocation(divide(SCALE, 4), 0);
        fpsLabel.setVisible(game.isShowFPS());
        add(fpsLabel);

        // TPS Label
        tpsLabel = new JLabel();
        tpsLabel.setFont(defaultFont);
        tpsLabel.setForeground(TPS_COLOR);
        tpsLabel.setSize(SCALE * 3, SCALE);
        tpsLabel.setLocation(divide(SCALE, 4), SCALE);
        tpsLabel.setVisible(game.isShowFPS());
        add(tpsLabel);

        // Score Label
        scoreLabel = new JLabel(SCORE_PREFIX + game.getScore());
        scoreLabel.setFont(defaultFont);
        scoreLabel.setForeground(SCORE_COLOR);
        scoreLabel.setSize(SCALE * 3, SCALE);
        scoreLabel.setLocation((SCALE - 3) * SCALE, 0);
        add(scoreLabel);

        var buttonWidth = divide(DIMENSION.width, 4);
        var buttonHeight = divide(DIMENSION.height, 6);

        // Reset Button
        resetButton = new JButton(RESTART);
        resetButton.setBorder(null);
        resetButton.setFocusable(false);
        resetButton.setSize(buttonWidth, buttonHeight);
        resetButton.setToolTipText(RESTART_TOOL_TIP);
        resetButton.setFont(new Font("Roboto", Font.PLAIN, divide(buttonHeight, 2)));
        resetButton.setLocation(divide(DIMENSION.width - buttonWidth, 2), divide(DIMENSION.height - buttonHeight, 2));
        resetButton.addActionListener(e -> game.reset());
        resetButton.setBackground(white);
        resetButton.setForeground(TEXT_COLOR);
        resetButton.setVisible(game.isGameOver());
        add(resetButton);

        // Add UI to Frame
        requestFocus();
        frame.add(this);
        frame.pack();
    }

    // Render Engine
    @Override
    public void paint(Graphics graphics) {

        // Graphics
        super.paint(graphics);
        Graphics2D g = (Graphics2D) graphics;

        // Variables
        Food food = game.getFood();
        Snake snake = game.getSnake();
        ArrayList<SnakePart> snakeParts = snake.getSnakeParts();

        // Background
        g.setColor(BACKGROUND_COLOR);
        for (var x = 0; x < FIELD_WIDTH; x++) for (var y = 0; y < FIELD_HEIGHT; y++) {
            g.fillRect(x * SCALE, y * SCALE, SCALE, SCALE);
            g.drawImage(BACKGROUND_TILE, x * SCALE, y * SCALE, null);
        }

        // Draw Food
        g.setColor(food.getColor());
        var foodImage = food.isOp() ? OP_FOOD_ANIMATION.getImage() : food.getImage();
        g.drawImage(foodImage, food.getX() * SCALE, food.getY() * SCALE, null);
        if (food.isOp()) g.drawImage(BACKGROUND_COVER, food.getX() * SCALE, food.getY() * SCALE, null);

        // Draw Snake
        for (var i = snakeParts.size() - 1; i >= 0; i--) {

            // Variables
            SnakePart snakePart = snakeParts.get(i);

            // Sync Graphics
            Toolkit.getDefaultToolkit().sync();

            // Draw Snake Part
            g.setColor(SNAKE_COLOR);
            g.fill(snakePart.getBounds());

            // Draw Snake Part Image
            g.transform(snakePart.getTransform());
            g.drawImage(snakePart.getImage(), 0, 0, null);
            if (game.isUltActive()) g.drawImage(snakePart.getAnimation().getImage(), 0, 0, null);

            // Reset Transform
            g.setTransform(new AffineTransform());
        }

        // Draw Grid Lines
        g.setColor(GRID_LAYOUT_COLOR);
        if (game.isShowGridLines() || game.isDebug()) for (var x = 0; x < FIELD_WIDTH; x++) for (var y = 0; y < FIELD_HEIGHT; y++) g.drawRect(x * SCALE, y * SCALE, SCALE, SCALE);

        // Draw Hitboxes
        if (game.isShowHitboxes() || game.isDebug()) {

            //Snake
            g.setColor(SNAKE_HITBOX_COLOR);
            for (SnakePart snakePart : snakeParts) g.draw(snakePart.getBounds());

            // Food
            g.setColor(FOOD_HITBOX_COLOR);
            g.draw(food.getBounds());
        }

        // Sync Graphics
        Toolkit.getDefaultToolkit().sync();

        // Update UI Components
        fpsLabel.setVisible(game.isShowFPS());
        tpsLabel.setVisible(game.isShowTPS());
        paintComponents(g);
    }

    // Getter
    public InputHandler getInputs() {
        return inputs;
    }

    // Setter
    public void setFps(int fps) {
        fpsLabel.setText(FPS_PREFIX + fps);
    }

    public void setTps(int tps) {
        tpsLabel.setText(TPS_PREFIX + tps);
    }

    public void setScore(int score) {
        scoreLabel.setText(SCORE_PREFIX + score);
    }

    public void setGameOver(boolean gameOver) {
        resetButton.setVisible(gameOver);
    }
}