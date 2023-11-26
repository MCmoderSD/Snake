package de.MCmoderSD.main;

import de.MCmoderSD.core.Game;
import de.MCmoderSD.utilities.Calculate;

public class Main {
    public static boolean isRunning = true;

    public static void main(String[] args) {
        new Game(args.length < 2 ? Calculate.ifFileExists("/config/default.json") ? new Config(args) : new Config(args, "https://raw.githubusercontent.com/MCmoderSD/Snake/master/src/main/resources") : new Config(args));
    }
}