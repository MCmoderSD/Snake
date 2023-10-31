package de.MCmoderSD.main;

import com.fasterxml.jackson.databind.JsonNode;
import de.MCmoderSD.utilities.ImageReader;
import de.MCmoderSD.utilities.JsonReader;


import java.awt.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("unused")
public class Config {

    // Config Variables
    private final String title;
    private final String iconPath;
    private final int scale;
    private final int fieldWidth;
    private final int fieldHeight;
    private final int fps;
    private final boolean resizable;

    // Assets
    private final Dimension dimension;
    private final BufferedImage icon;

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
        resizable = config.get("resizable").asBoolean();

        // Generate Assets
        dimension = new Dimension(fieldWidth * scale, fieldHeight * scale);
        icon = imageReader.read(iconPath);
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

    public boolean isResizable() {
        return resizable;
    }


    // Asset Getters
    public BufferedImage getIcon() {
        return icon;
    }

    public Dimension getDimension() {
        return dimension;
    }
}
