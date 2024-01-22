package de.MCmoderSD.objects;

import de.MCmoderSD.main.Config;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class Background {

    // Attributes
    private final ArrayList<BackgroundTile> backgroundTiles;
    private final Color color;

    // Constructor
    public Background(Config config) {
        color = config.getBackgroundColor();
        backgroundTiles = new ArrayList<>();

        for (int x = 0; x < config.getFieldWidth(); x++)
            for (int y = 0; y < config.getFieldHeight(); y++)
                backgroundTiles.add(new BackgroundTile(config, x, y));
    }

    // Getter
    public Color getColor() {
        return color;
    }

    public ArrayList<BackgroundTile> getBackgroundTiles() {
        return backgroundTiles;
    }
}
