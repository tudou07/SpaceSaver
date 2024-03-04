package org.asteroidGame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PVector;

import static org.junit.jupiter.api.Assertions.*;

class StarTest {

  Star star;
  Window window;
  public static final int max_weight = 7;
  PVector pos;
  public boolean isMoving = true;
  public boolean isNotMoving = true;
  float size;
  int margin = 50;
  float speed;
  int TOP_SPEED = 1;

  /**
   * Sets up before each test begins.
   */
    @BeforeEach
    void setUp() {
      window = new Window();
      star = new Star(window);
      pos = new PVector(window.width / 2f, window.height / 2f);
    }

    @Test
    void display() {
    }

    @Test
    void move() {
    }

  /**
   * Tests if the stars x position is greater than the margin set
   * to show the stars are moving.
   */
  @Test
    void checkStarsPosXMoving(){
      if (pos.x > window.width + margin){
        assertTrue(isMoving);
      }
    }

  /**
   * Tests if the stars x position is less than the margin set
   * to show the stars aren't moving.
   */
  @Test
  void checkStarsPosXNotMoving(){
    if (pos.x < window.width + margin){
      assertTrue(isNotMoving);
    }
  }

  /**
   * Tests if the stars y position is greater than the margin set
   * to show the stars are moving.
   */
    @Test
    void checkStarsPosYMoving(){
      if (pos.y > window.height + margin){
        assertTrue(isMoving);
      }
    }

  /**
   * Tests if the stars y position is less than the margin set
   * to show the stars aren't moving.
   */
  @Test
    void checkStarsPosYNotMoving(){
      if (pos.y < window.height + margin){
        assertTrue(isNotMoving);
      }
    }

  /**
   * Tests if the speed of the stars is updated.
   */
  @Test
    void checkStarSpeed(){
      if (speed == window.map(size, 0, max_weight, 1, TOP_SPEED)){
        assertTrue(isMoving);
      }
    }

  /**
   * Tests if the speed of the stars is not updated.
   */
  @Test
    void checkStarNoSpeed(){
      if (speed != window.map(size, 0, max_weight, 1, TOP_SPEED)){
        assertTrue(isNotMoving);
      }
    }


}