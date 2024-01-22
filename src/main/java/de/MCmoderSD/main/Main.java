package de.MCmoderSD.main;

import de.MCmoderSD.UI.Frame;
import de.MCmoderSD.utilities.Calculate;

public class Main {
    public static boolean IS_RUNNING = true;

    public static void main(String[] args) {
        new Frame(args.length < 2 ? Calculate.ifFileExists("/config/default.json") ? new Config(args) : new Config(args, "https://raw.githubusercontent.com/MCmoderSD/Snake/master/src/main/resources") : new Config(args));
    }
}