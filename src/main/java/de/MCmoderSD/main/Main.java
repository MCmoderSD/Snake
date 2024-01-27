package de.MCmoderSD.main;

import de.MCmoderSD.UI.Frame;
import de.MCmoderSD.utilities.Calculate;

public class Main {
    public static boolean IS_RUNNING = true;

    public static void main(String[] args) {
        if (args.length > 1) new Frame(new Config(args)); // Custom Assets
        else
            new Frame(Calculate.ifFileExists("/config/default.json") ? new Config(args) : new Config(args, "https://raw.githubusercontent.com/MCmoderSD/Snake/master/src/main/resources"));
    }
}