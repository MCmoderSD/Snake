package de.MCmoderSD.objects;

import de.MCmoderSD.main.Config;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Food extends Rectangle {
    public Food(Config config, ArrayList<SnakePiece> snakePieces) {
        Random random = new Random();

        int scale = config.getScale();
        int fieldWidth = config.getFieldWidth();
        int fieldHeight = config.getFieldHeight();

        x = random.nextInt(fieldWidth);
        y = random.nextInt(fieldHeight);

        x = x * scale;
        y = y * scale;

        width = scale;
        height = scale;

        if (snakePieces.size() < fieldWidth * fieldHeight && !validSpawn(snakePieces)) new Food(config, snakePieces);
    }

    private boolean validSpawn(ArrayList<SnakePiece> snakePieces) {
        for (SnakePiece snakePiece : snakePieces) if (snakePiece.x == x && snakePiece.y == y) return false;
        return true;
    }
}
