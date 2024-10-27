package de.MCmoderSD.core;

import de.MCmoderSD.JavaAudioLibrary.AudioFile;
import de.MCmoderSD.executor.NanoLoop;

import de.MCmoderSD.UI.UI;
import de.MCmoderSD.objects.Food;
import de.MCmoderSD.objects.Snake;

import java.util.ArrayList;

import static de.MCmoderSD.main.Config.*;
import static de.MCmoderSD.utilities.Calculate.*;

public class Game {

    // Associations
    private final UI ui;

    // Game Threads
    private final NanoLoop tickExecutor;
    private final NanoLoop renderExecutor;
    private final NanoLoop debugExecutor;

    // Audio
    private final ArrayList<AudioFile> activeAudio;

    // Game State Flags
    private boolean paused;
    private boolean gameOver;
    private boolean gameStarted;
    private boolean ultActive;

    // Debug Flags
    private boolean debug;
    private boolean showFPS;
    private boolean showHitboxes;
    private boolean showGridLines;

    // Debug Variables
    private int ticks = 0;
    private int frames = 0;

    // Game Variables
    private Thread ult;
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
        activeAudio = new ArrayList<>();

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
        snake = new Snake(this, FIELD_WIDTH / 2 - 2, FIELD_HEIGHT / 2, HEAD, HEAD_ANIMATION);
        food = new Food(snake.getSnakePieces());

        // Start Game Threads
        debugExecutor.start();
        tickExecutor.start();
        renderExecutor.start();
    }

    // Debug Loop
    private void debug() {

        // Show Debug Information
        if (showFPS) ui.setFps(frames);

        // Print Debug Information
        System.out.printf("%s\t FPS: %d | TPS: %d%n", INFO, frames, ticks);

        // Reset Debug Counter
        ticks = 0;
        frames = 0;
    }

    // Game Loop
    private void tick() {

        // Check if game is started
        if (gameStarted && !paused && !gameOver) {

            // Check for win
            if (FIELD_WIDTH * FIELD_HEIGHT == snake.getSnakePieces().size()) {
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
                AudioFile sound = food.getSound(); // Get Sound
                activeAudio.add(sound); // Add Sound
                sound.play(); // Play Sound

                // Activate Ultimate
                if (food.isSpecial()) activateUlt(food.isOp());

                // Spawn new Food
                food = new Food(snake.getSnakePieces()); // Spawn new Food
            }

            // Check Input and Move Snake
            snake.updateDirection(ui.getInputs().getDirection());
            snake.moveSnake();
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

                // Set Ultimate Active
                ultActive = true;

                // Get Sound
                AudioFile audioPlayer = ULT_SOUND.copy();

                // Add Sound
                activeAudio.add(audioPlayer);
                audioPlayer.play();

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
        activeAudio.forEach(AudioFile::close);

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
        snake = new Snake(this, FIELD_WIDTH / 2 - 2, FIELD_HEIGHT / 2, HEAD, HEAD_ANIMATION);
        food = new Food(snake.getSnakePieces());

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

        // Get Sound
        AudioFile sound = DIE_SOUND.copy();
        activeAudio.add(sound);

        // Play Sound
        sound.play();
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

        // Remove all Audio that are not playing
        if (paused) activeAudio.removeIf(audioFile -> !audioFile.isPlaying());

        // Pause or resume all Audio Files
        if (paused) activeAudio.forEach(AudioFile::pause);
        else activeAudio.forEach(AudioFile::resume);
    }

    public void toggleDebug() {
        System.out.printf("%s\tDebug Toggled%n", DEBUG);
        debug = !debug;
    }

    public void toggleFps() {
        System.out.printf("%s\t FPS Toggled%n", DEBUG);
        showFPS = !showFPS;
    }

    public void toggleHitbox() {
        System.out.printf("%s\t Hitboxes Toggled%n", DEBUG);
        showHitboxes = !showHitboxes;
    }

    public void toggleGridLines() {
        System.out.printf("%s\t Grid Lines Toggled%n", DEBUG);
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

    public boolean isShowHitboxes() {
        return showHitboxes;
    }

    public boolean isShowGridLines() {
        return showGridLines;
    }
}