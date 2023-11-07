package de.MCmoderSD.main;

import com.fasterxml.jackson.databind.JsonNode;
import de.MCmoderSD.utilities.image.ImageReader;
import de.MCmoderSD.utilities.image.ImageStreamer;
import de.MCmoderSD.utilities.json.JsonReader;
import de.MCmoderSD.utilities.json.JsonStreamer;
import de.MCmoderSD.utilities.sound.AudioPlayer;


import java.awt.Dimension;
import java.awt.image.BufferedImage;

@SuppressWarnings("unused")
public class Config {

    // Associations
    private final AudioPlayer audioPlayer;
    private final JsonReader jsonReader;
    private final JsonStreamer jsonStreamer;
    private final ImageReader imageReader;
    private final ImageStreamer imageStreamer;

    // Config Variables
    private final String title;
    private final int scale;
    private final int fieldWidth;
    private final int fieldHeight;
    private final int fps;
    private final int tps;
    private final double specialFoodChance;
    private final boolean resizable;
    private final boolean solidWalls;

    // Assets
    private final Dimension dimension;
    private final BufferedImage icon;
    private final BufferedImage backgroundTile;
    private final BufferedImage snakeHead;
    private final BufferedImage snakeBody;
    private final BufferedImage food;
    private final BufferedImage goldFood;

    // Sounds
    private final String foodSound;
    private final String ultSound;

    // Constructor
    public Config(String[] args) {

        // Read Config
        audioPlayer = new AudioPlayer();
        imageReader = new ImageReader();
        jsonReader = new JsonReader();
        imageStreamer = null;
        jsonStreamer = null;
        JsonNode config = jsonReader.read("/config/default.json");

        if (config == null) throw new IllegalArgumentException("The config file could not be loaded");

        title = config.get("title").asText();
        scale = config.get("scale").asInt();
        fieldWidth = config.get("fieldWidth").asInt();
        fieldHeight = config.get("fieldHeight").asInt();
        fps = config.get("fps").asInt();
        tps = config.get("tps").asInt();
        specialFoodChance = config.get("specialFoodChance").asDouble();
        resizable = config.get("resizable").asBoolean();
        solidWalls = config.get("solidWalls").asBoolean();
        foodSound = config.get("foodSound").asText();
        ultSound = config.get("ultSound").asText();

        // Generate Assets
        dimension = new Dimension(fieldWidth * scale, fieldHeight * scale);
        icon = imageReader.read(config.get("icon").asText());
        backgroundTile = imageReader.scaleImage(config.get("backgroundTile").asText(), scale);
        snakeHead = imageReader.scaleImage(config.get("snakeHead").asText(), scale);
        snakeBody = imageReader.scaleImage(config.get("snakeBodyTile").asText(), scale);
        food = imageReader.scaleImage(config.get("food").asText(), scale);
        goldFood = imageReader.scaleImage(config.get("goldFood").asText(), scale);
        audioPlayer.loadAudio(foodSound);
        audioPlayer.loadAudio(ultSound);
    }

    // Constructor asset streaming
    public Config(String[] args, String url) {

        // Read Config
        audioPlayer = new AudioPlayer();
        imageStreamer = new ImageStreamer();
        jsonStreamer = new JsonStreamer();
        imageReader = null;
        jsonReader = null;
        JsonNode config = jsonStreamer.read(url);

        if (config == null) throw new IllegalArgumentException("The config file could not be loaded");

        title = config.get("title").asText();
        scale = config.get("scale").asInt();
        fieldWidth = config.get("fieldWidth").asInt();
        fieldHeight = config.get("fieldHeight").asInt();
        fps = config.get("fps").asInt();
        tps = config.get("tps").asInt();
        specialFoodChance = config.get("specialFoodChance").asDouble();
        resizable = config.get("resizable").asBoolean();
        solidWalls = config.get("solidWalls").asBoolean();
        foodSound = config.get("foodSound").asText();
        ultSound = config.get("ultSound").asText();

        // Generate Assets
        dimension = new Dimension(fieldWidth * scale, fieldHeight * scale);
        icon = imageStreamer.read(config.get("icon").asText());
        backgroundTile = imageStreamer.scaleImage(config.get("backgroundTile").asText(), scale);
        snakeHead = imageStreamer.scaleImage(config.get("snakeHead").asText(), scale);
        snakeBody = imageStreamer.scaleImage(config.get("snakeBodyTile").asText(), scale);
        food = imageStreamer.scaleImage(config.get("food").asText(), scale);
        goldFood = imageStreamer.scaleImage(config.get("goldFood").asText(), scale);
        audioPlayer.loadAudio(foodSound);
        audioPlayer.loadAudio(ultSound);
    }

    // Getters

    // Associations
    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    public ImageReader getImageReader() {
        return imageReader;
    }

    public ImageStreamer getImageStreamer() {
        return imageStreamer;
    }

    public JsonReader getJsonReader() {
        return jsonReader;
    }

    public JsonStreamer getJsonStreamer() {
        return jsonStreamer;
    }

    // Config Getters
    public String getTitle() {
        return title;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    public int getScale() {
        return scale;
    }

    public int getFps() {
        return fps;
    }

    public int getTps() {
        return tps;
    }

    public double getSpecialFoodChance() {
        return specialFoodChance;
    }

    public boolean isResizable() {
        return resizable;
    }

    public boolean isSolidWalls() {
        return solidWalls;
    }

    public String getFoodSound() {
        return foodSound;
    }

    public String getUltSound() {
        return ultSound;
    }

    // Asset Getters
    public BufferedImage getIcon() {
        return icon;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public BufferedImage getBackgroundTile() {
        return backgroundTile;
    }

    public BufferedImage getSnakeHead() {
        return snakeHead;
    }

    public BufferedImage getSnakeBody() {
        return snakeBody;
    }

    public BufferedImage getFood() {
        return food;
    }

    public BufferedImage getGoldFood() {
        return goldFood;
    }
}
