package de.MCmoderSD.main;

import com.fasterxml.jackson.databind.JsonNode;
import de.MCmoderSD.utilities.ImageReader;
import de.MCmoderSD.utilities.ImageStreamer;
import de.MCmoderSD.utilities.JsonReader;
import de.MCmoderSD.utilities.JsonStreamer;


import java.awt.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("unused")
public class Config {

    // Config Variables
    private final String title;
    private final String iconPath;
    private final String backgroundTilePath;
    private final String snakeHeadPath;
    private final String snakeBodyPath;
    private final String foodPath;
    private final String goldFoodPath;
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
        iconPath = config.get("icon").asText();
        scale = config.get("scale").asInt();
        fieldWidth = config.get("fieldWidth").asInt();
        fieldHeight = config.get("fieldHeight").asInt();
        fps = config.get("fps").asInt();
        tps = config.get("tps").asInt();
        specialFoodChance = config.get("specialFoodChance").asDouble();
        resizable = config.get("resizable").asBoolean();
        solidWalls = config.get("solidWalls").asBoolean();
        backgroundTilePath = config.get("backgroundTile").asText();
        snakeHeadPath = config.get("snakeHead").asText();
        snakeBodyPath = config.get("snakeBodyTile").asText();
        foodPath = config.get("food").asText();
        goldFoodPath = config.get("goldFood").asText();

        // Generate Assets
        dimension = new Dimension(fieldWidth * scale, fieldHeight * scale);
        icon = imageReader.read(iconPath);
        backgroundTile = imageReader.scaleImage(backgroundTilePath, scale);
        snakeHead = imageReader.scaleImage(snakeHeadPath, scale);
        snakeBody = imageReader.scaleImage(snakeBodyPath, scale);
        food = imageReader.scaleImage(foodPath, scale);
        goldFood = imageReader.scaleImage(goldFoodPath, scale);
    }

    // Constructor asset streaming
    public Config(String[] args, String url) {

        // Read Config
        ImageStreamer imageStreamer = new ImageStreamer();
        JsonStreamer jsonStreamer= new JsonStreamer();
        JsonNode config = jsonStreamer.read("/config/default.json");

        if (config == null) throw new IllegalArgumentException("The config file could not be loaded");

        title = config.get("title").asText();
        iconPath = config.get("icon").asText();
        scale = config.get("scale").asInt();
        fieldWidth = config.get("fieldWidth").asInt();
        fieldHeight = config.get("fieldHeight").asInt();
        fps = config.get("fps").asInt();
        tps = config.get("tps").asInt();
        specialFoodChance = config.get("specialFoodChance").asDouble();
        resizable = config.get("resizable").asBoolean();
        solidWalls = config.get("solidWalls").asBoolean();
        backgroundTilePath = config.get("backgroundTile").asText();
        snakeHeadPath = config.get("snakeHead").asText();
        snakeBodyPath = config.get("snakeBodyTile").asText();
        foodPath = config.get("food").asText();
        goldFoodPath = config.get("goldFood").asText();

        // Generate Assets
        dimension = new Dimension(fieldWidth * scale, fieldHeight * scale);
        icon = imageStreamer.read(iconPath);
        backgroundTile = imageStreamer.scaleImage(backgroundTilePath, scale);
        snakeHead = imageStreamer.scaleImage(snakeHeadPath, scale);
        snakeBody = imageStreamer.scaleImage(snakeBodyPath, scale);
        food = imageStreamer.scaleImage(foodPath, scale);
        goldFood = imageStreamer.scaleImage(goldFoodPath, scale);
    }

    // Getters

    // Config Getters
    public String getTitle() {
        return title;
    }

    public String getIconPath() {
        return iconPath;
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

    public String getBackgroundTilePath() {
        return backgroundTilePath;
    }

    public String getSnakeHeadPath() {
        return snakeHeadPath;
    }

    public String getSnakeBodyPath() {
        return snakeBodyPath;
    }

    public String getFoodPath() {
        return foodPath;
    }

    public String getGoldFoodPath() {
        return goldFoodPath;
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
