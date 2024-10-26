package de.MCmoderSD.main;

import com.fasterxml.jackson.databind.JsonNode;
import de.MCmoderSD.JavaAudioLibrary.AudioFile;
import de.MCmoderSD.JavaAudioLibrary.AudioLoader;
import de.MCmoderSD.imageloader.ImageLoader;
import de.MCmoderSD.json.JsonUtility;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

public class Config {

    // Config Variables
    public static int SCALE;
    public static int FIELD_WIDTH;
    public static int FIELD_HEIGHT;
    public static int FPS;
    public static int TPS;
    public static long OP_ULT_GROW_INTERVAL;
    public static long SPECIAL_FOOD_DURATION;
    public static double SPECIAL_FOOD_CHANCE;
    public static double ULT_SPEED_MODIFIER;
    public static double OP_ULT_SPEED_MODIFIER;
    public static boolean SOLID_WALLS;

    // Assets
    public static Dimension DIMENSION;
    public static BufferedImage ICON;
    public static BufferedImage BACKGROUND_COVER;
    public static BufferedImage BACKGROUND_TILE;
    public static BufferedImage HEAD;
    public static BufferedImage UPPER_BODY;
    public static BufferedImage LOWER_BODY;
    public static BufferedImage LEG_TILE;
    public static BufferedImage LEG_TRANSITION;
    public static BufferedImage FEET;
    public static BufferedImage FOOD;
    public static BufferedImage GOLD_FOOD;

    // Sounds
    public static AudioFile FOOD_SOUND;
    public static AudioFile ULT_SOUND;
    public static AudioFile DIE_SOUND;

    // Animations
    public static ImageIcon HEAD_ANIMATION;
    public static ImageIcon UPPER_BODY_ANIMATION;
    public static ImageIcon LOWER_BODY_ANIMATION;
    public static ImageIcon LEG_TILE_ANIMATION;
    public static ImageIcon LEG_TRANSITION_ANIMATION;
    public static ImageIcon FEET_ANIMATION;
    public static ImageIcon OP_FOOD_ANIMATION;

    // Language
    public static String LANGUAGE;
    public static String TITLE;
    public static String RESTART;
    public static String RESTART_TOOL_TIP;
    public static String GAME_OVER;
    public static String SCORE_PREFIX;
    public static String FPS_PREFIX;

    // Colors
    public static Color GRID_LAYOUT_COLOR;
    public static Color SNAKE_HITBOX_COLOR;
    public static Color FOOD_HITBOX_COLOR;
    public static Color FPS_COLOR;
    public static Color SCORE_COLOR;
    public static Color TEXT_COLOR;
    public static Color BACKGROUND_COLOR;
    public static Color SNAKE_COLOR;
    public static Color FOOD_COLOR;
    public static Color GOLD_FOOD_COLOR;
    public static Color OP_FOOD_COLOR;

    // Constructor
    public static void init(String[] args) {

        // Init Utilities
        AudioLoader audioLoader = new AudioLoader();
        ImageLoader imageLoader = new ImageLoader();
        JsonUtility jsonUtility = new JsonUtility();
        boolean isAbsolute = args.length > 1;

        JsonNode config;

        // Load Config
        String path = "/config/default.json";
        config = jsonUtility.load(path, isAbsolute);
        if (config == null)
            config = jsonUtility.load("https://raw.githubusercontent.com/MCmoderSD/Snake/main/src/main/resources" + path);

        // Null check
        if (config == null) throw new IllegalArgumentException("The config file could not be loaded");


        // Load Settings
        JsonNode settings = config.get("settings");
        SCALE = settings.get("scale").asInt();
        FIELD_WIDTH = settings.get("fieldWidth").asInt();
        FIELD_HEIGHT = settings.get("fieldHeight").asInt();
        FPS = settings.get("fps").asInt();
        TPS = settings.get("tps").asInt();
        OP_ULT_GROW_INTERVAL = settings.get("opUltGrowInterval").asLong();
        SPECIAL_FOOD_DURATION = settings.get("specialFoodDuration").asLong();
        SPECIAL_FOOD_CHANCE = settings.get("specialFoodChance").asDouble();
        ULT_SPEED_MODIFIER = settings.get("ultSpeed").asDouble();
        OP_ULT_SPEED_MODIFIER = settings.get("opUltSpeed").asDouble();
        SOLID_WALLS = settings.get("solidWalls").asBoolean();
        DIMENSION = new Dimension(FIELD_WIDTH * SCALE, FIELD_HEIGHT * SCALE);

        // Load Assets
        JsonNode assets = config.get("assets");
        try {

            // Load Images
            JsonNode images = assets.get("images");
            ICON = imageLoader.load(images.get("icon").asText());
            BACKGROUND_TILE = scaleImage(imageLoader.load(images.get("backgroundTile").asText()));
            BACKGROUND_COVER = scaleImage(imageLoader.load(images.get("backgroundCover").asText()));
            HEAD = scaleImage(imageLoader.load(images.get("head").asText()));
            UPPER_BODY = scaleImage(imageLoader.load(images.get("upperBody").asText()));
            LOWER_BODY = scaleImage(imageLoader.load(images.get("lowerBody").asText()));
            LEG_TILE = scaleImage(imageLoader.load(images.get("legTile").asText()));
            LEG_TRANSITION = scaleImage(imageLoader.load(images.get("legTransition").asText()));
            FEET = scaleImage(imageLoader.load(images.get("feet").asText()));
            FOOD = scaleImage(imageLoader.load(images.get("food").asText()));
            GOLD_FOOD = scaleImage(imageLoader.load(images.get("goldFood").asText()));

            // Animations
            JsonNode animations = assets.get("animations");
            HEAD_ANIMATION = new ImageIcon(scaleImage(imageLoader.load(animations.get("head").asText())));
            UPPER_BODY_ANIMATION = new ImageIcon(scaleImage(imageLoader.load(animations.get("upperBody").asText())));
            LOWER_BODY_ANIMATION = new ImageIcon(scaleImage(imageLoader.load(animations.get("lowerBody").asText())));
            LEG_TILE_ANIMATION = new ImageIcon(scaleImage(imageLoader.load(animations.get("legTile").asText())));
            LEG_TRANSITION_ANIMATION = new ImageIcon(scaleImage(imageLoader.load(animations.get("legTransition").asText())));
            FEET_ANIMATION = new ImageIcon(scaleImage(imageLoader.load(animations.get("feet").asText())));
            OP_FOOD_ANIMATION = new ImageIcon(scaleImage(imageLoader.load(animations.get("opFood").asText())));

            // Sounds
            JsonNode sounds = assets.get("sounds");
            FOOD_SOUND = audioLoader.load(sounds.get("food").asText());
            ULT_SOUND = audioLoader.load(sounds.get("ult").asText());
            DIE_SOUND = audioLoader.load(sounds.get("die").asText());
        } catch (IOException | URISyntaxException e) {
            System.err.println("An error occurred while loading Images: " + e.getMessage());
        }

        // Language set
        LANGUAGE = args.length > 0 ? args[0] : "en";
        JsonNode languageConfig = LANGUAGE.length() == 2 ? jsonUtility.load("/language/" + LANGUAGE + ".json", false) : jsonUtility.load(args[0], true);

        // Language
        TITLE = languageConfig.get("title").asText();
        RESTART = languageConfig.get("restart").asText();
        RESTART_TOOL_TIP = languageConfig.get("restartToolTip").asText();
        GAME_OVER = languageConfig.get("gameOver").asText();
        SCORE_PREFIX = languageConfig.get("scorePrefix").asText();
        FPS_PREFIX = languageConfig.get("fpsPrefix").asText();

        // Colors
        JsonNode colors = config.get("colors");
        GRID_LAYOUT_COLOR = getColor(colors.get("gridLayout").asText());
        SNAKE_HITBOX_COLOR = getColor(colors.get("snakeHitbox").asText());
        FOOD_HITBOX_COLOR = getColor(colors.get("foodHitbox").asText());
        FPS_COLOR = getColor(colors.get("fps").asText());
        SCORE_COLOR = getColor(colors.get("score").asText());
        TEXT_COLOR = getColor(colors.get("text").asText());
        BACKGROUND_COLOR = getColor(colors.get("background").asText());
        SNAKE_COLOR = getColor(colors.get("snake").asText());
        FOOD_COLOR = getColor(colors.get("food").asText());
        GOLD_FOOD_COLOR = getColor(colors.get("goldFood").asText());
        OP_FOOD_COLOR = getColor(colors.get("opFood").asText());
    }

    private static BufferedImage scaleImage(BufferedImage image) {
        BufferedImage scaledImage = new BufferedImage(SCALE, SCALE, BufferedImage.TYPE_INT_ARGB);
        scaledImage.getGraphics().drawImage(image.getScaledInstance(SCALE, SCALE, Image.SCALE_DEFAULT), 0, 0, SCALE, SCALE, null);
        return scaledImage;
    }

    private static Color getColor(String hex) {
        return new Color(Integer.parseInt(hex.substring(1), 16));
    }
}