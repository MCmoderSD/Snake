package de.MCmoderSD.main;

import de.MCmoderSD.core.Game;

public class Main {
    public static boolean isRunning = true;
    public static void main(String[] args) {
        new Game(new Config(args));
    }
}
