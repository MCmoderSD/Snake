package de.MCmoderSD.main;

import de.MCmoderSD.utilities.image.ImageReader;
import de.MCmoderSD.utilities.image.ImageStreamer;
import de.MCmoderSD.utilities.json.JsonNode;
import de.MCmoderSD.utilities.json.JsonUtility;
import de.MCmoderSD.utilities.sound.AudioPlayer;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

@SuppressWarnings("unused")
public class Config {

    // Associations
    private final AudioPlayer audioPlayer;

    // Config Variables
    private final int scale;
    private final int fieldWidth;
    private final int fieldHeight;
    private final int fps;
    private final int tps;
    private final int opUltGrowInterval;
    private final double specialFoodChance;
    private final double specialFoodDuration;
    private final double ultSpeedModifier;
    private final double opUltSpeedModifier;
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
    private final ImageIcon opFoodAnimation;

    // Language
    private final String language;
    private final String title;
    private final String restart;
    private final String gameOver;
    private final String score;

    // Color
    private final Color gridLayoutColor;
    private final Color snakeHitboxColor;
    private final Color foodHitboxColor;
    private final Color fpsColor;
    private final Color scoreColor;
    private final Color textColor;
    private final Color backgroundColor;
    private final Color snakeColor;
    private final Color foodColor;
    private final Color goldFoodColor;
    private final Color opFoodColor;

    // Constructor
    public Config(String[] args) {

        // Init Utilities
        audioPlayer = new AudioPlayer();
        ImageReader imageReader = new ImageReader();
        JsonUtility jsonUtility = new JsonUtility();

        JsonNode config;

        // Load Config
        if (args.length < 2) config = jsonUtility.load("/config/default.json");
        else {
            imageReader = new ImageReader(true);
            jsonUtility = new JsonUtility(true);
            config = jsonUtility.load(args[1]);
        }

        if (config == null) throw new IllegalArgumentException("The config file could not be loaded");

        scale = config.get("scale").asInt();
        fieldWidth = config.get("fieldWidth").asInt();
        fieldHeight = config.get("fieldHeight").asInt();
        fps = config.get("fps").asInt();
        tps = config.get("tps").asInt();
        opUltGrowInterval = config.get("opUltGrowInterval").asInt();
        specialFoodChance = config.get("specialFoodChance").asDouble();
        specialFoodDuration = config.get("specialFoodDuration").asDouble();
        ultSpeedModifier = config.get("ultSpeed").asDouble();
        opUltSpeedModifier = config.get("opUltSpeed").asDouble();
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

        // Animations
        headAnimation = imageReader.readGif(config.get("headAnimation").asText(), scale);
        upperBodyAnimation = imageReader.readGif(config.get("upperBodyAnimation").asText(), scale);
        lowerBodyAnimation = imageReader.readGif(config.get("lowerBodyAnimation").asText(), scale);
        legTileAnimation = imageReader.readGif(config.get("legTileAnimation").asText(), scale);
        legTransitionAnimation = imageReader.readGif(config.get("legTransitionAnimation").asText(), scale);
        feetAnimation = imageReader.readGif(config.get("feetAnimation").asText(), scale);
        opFoodAnimation = imageReader.readGif(config.get("opFoodAnimation").asText(), scale);

        // Language set
        language = args.length > 0 ? args[0] : "en";
        JsonNode languageConfig = language.length() == 2 ? jsonUtility.load("/language/" + language + ".json") : jsonUtility.load(args[0], true);

        // Language
        title = languageConfig.get("title").asText();
        restart = languageConfig.get("restart").asText();
        gameOver = languageConfig.get("gameOver").asText();
        score = languageConfig.get("score").asText();

        // Colors
        gridLayoutColor = config.get("gridLayoutColor").asColor();
        snakeHitboxColor = config.get("snakeHitboxColor").asColor();
        foodHitboxColor = config.get("foodHitboxColor").asColor();
        fpsColor = config.get("fpsColor").asColor();
        scoreColor = config.get("scoreColor").asColor();
        textColor = config.get("textColor").asColor();
        backgroundColor = config.get("backgroundColor").asColor();
        snakeColor = config.get("snakeColor").asColor();
        foodColor = config.get("foodColor").asColor();
        goldFoodColor = config.get("goldFoodColor").asColor();
        opFoodColor = config.get("opFoodColor").asColor();
    }

    // Constructor asset streaming
    public Config(String[] args, String url) {

        // Init Utilities
        audioPlayer = new AudioPlayer();
        ImageStreamer imageStreamer = new ImageStreamer(url);
        JsonUtility jsonUtility = new JsonUtility(url);

        JsonNode config;

        // Load Config
        if (args.length < 2) config = jsonUtility.load("/config/default.json");
        else {
            imageStreamer = new ImageStreamer(true);
            jsonUtility = new JsonUtility(true);
            config = jsonUtility.load(args[1]);
        }

        if (config == null) throw new IllegalArgumentException("The config file could not be loaded");

        scale = config.get("scale").asInt();
        fieldWidth = config.get("fieldWidth").asInt();
        fieldHeight = config.get("fieldHeight").asInt();
        fps = config.get("fps").asInt();
        tps = config.get("tps").asInt();
        opUltGrowInterval = config.get("opUltGrowInterval").asInt();
        specialFoodChance = config.get("specialFoodChance").asDouble();
        specialFoodDuration = config.get("specialFoodDuration").asDouble();
        ultSpeedModifier = config.get("ultSpeed").asDouble();
        opUltSpeedModifier = config.get("opUltSpeed").asDouble();
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

        // Animations
        headAnimation = imageStreamer.readGif(config.get("headAnimation").asText(), scale);
        upperBodyAnimation = imageStreamer.readGif(config.get("upperBodyAnimation").asText(), scale);
        lowerBodyAnimation = imageStreamer.readGif(config.get("lowerBodyAnimation").asText(), scale);
        legTileAnimation = imageStreamer.readGif(config.get("legTileAnimation").asText(), scale);
        legTransitionAnimation = imageStreamer.readGif(config.get("legTransitionAnimation").asText(), scale);
        feetAnimation = imageStreamer.readGif(config.get("feetAnimation").asText(), scale);
        opFoodAnimation = imageStreamer.readGif(config.get("opFoodAnimation").asText(), scale);

        // Language set
        language = args.length > 0 ? args[0] : "en";
        JsonNode languageConfig = language.length() == 2 ? jsonUtility.load("/language/" + language + ".json") : jsonUtility.load(args[0], true);

        // Language
        title = languageConfig.get("title").asText();
        restart = languageConfig.get("restart").asText();
        gameOver = languageConfig.get("gameOver").asText();
        score = languageConfig.get("score").asText();

        // Colors
        gridLayoutColor = config.get("gridLayoutColor").asColor();
        snakeHitboxColor = config.get("snakeHitboxColor").asColor();
        foodHitboxColor = config.get("foodHitboxColor").asColor();
        fpsColor = config.get("fpsColor").asColor();
        scoreColor = config.get("scoreColor").asColor();
        textColor = config.get("textColor").asColor();
        backgroundColor = config.get("backgroundColor").asColor();
        snakeColor = config.get("snakeColor").asColor();
        foodColor = config.get("foodColor").asColor();
        goldFoodColor = config.get("goldFoodColor").asColor();
        opFoodColor = config.get("opFoodColor").asColor();
    }

    // Getters

    // Associations
    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    // Config Getters
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

    public int getOpUltGrowInterval() {
        return opUltGrowInterval;
    }

    public double getSpecialFoodChance() {
        return specialFoodChance;
    }

    public double getSpecialFoodDuration() {
        return specialFoodDuration;
    }

    public double getUltSpeedModifier() {
        return ultSpeedModifier;
    }

    public double getOpUltSpeedModifier() {
        return opUltSpeedModifier;
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

    public ImageIcon getOpFoodAnimation() {
        return opFoodAnimation;
    }

    // Language Getters
    public String getLanguage() {
        return language;
    }

    public String getTitle() {
        return title;
    }

    public String getRestart() {
        return restart;
    }

    public String getGameOver() {
        return gameOver;
    }

    public String getScore() {
        return score;
    }

    // Color Getters
    public Color getGridLayoutColor() {
        return gridLayoutColor;
    }

    public Color getSnakeHitboxColor() {
        return snakeHitboxColor;
    }

    public Color getFoodHitboxColor() {
        return foodHitboxColor;
    }

    public Color getFpsColor() {
        return fpsColor;
    }

    public Color getScoreColor() {
        return scoreColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getSnakeColor() {
        return snakeColor;
    }

    public Color getFoodColor() {
        return foodColor;
    }

    public Color getGoldFoodColor() {
        return goldFoodColor;
    }

    public Color getOpFoodColor() {
        return opFoodColor;
    }
}