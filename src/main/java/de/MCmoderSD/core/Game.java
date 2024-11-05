package de.MCmoderSD.core;

import de.MCmoderSD.JavaAudioLibrary.AudioPlayer;
import de.MCmoderSD.executor.NanoLoop;

import de.MCmoderSD.UI.UI;
import de.MCmoderSD.objects.Food;
import de.MCmoderSD.objects.Snake;

import static de.MCmoderSD.main.Config.*;
import static de.MCmoderSD.utilities.Util.*;

public class Game {

    // Associations
    private final UI ui;

    // Game Threads
    private final NanoLoop tickExecutor;
    private final NanoLoop renderExecutor;
    private final NanoLoop debugExecutor;

    // Audio
    private final AudioPlayer audioPlayer;

    // Game State Flags
    private boolean paused;
    private boolean gameOver;
    private boolean gameStarted;
    private boolean ultActive;

    // Debug Flags
    private boolean debug;
    private boolean showFPS;
    private boolean showTPS;
    private boolean showHitboxes;
    private boolean showGridLines;

    // Debug Variables
    private int ticks = 0;
    private int frames = 0;

    // Game Variables
    private Thread ult;
    private int ultSoundID;
    private int score;

    // Game Objects
    private Snake snake;
    private Food food;


    public Game(UI ui) {

        // Init Associations
        this.ui = ui;

        // Init Game Threads
        debugExecutor = new NanoLoop(this::debug, 1);
        tickExecutor = new NanoLoop(this::tick, TPS);
        renderExecutor = new NanoLoop(this::render, FPS);

        // Init Audio List
        audioPlayer = new AudioPlayer();

        // Init Game State Flags
        paused = false;
        gameOver = false;
        gameStarted = false;
        ultActive = false;

        // Init Debug Flags
        debug = false;
        showFPS = false;
        showHitboxes = false;
        showGridLines = false;

        // Init Game Variables
        score = 0;

        // Init Objects
        snake = new Snake(this, FIELD_WIDTH / 2 - 2, FIELD_HEIGHT / 2);
        food = new Food(snake.getSnakeParts());

        // Start Game Threads
        debugExecutor.start();
        tickExecutor.start();
        renderExecutor.start();
    }

    // Debug Loop
    private void debug() {

        // Show Debug Information
        if (showFPS) ui.setFps(frames);
        if (showTPS) ui.setTps(ticks);

        // Print Debug Information
        if (debug || showFPS || showTPS) System.out.printf("%s\t FPS: %d | TPS: %d%n", INFO, frames, ticks);

        // Reset Debug Counter
        ticks = 0;
        frames = 0;
    }

    // Game Loop
    private void tick() {

        // Check if game is started
        if (gameStarted && !paused && !gameOver) {

            // Check for win
            if (FIELD_WIDTH * FIELD_HEIGHT == snake.getSnakeParts().size()) {
                // ToDo Win
                System.out.printf("%s\t Win%n", GAME);
                gameOver();
            }

            // Check Collision with itself
            if (snake.checkCollision() && !ultActive) gameOver();

            // Check Collision with Food
            if (snake.checkFood(food)) {

                // Increase Score
                score++;
                ui.setScore(score);

                // Grow Snake
                snake.grow();

                // Sound
                audioPlayer.play(FOOD_SOUND);

                // Activate Ultimate
                if (food.isSpecial()) activateUlt(food.isOp());

                // Spawn new Food
                food = new Food(snake.getSnakeParts()); // Spawn new Food
            }

            // Check Input and Move Snake
            snake.setDirection(ui.getInputs().getDirection());
            snake.move();
        }

        // Debug
        ticks++;
    }

    // Render Loop
    private void render() {

        // Render UI
        if (gameStarted && !gameOver) ui.repaint();

        // Debug
        frames++;
    }

    // Activate Ultimate
    @SuppressWarnings("BusyWait")
    private void activateUlt(boolean isOp) {

        // Stop old Ultimate
        if (ult != null && ult.isAlive()) ult.interrupt();

        // Start new Ultimate
        ult = new Thread(() -> {
            try {


                // Get Sound
                if (ultActive && ultSoundID > -1) audioPlayer.remove(ultSoundID);

                // Set Ultimate Active
                ultActive = true;

                // Play Sound
                ultSoundID = audioPlayer.play(ULT_SOUND);

                // Set Speed Modifier
                tickExecutor.setModifier(isOp ? OP_ULT_SPEED_MODIFIER : ULT_SPEED_MODIFIER);

                if (isOp) {

                    // Grow Snake
                    for (var i = 0; i < SPECIAL_FOOD_DURATION; i++) {
                        snake.grow();
                        score++;
                        ui.setScore(score);
                        Thread.sleep(OP_ULT_GROW_INTERVAL);
                    }

                    // Reset Speed Modifier
                    tickExecutor.setModifier(1);

                } else {

                    // Wait
                    Thread.sleep((SPECIAL_FOOD_DURATION * 1000));

                    // Reset Speed Modifier
                    tickExecutor.setModifier(1);
                }

                // Set Ultimate Inactive
                ultActive = false;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        ult.start();
    }

    // Reset Game
    public void reset() {

        // Check if game is started
        if (!gameStarted || !gameOver) return;

        // Print Debug Information
        System.out.printf("%s\t Reset%n", GAME);

        // Stop all Audio
        audioPlayer.stop();

        // Reset Game State Flags
        paused = false;
        gameOver = false;
        gameStarted = false;
        ultActive = false;

        // Reset Speed Modifier
        tickExecutor.setModifier(1);

        // Reset Game Variables
        score = 0;

        // Reset Objects
        snake = new Snake(this, FIELD_WIDTH / 2 - 2, FIELD_HEIGHT / 2);
        food = new Food(snake.getSnakeParts());

        // Reset UI
        ui.setScore(score);
        ui.setGameOver(false);
        ui.repaint();
    }

    // Game Over
    public void gameOver() {

        // Check if game is started
        if (!gameStarted || gameOver) return;

        // ToDo Game Over

        // Print Debug Information
        System.out.printf("%s\t Game Over%n", GAME);

        // Set Game Over
        gameOver = true;
        ui.setGameOver(true);

        // Play Sound
        audioPlayer.play(DIE_SOUND);
    }

    // Trigger
    public void startGame() {
        if (gameStarted || gameOver) return;
        System.out.printf("%s\t Game Started%n", GAME);
        gameStarted = true;
    }

    public void togglePause() {

        // Check if game is started
        if (!gameStarted || ultActive || gameOver) return;

        // Set Flag
        paused = !paused;

        // Pause or resume Audio
        if (paused) audioPlayer.pause();
        else audioPlayer.resume();
    }

    public void toggleDebug() {
        System.out.printf("%s\t Debug Toggled %s%n", DEBUG, debug ? "OFF" : "ON");
        debug = !debug;
    }

    public void toggleFps() {
        System.out.printf("%s\t FPS Toggled %s%n", DEBUG, showFPS ? "OFF" : "ON");
        showFPS = !showFPS;
    }

    public void toggleTps() {
        System.out.printf("%s\t TPS Toggled %s%n", DEBUG , showTPS ? "OFF" : "ON");
        showTPS = !showTPS;
    }

    public void toggleHitbox() {
        System.out.printf("%s\t Hitboxes Toggled %s%n", DEBUG, showHitboxes ? "OFF" : "ON");
        showHitboxes = !showHitboxes;
    }

    public void toggleGridLines() {
        System.out.printf("%s\t Grid Lines Toggled %s%n", DEBUG, showGridLines ? "OFF" : "ON");
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

    // Game State Getter
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

    public boolean isShowTPS() {
        return showTPS;
    }

    public boolean isShowHitboxes() {
        return showHitboxes;
    }

    public boolean isShowGridLines() {
        return showGridLines;
    }
}