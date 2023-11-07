package de.MCmoderSD.core;

import de.MCmoderSD.UI.Frame;
import de.MCmoderSD.UI.InputHandler;
import de.MCmoderSD.UI.UI;
import de.MCmoderSD.main.Config;
import de.MCmoderSD.main.Main;
import de.MCmoderSD.objects.Food;
import de.MCmoderSD.objects.Snake;
import de.MCmoderSD.utilities.Calculate;

@SuppressWarnings("BusyWait")
public class Game implements Runnable{

    // Associations
    private final Config config;
    private final Frame frame;
    private final UI ui;
    private final InputHandler inputs;
    private final Snake snake;
    private final double tickrate;

    // Game Variables
    private Food food;
    private int score;
    private boolean isPaused;
    private boolean gameOver;
    private double speedModifier;
    private boolean gameStarted;

    public Game(Config config) {
        this.config = config;

        // Init Game Variables
        score = 0;
        speedModifier = 1;
        isPaused = false;
        gameOver = false;
        gameStarted = false;
        tickrate = Calculate.calculateTickrate(config.getTps());

        // Init UI
        frame = new Frame(config, this);
        ui = frame.getUI();
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
            double delta = 0;
            long current, now = System.nanoTime();


            // Game Loop
            while (!isPaused && !gameOver) {
                current = System.nanoTime();
                delta += (current - now) / (tickrate/speedModifier);
                now = current;

                // Wait for Start
                if (!gameStarted) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    delta = 0;
                }

                // Tick
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
                        ui.setScore(score);
                        // ToDo Play Sound
                        food = new Food(config, snake.getSnakePieces());
                        System.out.println("Score: " + score);
                    }

                    // Check Input and Move Snake
                    snake.updateDirection(inputs.getDirection());
                    snake.moveSnake();

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

    public void start() {
        gameStarted = true;
    }

    public void setSpeedModifier(double speedModifier) {
        this.speedModifier = speedModifier;
    }

    // Getter
    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }

    public String getScore() {
        return String.valueOf(score);
    }
}
