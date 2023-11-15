package de.MCmoderSD.main;

import de.MCmoderSD.core.Game;
import de.MCmoderSD.utilities.Calculate;

public class Main {
    public static boolean isRunning = true;
    public static boolean streamingMode;
    public static void main(String[] args) {
        streamingMode = !Calculate.doesFileExist("/config/default.json");

        if (!streamingMode) new Game(new Config(args));
        else new Game(new Config(args, "https://raw.githubusercontent.com/MCmoderSD/Snake/master/src/main/resources"));
    }
}