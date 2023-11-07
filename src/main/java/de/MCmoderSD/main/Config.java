package de.MCmoderSD.main;

import com.fasterxml.jackson.databind.JsonNode;
import de.MCmoderSD.utilities.ImageReader;
import de.MCmoderSD.utilities.ImageStreamer;
import de.MCmoderSD.utilities.JsonReader;
import de.MCmoderSD.utilities.JsonStreamer;


import java.awt.Dimension;
import java.awt.image.BufferedImage;

@SuppressWarnings("unused")
public class Config {

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

    // Constructor
    public Config(String[] args) {

        // Read Config
        ImageReader imageReader = new ImageReader();
        JsonReader jsonReader = new JsonReader();
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

        // Generate Assets
        dimension = new Dimension(fieldWidth * scale, fieldHeight * scale);
        icon = imageReader.read(config.get("icon").asText());
        backgroundTile = imageReader.scaleImage(config.get("backgroundTile").asText(), scale);
        snakeHead = imageReader.scaleImage(config.get("snakeHead").asText(), scale);
        snakeBody = imageReader.scaleImage(config.get("snakeBodyTile").asText(), scale);
        food = imageReader.scaleImage(config.get("food").asText(), scale);
        goldFood = imageReader.scaleImage(config.get("goldFood").asText(), scale);
    }

    // Constructor asset streaming
    public Config(String[] args, String url) {

        // Read Config
        ImageStreamer imageStreamer = new ImageStreamer();
        JsonStreamer jsonStreamer= new JsonStreamer();
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

        // Generate Assets
        dimension = new Dimension(fieldWidth * scale, fieldHeight * scale);
        icon = imageStreamer.read(config.get("icon").asText());
        backgroundTile = imageStreamer.scaleImage(config.get("backgroundTile").asText(), scale);
        snakeHead = imageStreamer.scaleImage(config.get("snakeHead").asText(), scale);
        snakeBody = imageStreamer.scaleImage(config.get("snakeBodyTile").asText(), scale);
        food = imageStreamer.scaleImage(config.get("food").asText(), scale);
        goldFood = imageStreamer.scaleImage(config.get("goldFood").asText(), scale);
    }

    // Getters

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
