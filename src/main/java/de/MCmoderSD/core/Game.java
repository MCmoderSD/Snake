package de.MCmoderSD.core;

import de.MCmoderSD.UI.Frame;
import de.MCmoderSD.UI.InputHandler;
import de.MCmoderSD.main.Config;
import de.MCmoderSD.main.Main;
import de.MCmoderSD.objects.Food;
import de.MCmoderSD.objects.Snake;

@SuppressWarnings("BusyWait")
public class Game implements Runnable{
    private final Config config;
    private final Frame frame;
    private final InputHandler inputs;
    private final Snake snake;

    // Game Variables
    private Food food;
    private int score = 0;
    private boolean isPaused = false;

    public Game(Config config) {
        this.config = config;

        frame = new Frame(config, this);
        inputs = frame.getInputs();
        snake = new Snake(config);
        food = new Food(config, snake.getSnakePieces());

        new Thread(this).start();
    }

    @Override
    public void run() {
        while (Main.isRunning) {
            // Timer
            double tickrate = 100000000, delta = 0;
            long current, now = System.nanoTime();


            // Game Loop
            while (!isPaused) {
                current = System.nanoTime();
                delta += (current - now) / tickrate;
                now = current;

                if (delta >= 1) {
                    // Game Loop Start:



                    // Check Collision with itself
                    snake.checkCollision();

                    // Check Collision with Food
                    if (snake.checkFood(food)) {

                        // Interact with Food
                        snake.grow(config);
                        score++;
                        // ToDo Play Sound
                        food = new Food(config, snake.getSnakePieces());
                    }

                    // Check Input and Move Snake
                    snake.updateDirection(inputs.getDirection());
                    snake.moveSnake();

                    // Check for Win
                    //if (config.getFieldWidth() * config.getFieldHeight() == snake.getSnakePieces().size()) // ToDo Win

                    // Update Frame
                    frame.repaint();



                    // Game Loop End:
                    delta--;
                }
            }

            // Delay to prevent 100% CPU Usage
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Setter
    public void togglePause() {
        isPaused = !isPaused;
    }

    // Getter
    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }
}
