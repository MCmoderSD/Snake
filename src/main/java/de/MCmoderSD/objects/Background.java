package de.MCmoderSD.objects;

import de.MCmoderSD.main.Config;

import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class Background {

    // Attributes
    private final BackgroundTile[][] background;
    private final ArrayList<BackgroundTile> backgroundTiles;
    private final Color color;

    // Constructor
    public Background(Config config) {
        color = config.getBackgroundColor();
        backgroundTiles = new ArrayList<>();
        background = new BackgroundTile[config.getFieldWidth()][config.getFieldHeight()];

        for (int x = 0; x < config.getFieldWidth(); x++)
            for (int y = 0; y < config.getFieldHeight(); y++)
                backgroundTiles.add(background[x][y] = new BackgroundTile(config, x, y));
    }

    // Getter
    public Color getColor() {
        return color;
    }

    public BackgroundTile[][] getBackgroundTiles() {
        return background;
    }

    public BackgroundTile getBackgroundTile(int x, int y) {
        return background[x][y];
    }

    public ArrayList<BackgroundTile> getBackgroundTilesList() {
        return backgroundTiles;
    }

    public BackgroundTile getBackgroundTile(int index) {
        return backgroundTiles.get(index);
    }

    public int getBackgroundTileCount() {
        return backgroundTiles.size();
    }
}
