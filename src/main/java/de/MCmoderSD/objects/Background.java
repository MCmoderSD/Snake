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
    public Background() {
        color = Config.BACKGROUND_COLOR;
        backgroundTiles = new ArrayList<>();

        for (int x = 0; x < Config.FIELD_WIDTH; x++)
            for (int y = 0; y < Config.FIELD_HEIGHT; y++)
                backgroundTiles.add(new BackgroundTile(x, y));
    }

    // Getter
    public Color getColor() {
        return color;
    }

    public ArrayList<BackgroundTile> getBackgroundTiles() {
        return backgroundTiles;
    }
}
