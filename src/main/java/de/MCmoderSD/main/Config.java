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

    // Utilities
    private final AudioPlayer audioPlayer;

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
    public static boolean RESIZABLE;
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
    public static String FOOD_SOUND;
    public static String ULT_SOUND;
    public static String DIE_SOUND;

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

        SCALE = config.get("scale").asInt();
        FIELD_WIDTH = config.get("fieldWidth").asInt();
        FIELD_HEIGHT = config.get("fieldHeight").asInt();
        FPS = config.get("fps").asInt();
        TPS = config.get("tps").asInt();
        OP_ULT_GROW_INTERVAL = config.get("opUltGrowInterval").asLong();
        SPECIAL_FOOD_DURATION = config.get("specialFoodDuration").asLong();
        SPECIAL_FOOD_CHANCE = config.get("specialFoodChance").asDouble();
        ULT_SPEED_MODIFIER = config.get("ultSpeed").asDouble();
        OP_ULT_SPEED_MODIFIER = config.get("opUltSpeed").asDouble();
        RESIZABLE = config.get("resizable").asBoolean();
        SOLID_WALLS = config.get("solidWalls").asBoolean();
        FOOD_SOUND = config.get("foodSound").asText();
        ULT_SOUND = config.get("ultSound").asText();
        DIE_SOUND = config.get("dieSound").asText();

        // Generate Assets
        DIMENSION = new Dimension(FIELD_WIDTH * SCALE, FIELD_HEIGHT * SCALE);
        ICON = imageReader.read(config.get("icon").asText());
        BACKGROUND_COVER = imageReader.scaleImage(config.get("backgroundCover").asText(), SCALE);
        BACKGROUND_TILE = imageReader.scaleImage(config.get("backgroundTile").asText(), SCALE);
        HEAD = imageReader.scaleImage(config.get("head").asText(), SCALE);
        UPPER_BODY = imageReader.scaleImage(config.get("upperBody").asText(), SCALE);
        LOWER_BODY = imageReader.scaleImage(config.get("lowerBody").asText(), SCALE);
        LEG_TILE = imageReader.scaleImage(config.get("legTile").asText(), SCALE);
        LEG_TRANSITION = imageReader.scaleImage(config.get("legTransition").asText(), SCALE);
        FEET = imageReader.scaleImage(config.get("feet").asText(), SCALE);
        FOOD = imageReader.scaleImage(config.get("food").asText(), SCALE);
        GOLD_FOOD = imageReader.scaleImage(config.get("goldFood").asText(), SCALE);

        // Animations
        HEAD_ANIMATION = imageReader.readGif(config.get("headAnimation").asText(), SCALE);
        UPPER_BODY_ANIMATION = imageReader.readGif(config.get("upperBodyAnimation").asText(), SCALE);
        LOWER_BODY_ANIMATION = imageReader.readGif(config.get("lowerBodyAnimation").asText(), SCALE);
        LEG_TILE_ANIMATION = imageReader.readGif(config.get("legTileAnimation").asText(), SCALE);
        LEG_TRANSITION_ANIMATION = imageReader.readGif(config.get("legTransitionAnimation").asText(), SCALE);
        FEET_ANIMATION = imageReader.readGif(config.get("feetAnimation").asText(), SCALE);
        OP_FOOD_ANIMATION = imageReader.readGif(config.get("opFoodAnimation").asText(), SCALE);

        // Language set
        LANGUAGE = args.length > 0 ? args[0] : "en";
        JsonNode languageConfig = LANGUAGE.length() == 2 ? jsonUtility.load("/language/" + LANGUAGE + ".json") : jsonUtility.load(args[0], true);

        // Language
        TITLE = languageConfig.get("title").asText();
        RESTART = languageConfig.get("restart").asText();
        RESTART_TOOL_TIP = languageConfig.get("restartToolTip").asText();
        GAME_OVER = languageConfig.get("gameOver").asText();
        SCORE_PREFIX = languageConfig.get("scorePrefix").asText();
        FPS_PREFIX = languageConfig.get("fpsPrefix").asText();

        // Colors
        GRID_LAYOUT_COLOR = config.get("gridLayoutColor").asColor();
        SNAKE_HITBOX_COLOR = config.get("snakeHitboxColor").asColor();
        FOOD_HITBOX_COLOR = config.get("foodHitboxColor").asColor();
        FPS_COLOR = config.get("fpsColor").asColor();
        SCORE_COLOR = config.get("scoreColor").asColor();
        TEXT_COLOR = config.get("textColor").asColor();
        BACKGROUND_COLOR = config.get("backgroundColor").asColor();
        SNAKE_COLOR = config.get("snakeColor").asColor();
        FOOD_COLOR = config.get("foodColor").asColor();
        GOLD_FOOD_COLOR = config.get("goldFoodColor").asColor();
        OP_FOOD_COLOR = config.get("opFoodColor").asColor();

        // Sound
        audioPlayer.loadAudio(FOOD_SOUND);
        audioPlayer.loadAudio(ULT_SOUND);
        audioPlayer.loadAudio(DIE_SOUND);
    }

    // Constructor asset streaming
    public Config(String[] args, String url) {

        // Init Utilities
        audioPlayer = new AudioPlayer(url);
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

        SCALE = config.get("scale").asInt();
        FIELD_WIDTH = config.get("fieldWidth").asInt();
        FIELD_HEIGHT = config.get("fieldHeight").asInt();
        FPS = config.get("fps").asInt();
        TPS = config.get("tps").asInt();
        OP_ULT_GROW_INTERVAL = config.get("opUltGrowInterval").asLong();
        SPECIAL_FOOD_DURATION = config.get("specialFoodDuration").asLong();
        SPECIAL_FOOD_CHANCE = config.get("specialFoodChance").asDouble();
        ULT_SPEED_MODIFIER = config.get("ultSpeed").asDouble();
        OP_ULT_SPEED_MODIFIER = config.get("opUltSpeed").asDouble();
        RESIZABLE = config.get("resizable").asBoolean();
        SOLID_WALLS = config.get("solidWalls").asBoolean();
        FOOD_SOUND = config.get("foodSound").asText();
        ULT_SOUND = config.get("ultSound").asText();
        DIE_SOUND = config.get("dieSound").asText();

        // Generate Assets
        DIMENSION = new Dimension(FIELD_WIDTH * SCALE, FIELD_HEIGHT * SCALE);
        ICON = imageStreamer.read(config.get("icon").asText());
        BACKGROUND_COVER = imageStreamer.scaleImage(config.get("backgroundCover").asText(), SCALE);
        BACKGROUND_TILE = imageStreamer.scaleImage(config.get("backgroundTile").asText(), SCALE);
        HEAD = imageStreamer.scaleImage(config.get("head").asText(), SCALE);
        UPPER_BODY = imageStreamer.scaleImage(config.get("upperBody").asText(), SCALE);
        LOWER_BODY = imageStreamer.scaleImage(config.get("lowerBody").asText(), SCALE);
        LEG_TILE = imageStreamer.scaleImage(config.get("legTile").asText(), SCALE);
        LEG_TRANSITION = imageStreamer.scaleImage(config.get("legTransition").asText(), SCALE);
        FEET = imageStreamer.scaleImage(config.get("feet").asText(), SCALE);
        FOOD = imageStreamer.scaleImage(config.get("food").asText(), SCALE);
        GOLD_FOOD = imageStreamer.scaleImage(config.get("goldFood").asText(), SCALE);

        // Animations
        HEAD_ANIMATION = imageStreamer.readGif(config.get("headAnimation").asText(), SCALE);
        UPPER_BODY_ANIMATION = imageStreamer.readGif(config.get("upperBodyAnimation").asText(), SCALE);
        LOWER_BODY_ANIMATION = imageStreamer.readGif(config.get("lowerBodyAnimation").asText(), SCALE);
        LEG_TILE_ANIMATION = imageStreamer.readGif(config.get("legTileAnimation").asText(), SCALE);
        LEG_TRANSITION_ANIMATION = imageStreamer.readGif(config.get("legTransitionAnimation").asText(), SCALE);
        FEET_ANIMATION = imageStreamer.readGif(config.get("feetAnimation").asText(), SCALE);
        OP_FOOD_ANIMATION = imageStreamer.readGif(config.get("opFoodAnimation").asText(), SCALE);

        // Language set
        LANGUAGE = args.length > 0 ? args[0] : "en";
        JsonNode languageConfig = LANGUAGE.length() == 2 ? jsonUtility.load("/language/" + LANGUAGE + ".json") : jsonUtility.load(args[0], true);

        // Language
        TITLE = languageConfig.get("title").asText();
        RESTART = languageConfig.get("restart").asText();
        RESTART_TOOL_TIP = languageConfig.get("restartToolTip").asText();
        GAME_OVER = languageConfig.get("gameOver").asText();
        SCORE_PREFIX = languageConfig.get("scorePrefix").asText();
        FPS_PREFIX = languageConfig.get("fpsPrefix").asText();

        // Colors
        GRID_LAYOUT_COLOR = config.get("gridLayoutColor").asColor();
        SNAKE_HITBOX_COLOR = config.get("snakeHitboxColor").asColor();
        FOOD_HITBOX_COLOR = config.get("foodHitboxColor").asColor();
        FPS_COLOR = config.get("fpsColor").asColor();
        SCORE_COLOR = config.get("scoreColor").asColor();
        TEXT_COLOR = config.get("textColor").asColor();
        BACKGROUND_COLOR = config.get("backgroundColor").asColor();
        SNAKE_COLOR = config.get("snakeColor").asColor();
        FOOD_COLOR = config.get("foodColor").asColor();
        GOLD_FOOD_COLOR = config.get("goldFoodColor").asColor();
        OP_FOOD_COLOR = config.get("opFoodColor").asColor();

        // Sound
        audioPlayer.loadAudio(FOOD_SOUND);
        audioPlayer.loadAudio(ULT_SOUND);
        audioPlayer.loadAudio(DIE_SOUND);
    }

    // Getter
    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }
}