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
    
    // Constants
    public static final String configPath = "/config/default.json";
    public static final String streamingURL = "https://raw.githubusercontent.com/MCmoderSD/Snake/refs/heads/master/src/main/resources";
    
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

        // Load Config
        JsonNode config = null;
        boolean customConfig = args.length > 1;
        boolean textureStreaming = !customConfig && Config.class.getResourceAsStream(configPath) == null;

        try {
            config = customConfig ? jsonUtility.load(args[1], true) : jsonUtility.load(textureStreaming ? streamingURL + configPath : configPath, false);
        } catch (IOException | URISyntaxException e) {
            System.err.println("An error occurred while loading Config: " + e.getMessage());
        }


        // Check if config is null
        if (config == null) throw new NullPointerException("Config is null");

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

        // Load Images
        JsonNode images = assets.get("images");
        String iconPath = images.get("icon").asText();
        String backgroundTilePath = images.get("backgroundTile").asText();
        String backgroundCoverPath = images.get("backgroundCover").asText();
        String headPath = images.get("head").asText();
        String upperBodyPath = images.get("upperBody").asText();
        String lowerBodyPath = images.get("lowerBody").asText();
        String legTilePath = images.get("legTile").asText();
        String legTransitionPath = images.get("legTransition").asText();
        String feetPath = images.get("feet").asText();
        String foodPath = images.get("food").asText();
        String goldFoodPath = images.get("goldFood").asText();

        // Load Animations
        JsonNode animations = assets.get("animations");
        String headAnimationPath = animations.get("head").asText();
        String upperBodyAnimationPath = animations.get("upperBody").asText();
        String lowerBodyAnimationPath = animations.get("lowerBody").asText();
        String legTileAnimationPath = animations.get("legTile").asText();
        String legTransitionAnimationPath = animations.get("legTransition").asText();
        String feetAnimationPath = animations.get("feet").asText();
        String opFoodAnimationPath = animations.get("opFood").asText();

        // Load Sounds
        JsonNode sounds = assets.get("sounds");
        String foodSoundPath = sounds.get("food").asText();
        String ultSoundPath = sounds.get("ult").asText();
        String dieSoundPath = sounds.get("die").asText();

        try {

            // Load Images
            ICON = imageLoader.load(textureStreaming ? streamingURL + iconPath : iconPath);
            BACKGROUND_TILE = scaleImage(imageLoader.load(textureStreaming ? streamingURL + backgroundTilePath : backgroundTilePath));
            BACKGROUND_COVER = scaleImage(imageLoader.load(textureStreaming ? streamingURL + backgroundCoverPath : backgroundCoverPath));
            HEAD = scaleImage(imageLoader.load(textureStreaming ? streamingURL + headPath : headPath));
            UPPER_BODY = scaleImage(imageLoader.load(textureStreaming ? streamingURL + upperBodyPath : upperBodyPath));
            LOWER_BODY = scaleImage(imageLoader.load(textureStreaming ? streamingURL + lowerBodyPath : lowerBodyPath));
            LEG_TILE = scaleImage(imageLoader.load(textureStreaming ? streamingURL + legTilePath : legTilePath));
            LEG_TRANSITION = scaleImage(imageLoader.load(textureStreaming ? streamingURL + legTransitionPath : legTransitionPath));
            FEET = scaleImage(imageLoader.load(textureStreaming ? streamingURL + feetPath : feetPath));
            FOOD = scaleImage(imageLoader.load(textureStreaming ? streamingURL + foodPath : foodPath));
            GOLD_FOOD = scaleImage(imageLoader.load(textureStreaming ? streamingURL + goldFoodPath : goldFoodPath));

            // Load Animations
            HEAD_ANIMATION = new ImageIcon(scaleImage(imageLoader.load(textureStreaming ? streamingURL + headAnimationPath : headAnimationPath)));
            UPPER_BODY_ANIMATION = new ImageIcon(scaleImage(imageLoader.load(textureStreaming ? streamingURL + upperBodyAnimationPath : upperBodyAnimationPath)));
            LOWER_BODY_ANIMATION = new ImageIcon(scaleImage(imageLoader.load(textureStreaming ? streamingURL + lowerBodyAnimationPath : lowerBodyAnimationPath)));
            LEG_TILE_ANIMATION = new ImageIcon(scaleImage(imageLoader.load(textureStreaming ? streamingURL + legTileAnimationPath : legTileAnimationPath)));
            LEG_TRANSITION_ANIMATION = new ImageIcon(scaleImage(imageLoader.load(textureStreaming ? streamingURL + legTransitionAnimationPath : legTransitionAnimationPath)));
            FEET_ANIMATION = new ImageIcon(scaleImage(imageLoader.load(textureStreaming ? streamingURL + feetAnimationPath : feetAnimationPath)));
            OP_FOOD_ANIMATION = new ImageIcon(scaleImage(imageLoader.load(textureStreaming ? streamingURL + opFoodAnimationPath : opFoodAnimationPath)));

            // Sounds
            FOOD_SOUND = audioLoader.load(textureStreaming ? streamingURL + foodSoundPath : foodSoundPath);
            ULT_SOUND = audioLoader.load(textureStreaming ? streamingURL + ultSoundPath : ultSoundPath);
            DIE_SOUND = audioLoader.load(textureStreaming ? streamingURL + dieSoundPath : dieSoundPath);
        } catch (IOException | URISyntaxException e) {
            System.err.println("An error occurred while loading Images: " + e.getMessage());
        }


        // Load Language
        JsonNode languageConfig;
        try {
            languageConfig = jsonUtility.load("/languages/en.json");
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        // Check if languageConfig is null
        if (languageConfig == null) throw new NullPointerException("Language Config is null");

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