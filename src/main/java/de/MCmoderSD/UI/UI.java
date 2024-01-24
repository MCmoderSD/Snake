package de.MCmoderSD.UI;

import de.MCmoderSD.core.Game;
import de.MCmoderSD.main.Config;
import de.MCmoderSD.objects.BackgroundTile;
import de.MCmoderSD.objects.Food;
import de.MCmoderSD.objects.Snake;
import de.MCmoderSD.objects.SnakePiece;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


@SuppressWarnings("unused")
public class UI extends JPanel {

    // Associations
    private final Game game;
    private final InputHandler inputs;

    // UI Components
    private final ArrayList<BackgroundTile> background;
    private final JLabel fpsLabel;
    private final JLabel scoreLabel;
    private final JButton resetButton;

    // Constructor
    public UI(Frame frame, Config config) {

        // Set UI Attributes
        setPreferredSize(Config.DIMENSION);
        setDoubleBuffered(true);
        setLayout(null);

        // Init Game
        game = new Game(this, config);

        // Add InputHandler
        addKeyListener(inputs = new InputHandler(game));

        // UI Components
        Font defaultFont = new Font("Roboto", Font.PLAIN, Config.SCALE / 2);

        // Background
        background = new ArrayList<>();
        for (int x = 0; x < Config.FIELD_WIDTH; x++)
            for (int y = 0; y < Config.FIELD_HEIGHT; y++)
                background.add(new BackgroundTile(x, y));

        // FPS Label
        fpsLabel = new JLabel();
        fpsLabel.setFont(defaultFont);
        fpsLabel.setForeground(Config.FPS_COLOR);
        fpsLabel.setSize(Config.SCALE * 3, Config.SCALE);
        fpsLabel.setLocation(Config.SCALE / 4, 0);
        fpsLabel.setVisible(game.isShowFPS());
        add(fpsLabel);

        // Score Label
        scoreLabel = new JLabel(Config.SCORE_PREFIX + game.getScore());
        scoreLabel.setFont(defaultFont);
        scoreLabel.setForeground(Config.SCORE_COLOR);
        scoreLabel.setSize(Config.SCALE * 3, Config.SCALE);
        scoreLabel.setLocation((Config.SCALE - 3) * Config.SCALE, 0);
        add(scoreLabel);

        int buttonWidth = Config.DIMENSION.width / 4;
        int buttonHeight = Config.DIMENSION.height / 6;

        // Reset Button
        resetButton = new JButton(Config.RESTART);
        resetButton.setBorder(null);
        resetButton.setFocusable(false);
        resetButton.setSize(buttonWidth, buttonHeight);
        resetButton.setToolTipText(Config.RESTART_TOOL_TIP);
        resetButton.setFont(new Font("Roboto", Font.PLAIN, buttonHeight / 2));
        resetButton.setLocation((Config.DIMENSION.width - buttonWidth) / 2, (Config.DIMENSION.height - buttonHeight) / 2);
        resetButton.addActionListener(e -> game.reset());
        resetButton.setBackground(Color.white);
        resetButton.setForeground(Config.TEXT_COLOR);
        resetButton.setVisible(game.isGameOver());
        add(resetButton);

        frame.add(this);
        frame.pack();
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
        for (BackgroundTile tile : background) {
            g.setColor(tile.getColor());
            g.fill(tile.getBounds());
            g.drawImage(tile.getImage(), tile.getX() * tile.getScale(), tile.getY() * tile.getScale(), null);
        }

        // Draw Food ToDo fix animation
        g.setColor(food.getColor());
        g.drawImage(food.isOp() ? food.getAnimation().getImage() : food.getImage(), food.getX() * food.getScale(), food.getY() * food.getScale(), null);

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

        // Draw Grid Lines
        if (game.isShowGridLines() || game.isDebug()) {
            for (BackgroundTile tile : background) {
                g.setColor(tile.getHitboxColor());
                g.draw(tile.getBounds());
            }
        }

        // Draw Hitboxes
        if (game.isShowHitboxes() || game.isDebug()) {

            //Snake
            g.setColor(snake.getHitboxColor());
            for (SnakePiece snakePiece : snakePieces) g.draw(snakePiece.getBounds());

            // Food
            g.setColor(food.getHitboxColor());
            g.draw(food.getBounds());
        }

        // Update UI Components
        fpsLabel.setVisible(game.isShowFPS());
        paintComponents(g);
    }

    // Getter
    public InputHandler getInputs() {
        return inputs;
    }

    // Setter
    public void setFps(int fps) {
        fpsLabel.setText(Config.FPS_PREFIX + fps);
    }

    public void setScore(int score) {
        scoreLabel.setText(Config.SCORE_PREFIX + score);
    }

    public void setGameOver(boolean gameOver) {
        resetButton.setVisible(gameOver);
    }
}