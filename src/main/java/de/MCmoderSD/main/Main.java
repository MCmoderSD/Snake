package de.MCmoderSD.main;

import de.MCmoderSD.UI.Frame;
import de.MCmoderSD.utilities.Calculate;

public class Main {
    public static boolean IS_RUNNING = true;

    public static void main(String[] args) {

        // Init Config
        Config.init(args);

        // Init Frame
        new Frame();
    }
}