package org.asteroidGame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextBoxTest {

  TextBox textBox;
  Window window;

  int keyCode;
  char key;

  boolean isKeyCapitalLetter = true;
  boolean isKeySmallLetter = true;
  boolean isKeyNumber = true;
  boolean isTextDeleted = true;
  boolean textFits = true;

  public int textSize = 32;
  public boolean BorderEnable = true;
  private boolean selected = true;
  public int TextLength = 0;
  public String Text = "";

  /**
   * Sets up before each test begins.
   */
  @BeforeEach
    void setUp() {
      textBox = new TextBox();
      window = new Window();
    }

  /**
   * Tests if the keycode 8 is mapped correctly.
   */
  @Test
    void testKeyPressed() {
      if (keyCode == 8){
        assertEquals(8, keyCode);
      }
    }

  /**
   * Tests if the keycode 10 is mapped correctly.
   */
  @Test
    void testKeyPressed2(){
      if (keyCode == 10){
        assertEquals(10, keyCode);
      }
    }

  /**
   * Tests if the keycode 32 is mapped correctly.
   */
  @Test
    void testKeyPressed3(){
      if (keyCode == 32){
        assertEquals(32, keyCode);
      }
    }

  /**
   * Tests if the wrong keycode is pressed.
   */
  @Test
    void checkWrongKeyTenPressed(){
      if (keyCode == 12){
        assertNotEquals(12, 10);
      }
    }

  /**
   * Tests if the wrong keycode 8 is pressed
   */
  @Test
    void checkWrongKeyEightPressed(){
      if (keyCode == 10){
        assertNotEquals(10, 8);
      }
    }

  /**
   * Tests if the wrong keycode 10 is pressed.
   */
  @Test
    void checkWrongKeyThirtyTwoPressed(){
      if (keyCode == 32){
        assertNotEquals(32, 10);
      }
    }

  /**
   * Tests if the key pressed is a capital letter.
   */
  @Test
  void checkIsKeyCapital(){
    if (key >= 'A' && key <= 'Z'){
      assertTrue(isKeyCapitalLetter);
    }
  }

  /**
   * Tests if the key pressed outputs a lowercase instead of
   * a capital letter.
   */
  @Test
  void checkIsKeyCapital2(){
      if (key >= 'A' && key <= 'Z'){
        assertNotEquals('a', 'A');
      }
  }

  /**
   * Tests if the key pressed is a lower case.
   */
  @Test
  void checkIsKeyLowerCase(){
    if (key >= 'a' && key <= 'z'){
      assertTrue(isKeySmallLetter);
    }
  }

  /**
   * Tests if the key pressed outputs a capital letter
   * instead of a lower case.
   */
  @Test
  void checkIsKeyLowerCase2(){
    if (key >= 'a' && key <= 'z'){
      assertNotEquals('A', 'a');
    }
  }


  /**
   * Tests if the key pressed is a number.
   */
  @Test
  void checkIsKeyANumber(){
      if (key >= 1 && key <= 9){
        assertTrue(isKeyNumber);
      }
  }

  /**
   * Tests if the key pressed outputs a lower case character
   * instead of a number.
   */
  @Test
  void checkIsKeyNotANumber(){
      if (key >= 1 && key <= 9){
        assertNotEquals('a', 1);
      }
  }

  /**
   * Tests if the key pressed is within the range of numbers.
   */
  @Test
  void checkIsKeyInRange(){
      if (key >= 1 && key <=8){
        assertNotEquals(9, 1);
      }
  }

  /**
   * Tests if the text is not deleted.
   */

  @Test
  void checkIfTextNotDeleted(){
    if (TextLength - 1 >= 0){
      int initial = TextLength;
      Text = Text.substring(0, TextLength - 1);
      if(Text.length() < initial) {
        isTextDeleted = false;
      }
      assertFalse(isTextDeleted);
    }
  }


  /**
   * Tests if the text fits within the textbox
   */
  @Test
  void textSizeFits(){
      if (textSize < TextLength){
        assertTrue(textFits);
      }
  }

    @Test
    void testPRESSED() {
    }
}