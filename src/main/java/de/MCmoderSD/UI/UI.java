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
    private final Game game;
    private final InputHandler inputs;
    public UI(Config config, Game game) {
        this.game = game;

        // Set UI Attributes
        setPreferredSize(config.getDimension());
        setFocusable(true);
        requestFocus();

        // Add InputHandler
        addKeyListener(inputs = new InputHandler(game));

        // Debug
        setBackground(new Color(49, 51, 56));
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        // Cast Graphics to Graphics2D
        Graphics2D g = (Graphics2D) graphics;

        // Temp Variables
        Snake snake = game.getSnake();
        ArrayList<SnakePiece> snakePieces = snake.getSnakePieces();
        Food food = game.getFood();

        // Draw Snake Head
        g.setColor(Color.red);
        g.draw(snakePieces.get(0));

        // Draw Snake Body
        g.setColor(Color.green);
        for (int i = 1; i < game.getSnake().getSnakePieces().size(); i++) g.draw(snakePieces.get(i));

        // Draw Food
        g.setColor(Color.yellow);
        g.draw(food);
    }

    // Getter
    public InputHandler getInputs() {
        return inputs;
    }
}
