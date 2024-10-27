package de.MCmoderSD.main;

import com.fasterxml.jackson.databind.JsonNode;
import de.MCmoderSD.JavaAudioLibrary.AudioFile;
import de.MCmoderSD.JavaAudioLibrary.AudioLoader;
import de.MCmoderSD.imageloader.AnimationLoader;
import de.MCmoderSD.imageloader.ImageLoader;
import de.MCmoderSD.json.JsonUtility;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

@SuppressWarnings("unused")
public class Config {
    
    // Constants
    public static final String CONFIG_PATH = "/config/default.json";
    public static final String STREAMING_URL = "https://raw.githubusercontent.com/MCmoderSD/Snake/refs/heads/master/src/main/resources";
    
    // Config Variables
    public static int SCALE;
    public static int FIELD_WIDTH;
    public static int FIELD_HEIGHT;
    public static int FPS;
    public static int TPS;
    public static long OP_ULT_GROW_INTERVAL;
    public static long SPECIAL_FOOD_DURATION;
    public static float SPECIAL_FOOD_CHANCE;
    public static float ULT_SPEED_MODIFIER;
    public static float OP_ULT_SPEED_MODIFIER;
    public static boolean SOLID_WALLS;

    // Language
    public static String LANGUAGE;
    public static String TITLE;
    public static String RESTART;
    public static String RESTART_TOOL_TIP;
    public static String GAME_OVER;
    public static String SCORE_PREFIX;
    public static String FPS_PREFIX;
    public static String TPS_PREFIX;

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

    // Animations
    public static ImageIcon HEAD_ANIMATION;
    public static ImageIcon UPPER_BODY_ANIMATION;
    public static ImageIcon LOWER_BODY_ANIMATION;
    public static ImageIcon LEG_TILE_ANIMATION;
    public static ImageIcon LEG_TRANSITION_ANIMATION;
    public static ImageIcon FEET_ANIMATION;
    public static ImageIcon OP_FOOD_ANIMATION;

    // Sounds
    public static AudioFile FOOD_SOUND;
    public static AudioFile ULT_SOUND;
    public static AudioFile DIE_SOUND;

    // Colors
    public static Color GRID_LAYOUT_COLOR;
    public static Color SNAKE_HITBOX_COLOR;
    public static Color FOOD_HITBOX_COLOR;
    public static Color FPS_COLOR;
    public static Color TPS_COLOR;
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
        ImageLoader imageLoader = new ImageLoader();
        AnimationLoader animationLoader = new AnimationLoader();
        AudioLoader audioLoader = new AudioLoader();
        JsonUtility jsonUtility = new JsonUtility();

        // Get Config Path
        JsonNode config = null;
        boolean customConfig = args.length > 1;
        boolean textureStreaming = !customConfig && Config.class.getResourceAsStream(CONFIG_PATH) == null;

        // Get Language Config Path
        JsonNode languageConfig = null;
        boolean customLanguage = args.length == 1;
        String languagePath = null;
        if (customConfig) languagePath = args[0];
        if (customLanguage) languagePath = "/languages/" + args[0].toLowerCase() + ".json";
        languagePath = textureStreaming ? STREAMING_URL + (customLanguage ? languagePath : "/languages/en.json") : "/languages/en.json";

        try {

            // Load Config
            config = customConfig ? jsonUtility.load(args[1], true) : jsonUtility.load(textureStreaming ? STREAMING_URL + CONFIG_PATH : CONFIG_PATH, false);

            // Load Language
            languageConfig = jsonUtility.load(languagePath, customConfig);

        } catch (IOException | URISyntaxException e) {
            System.err.println("An error occurred while loading Config: " + e.getMessage());
        }

        // Check if config is null
        if (config == null) throw new NullPointerException("Config is null");


        // Check if languageConfig is null
        if (languageConfig == null) throw new NullPointerException("Language Config is null");

        // Load Settings
        JsonNode settings = config.get("settings");
        SCALE = settings.get("scale").asInt();
        FIELD_WIDTH = settings.get("fieldWidth").asInt();
        FIELD_HEIGHT = settings.get("fieldHeight").asInt();
        FPS = settings.get("fps").asInt();
        TPS = settings.get("tps").asInt();
        OP_ULT_GROW_INTERVAL = settings.get("opUltGrowInterval").asLong();
        SPECIAL_FOOD_DURATION = settings.get("specialFoodDuration").asLong();
        SPECIAL_FOOD_CHANCE = Float.parseFloat(settings.get("specialFoodChance").asText());
        ULT_SPEED_MODIFIER = Float.parseFloat(settings.get("ultSpeed").asText());
        OP_ULT_SPEED_MODIFIER = Float.parseFloat(settings.get("opUltSpeed").asText());
        SOLID_WALLS = settings.get("solidWalls").asBoolean();
        DIMENSION = new Dimension(FIELD_WIDTH * SCALE, FIELD_HEIGHT * SCALE);

        // Language
        TITLE = languageConfig.get("title").asText();
        RESTART = languageConfig.get("restart").asText();
        RESTART_TOOL_TIP = languageConfig.get("restartToolTip").asText();
        GAME_OVER = languageConfig.get("gameOver").asText();
        SCORE_PREFIX = languageConfig.get("scorePrefix").asText();
        FPS_PREFIX = languageConfig.get("fpsPrefix").asText();
        TPS_PREFIX = languageConfig.get("tpsPrefix").asText();

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
            ICON = imageLoader.load(textureStreaming ? STREAMING_URL + iconPath : iconPath);
            BACKGROUND_TILE = scaleImage(imageLoader.load(textureStreaming ? STREAMING_URL + backgroundTilePath : backgroundTilePath));
            BACKGROUND_COVER = scaleImage(imageLoader.load(textureStreaming ? STREAMING_URL + backgroundCoverPath : backgroundCoverPath));
            HEAD = scaleImage(imageLoader.load(textureStreaming ? STREAMING_URL + headPath : headPath));
            UPPER_BODY = scaleImage(imageLoader.load(textureStreaming ? STREAMING_URL + upperBodyPath : upperBodyPath));
            LOWER_BODY = scaleImage(imageLoader.load(textureStreaming ? STREAMING_URL + lowerBodyPath : lowerBodyPath));
            LEG_TILE = scaleImage(imageLoader.load(textureStreaming ? STREAMING_URL + legTilePath : legTilePath));
            LEG_TRANSITION = scaleImage(imageLoader.load(textureStreaming ? STREAMING_URL + legTransitionPath : legTransitionPath));
            FEET = scaleImage(imageLoader.load(textureStreaming ? STREAMING_URL + feetPath : feetPath));
            FOOD = scaleImage(imageLoader.load(textureStreaming ? STREAMING_URL + foodPath : foodPath));
            GOLD_FOOD = scaleImage(imageLoader.load(textureStreaming ? STREAMING_URL + goldFoodPath : goldFoodPath));

            // Load Animations
            HEAD_ANIMATION = scaleAnimation(animationLoader.load(textureStreaming ? STREAMING_URL + headAnimationPath : headAnimationPath));
            UPPER_BODY_ANIMATION = scaleAnimation(animationLoader.load(textureStreaming ? STREAMING_URL + upperBodyAnimationPath : upperBodyAnimationPath));
            LOWER_BODY_ANIMATION = scaleAnimation(animationLoader.load(textureStreaming ? STREAMING_URL + lowerBodyAnimationPath : lowerBodyAnimationPath));
            LEG_TILE_ANIMATION = scaleAnimation(animationLoader.load(textureStreaming ? STREAMING_URL + legTileAnimationPath : legTileAnimationPath));
            LEG_TRANSITION_ANIMATION = scaleAnimation(animationLoader.load(textureStreaming ? STREAMING_URL + legTransitionAnimationPath : legTransitionAnimationPath));
            FEET_ANIMATION = scaleAnimation(animationLoader.load(textureStreaming ? STREAMING_URL + feetAnimationPath : feetAnimationPath));
            OP_FOOD_ANIMATION = scaleAnimation(animationLoader.load(textureStreaming ? STREAMING_URL + opFoodAnimationPath : opFoodAnimationPath));

            // Sounds
            FOOD_SOUND = audioLoader.load(textureStreaming ? STREAMING_URL + foodSoundPath : foodSoundPath);
            ULT_SOUND = audioLoader.load(textureStreaming ? STREAMING_URL + ultSoundPath : ultSoundPath);
            DIE_SOUND = audioLoader.load(textureStreaming ? STREAMING_URL + dieSoundPath : dieSoundPath);
            
        } catch (IOException | URISyntaxException e) {
            System.err.println("An error occurred while loading Images: " + e.getMessage());
        }

        // Colors
        JsonNode colors = config.get("colors");
        GRID_LAYOUT_COLOR = getColor(colors.get("gridLayout").asText());
        SNAKE_HITBOX_COLOR = getColor(colors.get("snakeHitbox").asText());
        FOOD_HITBOX_COLOR = getColor(colors.get("foodHitbox").asText());
        FPS_COLOR = getColor(colors.get("fps").asText());
        TPS_COLOR = getColor(colors.get("tps").asText());
        SCORE_COLOR = getColor(colors.get("score").asText());
        TEXT_COLOR = getColor(colors.get("text").asText());
        BACKGROUND_COLOR = getColor(colors.get("background").asText());
        SNAKE_COLOR = getColor(colors.get("snake").asText());
        FOOD_COLOR = getColor(colors.get("food").asText());
        GOLD_FOOD_COLOR = getColor(colors.get("goldFood").asText());
        OP_FOOD_COLOR = getColor(colors.get("opFood").asText());
    }

    // Helper Methods
    private static BufferedImage scaleImage(BufferedImage image) {
        BufferedImage scaledImage = new BufferedImage(SCALE, SCALE, BufferedImage.TYPE_INT_ARGB);
        scaledImage.getGraphics().drawImage(image.getScaledInstance(SCALE, SCALE, Image.SCALE_DEFAULT), 0, 0, SCALE, SCALE, null);
        return scaledImage;
    }
    
    private static ImageIcon scaleAnimation(ImageIcon icon) {
        return new ImageIcon(icon.getImage().getScaledInstance(SCALE, SCALE, Image.SCALE_DEFAULT));
    }

    private static Color getColor(String hex) {
        return new Color(Integer.parseInt(hex.substring(1), 16));
    }
}