package de.MCmoderSD.main;

import de.MCmoderSD.UI.Frame;

public class Main {
    
    public static void main(String[] args) {

        // Init Config
        Config.init(args);

        // Init Frame
        new Frame();
    }
}