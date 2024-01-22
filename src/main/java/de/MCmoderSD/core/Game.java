package de.MCmoderSD.core;

import de.MCmoderSD.UI.UI;
import de.MCmoderSD.main.Config;
import de.MCmoderSD.main.Main;
import de.MCmoderSD.objects.Food;
import de.MCmoderSD.objects.Snake;
import de.MCmoderSD.utilities.Calculate;
import de.MCmoderSD.utilities.sound.AudioPlayer;

@SuppressWarnings("BusyWait")
public class Game implements Runnable {

    // Associations
    private final UI ui;
    private final AudioPlayer audioPlayer;

    // Attributes
    private final double tickrate;

    // Game Variables
    private Thread ult;
    private int score;
    private boolean isPaused;
    private boolean gameOver;
    private double speedModifier;
    private boolean gameStarted;
    private boolean ultActive;

    // Debug Variables
    private boolean debug;
    private boolean showFPS;
    private boolean showHitboxes;
    private boolean showGridLines;

    // Objects
    private Snake snake;
    private Food food;

    public Game(UI ui, Config config) {

        // Init Associations
        this.ui = ui;
        this.audioPlayer = config.getAudioPlayer();

        // Init Game Variables
        score = 0;
        speedModifier = 1;
        isPaused = false;
        gameOver = false;
        gameStarted = false;
        ultActive = false;
        tickrate = Calculate.tickrate(Config.TPS);

        // Init Debug Variables
        debug = false;
        showFPS = false;
        showHitboxes = false;
        showGridLines = false;

        // Init Objects
        snake = new Snake(this, Config.FIELD_WIDTH / 2 - 2, Config.FIELD_HEIGHT / 2, Config.HEAD, Config.HEAD_ANIMATION);
        food = new Food(snake.getSnakePieces());

        new Thread(this).start();
    }

    @Override
    public void run() {
        while (Main.IS_RUNNING) {

            // Timer
            double delta = 0;
            long current;
            long timer = 0;
            long now = System.nanoTime();
            int renderedFrames = 0;


            // Game Loop
            while (!isPaused && !gameOver) {
                current = System.nanoTime();
                delta += (current - now) / (tickrate / speedModifier);
                timer += current - now;
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
                    if (Config.FIELD_WIDTH * Config.FIELD_HEIGHT == snake.getSnakePieces().size()) {
                        // ToDo Win
                        System.out.println("Win");
                        gameOver();
                    }

                    // Check Collision with itself
                    if (snake.checkCollision() && !ultActive) gameOver();

                    // Check Collision with Food
                    if (snake.checkFood(food)) {

                        // Interact with Food
                        score++; // Increase Score
                        ui.setScore(score); // Update Score
                        snake.grow(); // Grow Snake
                        audioPlayer.play(food.getSound()); // Play Sound

                        if (food.isSpecial()) activateUlt(food.isOp()); // Activate Ult

                        food = new Food(snake.getSnakePieces()); // Spawn new Food
                    }

                    // Check Input and Move Snake
                    snake.updateDirection(ui.getInputs().getDirection());
                    snake.moveSnake();

                    // Update Frame
                    if (renderedFrames < Config.FPS) {
                        ui.repaint();
                        renderedFrames++;
                    }

                    // FPS Counter
                    if (timer >= 1000000000) {
                        ui.setFps(renderedFrames);
                        timer = 0;
                        renderedFrames = 0;
                    }

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
    private void activateUlt(boolean isOp) {
        if (ult != null && ult.isAlive()) ult.interrupt();

        ult = new Thread(() -> {
            try {
                ultActive = true;
                audioPlayer.play(Config.ULT_SOUND);
                if (isOp) {
                    setSpeedModifier(Config.OP_ULT_SPEED_MODIFIER);
                    for (int i = 0; i < Config.SPECIAL_FOOD_DURATION; i++) {
                        snake.grow();
                        score++;
                        Thread.sleep(Config.OP_ULT_GROW_INTERVAL);
                    }
                    setSpeedModifier(1);
                } else {
                    setSpeedModifier(Config.ULT_SPEED_MODIFIER);
                    Thread.sleep((Config.SPECIAL_FOOD_DURATION * 1000));
                    setSpeedModifier(1);
                }

                ultActive = false;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        ult.start();
    }

    public void gameOver() {
        // ToDo Game Over
        audioPlayer.play(Config.DIE_SOUND);
        ui.setGameOver(true);
        gameOver = true;

        System.out.println("Game Over");
    }

    public void start() {
        gameStarted = true;
    }

    public void setSpeedModifier(double speedModifier) {
        this.speedModifier = speedModifier;
    }

    public void reset() {

        // Reset Game Variables
        score = 0;
        speedModifier = 1;
        isPaused = false;
        gameOver = false;
        gameStarted = false;
        ultActive = false;

        // Reset Objects
        snake = new Snake(this, Config.FIELD_WIDTH / 2 - 2, Config.FIELD_HEIGHT / 2, Config.HEAD, Config.HEAD_ANIMATION);
        food = new Food(snake.getSnakePieces());

        // Reset UI
        ui.setScore(score);
        ui.setGameOver(false);
        ui.repaint();
    }

    // Trigger
    public void togglePause() {
        if (ultActive) return;
        if (isPaused) audioPlayer.pauseAll();
        else audioPlayer.resumeAll();
        isPaused = !isPaused;
    }

    public void toggleDebug() {
        debug = !debug;
    }

    public void toggleFps() {
        showFPS = !showFPS;
    }

    public void toggleHitbox() {
        showHitboxes = !showHitboxes;
    }

    public void toggleGridLines() {
        showGridLines = !showGridLines;
    }

    // Getter
    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }

    public int getScore() {
        return score;
    }

    public boolean isUltActive() {
        return ultActive;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    // Debug Getter
    public boolean isDebug() {
        return debug;
    }

    public boolean isShowFPS() {
        return showFPS;
    }

    public boolean isShowHitboxes() {
        return showHitboxes;
    }

    public boolean isShowGridLines() {
        return showGridLines;
    }
}
