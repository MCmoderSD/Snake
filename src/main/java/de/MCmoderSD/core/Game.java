package de.MCmoderSD.core;

import de.MCmoderSD.UI.Frame;
import de.MCmoderSD.UI.InputHandler;
import de.MCmoderSD.UI.UI;
import de.MCmoderSD.main.Config;
import de.MCmoderSD.main.Main;
import de.MCmoderSD.objects.Food;
import de.MCmoderSD.objects.Snake;
import de.MCmoderSD.utilities.Calculate;
import de.MCmoderSD.utilities.sound.AudioPlayer;

@SuppressWarnings("BusyWait")
public class Game implements Runnable{

    // Associations
    private final Config config;
    private final Frame frame;
    private final UI ui;
    private final AudioPlayer audioPlayer;
    private final InputHandler inputs;
    private final Snake snake;
    private final double tickrate;

    // Game Variables
    private Food food;
    private Thread ult;
    private int score;
    private boolean isPaused;
    private boolean gameOver;
    private double speedModifier;
    private boolean gameStarted;
    private boolean ultActive;

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
        audioPlayer = config.getAudioPlayer();

        // Init Objects
        snake = new Snake(config.getFieldWidth()/2 - 2, config.getFieldHeight()/2, config.getHead(), config.getHeadAnimation(), this, config);
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
                    if (snake.checkCollision() && !ultActive) gameOver();

                    // Check Collision with Food
                    if (snake.checkFood(food)) {

                        // Interact with Food
                        score++;
                        snake.grow(config);
                        audioPlayer.playAudio(food.getSound());
                        ui.setScore(score);

                        if (food.isSpecial()) activateUlt();

                        food = new Food(config, snake.getSnakePieces());
                    }

                    // Check Input and Move Snake
                    snake.updateDirection(inputs.getDirection());
                    snake.moveSnake();

                    // Update Frame
                    frame.repaint();



                    // Game Loop End:
                    delta--;
                }
                ui.requestFocusInWindow();
            }

            // Delay to prevent 100% CPU Usage
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ui.requestFocusInWindow();
        }
    }

    // Methods
    private void activateUlt() {
        if (ult != null && ult.isAlive()) ult.interrupt();

        ult = new Thread(() -> {
            try {
                ultActive = true;
                audioPlayer.playAudio(config.getUltSound());
                setSpeedModifier(2);
                Thread.sleep(7000);
                setSpeedModifier(1);
                ultActive = false;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        ult.start();
    }


    // Setter
    public void togglePause() {
        if (ultActive) return;
        if (isPaused) audioPlayer.pauseAll();
        else audioPlayer.resumeAll();
        isPaused = !isPaused;
    }

    public void gameOver() {
        // ToDo Game Over
        audioPlayer.playAudio(config.getDieSound());
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

    public boolean isUltActive() {
        return ultActive;
    }
}
