package org.asteroidGame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

  Window window;

  Player player;

  float posX;
  float posY;

  /**
   * Sets up before each test begins.
   */
  @BeforeEach
  void setup(){
    window = new Window();
    player = Player.getInstance(window);
  }

  /**
   * Tests position x of the laser to check if it's moving.
   */
  @Test
  void checkPosXLaser1(){
    if (posX > window.width){
      assertEquals(0, posX);
    }
  }

  /**
   * Tests the position x of the laser relative to the height
   * of the window.
   */
  @Test
  void checkNotPosXLaser(){
    if (posX > window.height){
      assertNotEquals(4, posX, 0);
    }
  }

  /**
   * Tests the laser position x if it's moving.
   */
  @Test
  void checkPosXLaser2(){
    if (posX < 0){
      assertEquals(window.width, posX);
    }
  }

  /**
   * Tests if the position x of the laser fired is moving
   * backwards when fired.
   */
  @Test
  void checkNotPosXLaser2(){
    if (posX < 0){
      assertNotEquals(-5, window.width, posX);
    }
  }

  /**
   * Tests position y of the laser to check if it's moving.
   */
  @Test
  void checkPosYLaser1(){
    if (posY > window.height){
      assertEquals(0, posY);
    }
  }

  /**
   * Tests if the position x of the laser fired is moving
   * backwards when fired.
   */
  @Test
  void checkPosYLaser2(){
    if (posY < 0){
      assertEquals(window.height, posY);
    }
  }
}