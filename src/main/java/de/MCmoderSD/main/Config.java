package de.MCmoderSD.main;

import com.fasterxml.jackson.databind.JsonNode;
import de.MCmoderSD.utilities.image.ImageReader;
import de.MCmoderSD.utilities.image.ImageStreamer;
import de.MCmoderSD.utilities.json.JsonReader;
import de.MCmoderSD.utilities.json.JsonStreamer;
import de.MCmoderSD.utilities.sound.AudioPlayer;


import javax.swing.*;
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
    private final BufferedImage head;
    private final BufferedImage upperBody;
    private final BufferedImage lowerBody;
    private final BufferedImage legTile;
    private final BufferedImage legTransition;
    private final BufferedImage feet;
    private final BufferedImage food;
    private final BufferedImage goldFood;

    // Sounds
    private final String foodSound;
    private final String ultSound;
    private final String dieSound;

    // Animations
    private final ImageIcon headAnimation;
    private final ImageIcon upperBodyAnimation;
    private final ImageIcon lowerBodyAnimation;
    private final ImageIcon legTileAnimation;
    private final ImageIcon legTransitionAnimation;
    private final ImageIcon feetAnimation;

    // Constructor
    public Config(String[] args) {

        // Read Config
        audioPlayer = new AudioPlayer();
        imageReader = new ImageReader();
        jsonReader = new JsonReader();
        imageStreamer = new ImageStreamer();
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
        dieSound = config.get("dieSound").asText();

        // Generate Assets
        dimension = new Dimension(fieldWidth * scale, fieldHeight * scale);
        icon = imageReader.read(config.get("icon").asText());
        backgroundTile = imageReader.scaleImage(config.get("backgroundTile").asText(), scale);
        head = imageReader.scaleImage(config.get("head").asText(), scale);
        upperBody = imageReader.scaleImage(config.get("upperBody").asText(), scale);
        lowerBody = imageReader.scaleImage(config.get("lowerBody").asText(), scale);
        legTile = imageReader.scaleImage(config.get("legTile").asText(), scale);
        legTransition = imageReader.scaleImage(config.get("legTransition").asText(), scale);
        feet = imageReader.scaleImage(config.get("feet").asText(), scale);
        food = imageReader.scaleImage(config.get("food").asText(), scale);
        goldFood = imageReader.scaleImage(config.get("goldFood").asText(), scale);
        audioPlayer.loadAudio(foodSound);
        audioPlayer.loadAudio(ultSound);
        audioPlayer.loadAudio(dieSound);

        // Animations
        headAnimation = imageReader.readGif(config.get("headAnimation").asText(), scale);
        upperBodyAnimation = imageReader.readGif(config.get("upperBodyAnimation").asText(), scale);
        lowerBodyAnimation = imageReader.readGif(config.get("lowerBodyAnimation").asText(), scale);
        legTileAnimation = imageReader.readGif(config.get("legTileAnimation").asText(), scale);
        legTransitionAnimation = imageReader.readGif(config.get("legTransitionAnimation").asText(), scale);
        feetAnimation = imageReader.readGif(config.get("feetAnimation").asText(), scale);
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
        dieSound = config.get("dieSound").asText();

        // Generate Assets
        dimension = new Dimension(fieldWidth * scale, fieldHeight * scale);
        icon = imageStreamer.read(config.get("icon").asText());
        backgroundTile = imageStreamer.scaleImage(config.get("backgroundTile").asText(), scale);
        head = imageStreamer.scaleImage(config.get("head").asText(), scale);
        upperBody = imageStreamer.scaleImage(config.get("upperBody").asText(), scale);
        lowerBody = imageStreamer.scaleImage(config.get("lowerBody").asText(), scale);
        legTile = imageStreamer.scaleImage(config.get("legTile").asText(), scale);
        legTransition = imageStreamer.scaleImage(config.get("legTransition").asText(), scale);
        feet = imageStreamer.scaleImage(config.get("feet").asText(), scale);
        food = imageStreamer.scaleImage(config.get("food").asText(), scale);
        goldFood = imageStreamer.scaleImage(config.get("goldFood").asText(), scale);
        audioPlayer.loadAudio(foodSound);
        audioPlayer.loadAudio(ultSound);
        audioPlayer.loadAudio(dieSound);

        // Animations
        headAnimation = imageStreamer.readGif(config.get("headAnimation").asText(), scale);
        upperBodyAnimation = imageStreamer.readGif(config.get("upperBodyAnimation").asText(), scale);
        lowerBodyAnimation = imageStreamer.readGif(config.get("lowerBodyAnimation").asText(), scale);
        legTileAnimation = imageStreamer.readGif(config.get("legTileAnimation").asText(), scale);
        legTransitionAnimation = imageStreamer.readGif(config.get("legTransitionAnimation").asText(), scale);
        feetAnimation = imageStreamer.readGif(config.get("feetAnimation").asText(), scale);
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

    // Sound Getters

    public String getFoodSound() {
        return foodSound;
    }

    public String getUltSound() {
        return ultSound;
    }

    public String getDieSound() {
        return dieSound;
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

    public BufferedImage getHead() {
        return head;
    }

    public BufferedImage getUpperBody() {
        return upperBody;
    }

    public BufferedImage getLowerBody() {
        return lowerBody;
    }

    public BufferedImage getLegTile() {
        return legTile;
    }

    public BufferedImage getLegTransition() {
        return legTransition;
    }

    public BufferedImage getFeet() {
        return feet;
    }

    public BufferedImage getFood() {
        return food;
    }

    public BufferedImage getGoldFood() {
        return goldFood;
    }

    // Animation Getters
    public ImageIcon getHeadAnimation() {
        return headAnimation;
    }

    public ImageIcon getUpperBodyAnimation() {
        return upperBodyAnimation;
    }

    public ImageIcon getLowerBodyAnimation() {
        return lowerBodyAnimation;
    }

    public ImageIcon getLegTileAnimation() {
        return legTileAnimation;
    }

    public ImageIcon getLegTransitionAnimation() {
        return legTransitionAnimation;
    }

    public ImageIcon getFeetAnimation() {
        return feetAnimation;
    }
}
