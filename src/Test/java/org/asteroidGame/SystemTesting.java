package org.asteroidGame;

import org.junit.jupiter.api.Test;
import processing.core.PApplet;

import static org.junit.jupiter.api.Assertions.*;

class SystemTesting {

    /**
     * The speed of whole program should be less than 2 sec
     */
    @Test
    public void speedTest(){
        long windowStart = System.currentTimeMillis();

        String[] appletArgs = new String[]{"Spacesaver"};
        Window window = new Window();
        PApplet.runSketch(appletArgs, window);

        long windowEnd = System.currentTimeMillis();

        assertTrue((windowEnd - windowStart) < 2000);
    }
}