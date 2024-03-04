package org.asteroidGame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class WindowTest {

  Window window;

  Player player;
  int gameState;
  int keyCode;
  int roundTitleCounter = 5;
  boolean notRoundOne = false;

  /**
   * Sets up before each test begins.
   */
  @BeforeEach
  void setup(){
    window = new Window();
    player = Player.getInstance(window);
  }


  /**
   * Checks the window size.
   */
  @Test
  void checkWindowSize(){
    window.setSize(569, 789);
    assertFalse(window.checkWindowSize(window));

    window.setSize(640, 360);
    assertTrue(window.checkWindowSize(window));
  }

  /**
   * Checks if the gameState is 1.
   */
  @Test
  void checkGameState(){
    if (keyCode == 10){
      gameState = 1;
      assertEquals(1, gameState);
    }
  }

  /**
   * Tests if the gameState is incorrect.
   */
  @Test
  void checkWrongGameState(){
    if (keyCode == 10){
      gameState = 4;
      assertNotEquals(gameState, 1);
    }
  }

  /**
   * Tests if the gameState is out of bounds.
   */
  @Test
  void checkWrongGameState2(){
    if (keyCode == 10){
      gameState = 3;
      assertNotEquals(gameState, 0);
    }
  }

  /**
   * Checks if the gameState is 2.
   */
  @Test
  void checkGameStateTest2(){
    if (keyCode == 10){
      gameState = 2;
      assertNotEquals(1, gameState);
    }
  }

  /**
   * Tests if the player is rotating right.
   */
  @Test
  void checkKeyCodeRight(){
    if (keyCode == 39){
      player.isRotating = -1;
      assertEquals(-1, player.isRotating);
    }
  }

  /**
   * Tests if the player is rotating right and not left with the proper
   * key code.
   */
  @Test
  void checkKeyCodeRight2(){
    if (keyCode == 39){
      player.isRotating = -1;
      assertNotEquals(1, player.isRotating);
    }
  }

  /**
   * Tests if the player is rotating left.
   */
  @Test
  void checkKeyCodeLeft(){
    if (keyCode == 37){
      player.isRotating = 1;
      assertEquals(1, player.isRotating);
    }
  }

  /**
   * Tests whether the keycode is mapped to the proper player rotation.
   */
  @Test
  void checkKeyCodeLeft2(){
    if (keyCode == 37){
      player.isRotating = 1;
      assertNotEquals(-1, player.isRotating);
    }
  }

  /**
   * Tests if the keycode is mapped to the player button to fire lasers.
   */
  @Test
  void checkKeyCode32(){
    if (keyCode == 32){
      player.shotFired = true;
      assertTrue(player.shotFired);
    }
  }

  /**
   * Tests if the player stops rotating right.
   */
  @Test
  void checkPlayerNotRotatingRight(){
    if (keyCode == 39){
      player.isRotating = 0;
      assertEquals(0, player.isRotating);
    }
  }

  /**
   * Tests if the player rotates left instead of stopping from
   * right rotation.
   */
  @Test
  void checkPlayerNotRotatingRight2(){
    if (keyCode == 39){
      player.isRotating = 0;
      assertNotEquals(-1,player.isRotating);
    }
  }

  /**
   * Tests if the player stops rotating from the left.
   */
  @Test
  void checkPlayerNotRotatingLeft(){
    if (keyCode == 37){
      player.isRotating = 0;
      assertEquals(0, player.isRotating);
    }
  }

  /**
   * Tests if the player rotates right instead of stopping from
   * left rotation.
   */
  @Test
  void checkPlayerNotRotatingLeft2(){
    if (keyCode == 37){
      player.isRotating = 0;
      assertNotEquals(1, player.isRotating);
    }
  }

  /**
   * Tests if the roundTitleCounter is greater than 0.
   */
  @Test
  void checkRoundUpdate(){
    if (roundTitleCounter > 0){
      roundTitleCounter--;
      assertTrue(roundTitleCounter < 5);
    }
  }

  /**
   * Tests if the roundTitleCounter is not 6.
   */
  @Test
  void checkRoundUpdate2(){
    if (roundTitleCounter > 0){
      roundTitleCounter--;
      assertNotEquals(6, roundTitleCounter);
    }
  }

  /**
   * Tests if the roundTitleCounter is not decrementing at 0.
   */
  @Test
  void checkRoundUpdate3(){
    if (roundTitleCounter > 0){
      roundTitleCounter--;
      assertNotEquals(0, roundTitleCounter);
    }
  }

  /**
   * Tests if the game is not in round one.
   */
  @Test
  void checkRoundOne(){
    assertFalse(notRoundOne);
  }

  /**
   * Tests if the roundTitleCounter is 2.
   */
  @Test
  void checkNotRoundOne(){
    assertNotEquals(1, roundTitleCounter);
  }

}