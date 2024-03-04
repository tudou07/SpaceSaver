package org.asteroidGame;

public class StarsStraght {

    float x;
    float y;
    float z;
    float pz;
    public float speed;

  /**
   * Constructor for starsStraght class.
   * @param w window for the user
   */
  StarsStraght(Window w){
        x = w.random(-w.width, w.width);
        y = w.random(-w.height, w.height);
        z = w.random( w.width);
        speed = w.speed;
        pz = z;
    }

  /**
   * Converts the value of speed (pixels on the screen) into a value that ranges from the left edge of the
   * window to the right and updates the speed of the pixels with the mouse centered in the main menu.
   * @param w window for the user
   */
  void update(Window w){
        speed = w.map (w.mouseX, 5, w.width+10, 5, 30);
        z = z - speed;
        if(z < 1){
            z = w.width;
            x = w.random(-w.width, w.width);
            y = w.random(-w.height, w.height);
            pz = z;
        }
    }

  /**
   * Shows the stars streaming on the menu screen.
   * @param w window for the user
   */
  void show(Window w){
        w.fill(255);
        w.noStroke();

        float sx = w.map((x / z), 0, 1, 0, w.width);
        float sy = w.map((y / z), 0, 1, 0, w.height);

        float r = w.map(z, 0, w.width, 16, 0);
        w.ellipse(sx, sy, r, r);

        float px = w.map((x / pz), 0, 1, 0, w.width);
        float py = w.map((y / pz), 0, 1, 0, w.height);


        pz = z;
        w.stroke(255);
        w.line(px, py, sx, sy);
    }
}