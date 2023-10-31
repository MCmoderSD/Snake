package de.MCmoderSD.core;

import de.MCmoderSD.UI.Frame;
import de.MCmoderSD.UI.InputHandler;
import de.MCmoderSD.main.Config;
import de.MCmoderSD.main.Main;
import de.MCmoderSD.objects.Food;
import de.MCmoderSD.objects.Snake;

@SuppressWarnings("BusyWait")
public class Game implements Runnable{

    // Associations
    private final Config config;
    private final Frame frame;
    private final InputHandler inputs;
    private final Snake snake;

    // Game Variables
    private Food food;
    private int score;
    private boolean isPaused;
    private boolean gameOver;

    public Game(Config config) {
        this.config = config;

        // Init Game Variables
        score = 0;
        isPaused = false;
        gameOver = false;

        // Init UI
        frame = new Frame(config, this);
        inputs = frame.getInputs();

        // Init Objects
        snake = new Snake(this, config);
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
            while (!isPaused && !gameOver) {
                current = System.nanoTime();
                delta += (current - now) / tickrate;
                now = current;

                if (delta >= 1) {
                    // Game Loop Start:


                    // Check for win
                    if (config.getFieldWidth() * config.getFieldHeight() == snake.getSnakePieces().size()) {
                        // ToDo Win
                        System.out.println("Win");
                        gameOver();
                    }

                    // Check Collision with itself
                    if (snake.checkCollision()) gameOver();

                    // Check Collision with Food
                    if (snake.checkFood(food)) {

                        // Interact with Food
                        snake.grow(config);
                        score++;
                        // ToDo Play Sound
                        food = new Food(config, snake.getSnakePieces());
                        System.out.println("Score: " + score);
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

    public void gameOver() {
        // ToDo Game Over
        gameOver = true;
    }

    // Getter
    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }
}
