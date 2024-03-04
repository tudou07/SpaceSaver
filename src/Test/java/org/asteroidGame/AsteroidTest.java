package org.asteroidGame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

import static org.junit.jupiter.api.Assertions.*;

class AsteroidTest {
    Player player;

    Window window;

    float posX;

    float posY;

    float randomDir;

    float speed;

    boolean top = true;

    boolean left = true;

  /**
   * Sets up before each test begins.
   */
  @BeforeEach
    void setup(){
        window = new Window();
        player = Player.getInstance(window);
    }

  /**
   * Tests if the posX of the asteroid is greater than the width
   * of the window.
   */
  @Test
    void checkPosX1(){
        if(posX > window.width){
            assertEquals(0, posX);
        }
    }

  /**
   * Tests if the asteroids position x is a negative coordinate on spawn.
   */
  @Test
    void checkPosX2(){
        if (posX < 0){
            assertEquals(window.width, posX);
        }
    }

  /**
   * Tests if the posY of the asteroid is greater than the height
   * of the window.
   */
  @Test
    void checkPosY1(){
        if (posY > window.height){
            assertEquals(0, posY);
        }
    }

  /**
   * Tests if the asteroids position y is a negative coordinate on spawn.
   */
  @Test
    void checkPosY2(){
        if (posY < 0){
            assertEquals(window.height, posY);
        }
    }

  /**
   * Tests if the spawn of an asteroid is set to a random position
   * on the top of the window.
   */
  @Test
    void checkRandPos1(){
        if(PApplet.floor(window.random(0, 2)) == 0){
            top = true;
        }
      assertTrue(top);
    }

  /**
   * Tests if the spawn of an asteroid is set to a random position
   * on the left of the window.
   */
  @Test
    void checkRandPos2(){
        if(PApplet.floor(window.random(0, 2)) == 0){
            left = true;
        }
      assertTrue(left);
    }

  /**
   * Tests if the spawn of an asteroid is within the -100 pixel block setup in the
   * top right x quadrant so the player doesn't lose their lives o spawn.
   */
  @Test
    void checkTopAndLeftX(){
        if (top && !left){
            posX = window.random(0, (window.height/2 - 100));
        }
        assertEquals(window.random(0, (window.height/2 - 100)), posX);
    }

  /**
   * Tests if the spawn of an asteroid is within the -100 pixel block setup in the
   * top right y quadrant so the player doesn't lose their lives o spawn.
   */
  @Test
    void checkTopAndLeftY(){
        if (top && !left){
            posY = window.random(0, (window.height/2 - 100));
        }
        assertEquals(window.random(0, (window.height/2 - 100)), posY);
    }

  /**
   * Tests if the spawn of an asteroid is within the -100 pixel block setup in the
   * bottom left x quadrant so the player doesn't lose their lives o spawn.
   */
  @Test
    void checkNotTopAndLeftX(){
        if(!top && left){
            posX = window.random(0, (window.width/2 - 100));
        }
        assertEquals(window.random(0, (window.width/2 - 100)), posX);
    }

  /**
   * Tests if the spawn of an asteroid is within the -100 pixel block setup in the
   * bottom left y quadrant so the player doesn't lose their lives o spawn.
   */
  @Test
  void checkNotTopAndLeftY(){
    if(!top && left){
      posX = window.random(0, (window.width/2 - 100));
    }
    assertEquals(window.random(0, (window.width/2 - 100)), posY);
  }

  /**
   * Tests if the posX of an asteroid updates in a random direction * the speed.
   */
  @Test
    void checkUpdate1(){
        posX += window.cos(randomDir)*speed;
        assertEquals(posX += PApplet.cos(randomDir)*speed, posX);
    }

  /**
   * Tests if the posX of an asteroid decrements its position in a random direction
   * * the speed.
   */
  @Test
  void checkDecrementUpdate1(){
    posX -= PApplet.cos(randomDir)*speed;
    assertEquals(posX -= PApplet.cos(randomDir)*speed, posX);
  }

  /**
   * Tests if the posY of an asteroid decrements its position in a random direction
   * * the speed.
   */
  @Test
  void checkDecrementUpdate2(){
    posY -= PApplet.cos(randomDir)*speed;
    assertEquals(posX -= PApplet.cos(randomDir)*speed, posY);
  }

  /**
   * Tests if the posY of an asteroid updates in a random direction
   * multiplied by the speed of the asteroid.
   */
  @Test
    void checkUpdate2(){
        posY += PApplet.sin(randomDir)*speed;
        assertEquals(posY += PApplet.sin(randomDir)*speed, posY);
    }

}