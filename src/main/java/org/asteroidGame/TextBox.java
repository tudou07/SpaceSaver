package org.asteroidGame;

public class TextBox implements IText{

    public int textSize = 32;
    public int X = 0, Y = 0, H = 35, W = 200;
    public boolean BorderEnable = true;
    private boolean selected = true;
    public int TextLength = 0;
    public String Text = "";
    public int BorderWeight = 1;

    TextBox(){

    }

  /**
   * Constructor for the TextBox class.
   * @param x x-coordinate used for a rectangle
   * @param y y-coordinate used for a rectangle
   * @param w width used for a rectangle
   * @param h height used for a rectangle
   */
    TextBox(int x, int y, int w, int h) {
        X = x; Y = y; W = w; H = h;
    }

  /**
   * Draws the rectangle for the TextBox when the game starts.
   * @param w window for the user
   */
  @Override
  public void draw(Window w){
        w.noStroke();
        w.noFill();
        w.rect(X, Y, W, H);
        w.textSize(textSize);
        w.text(Text, X + (w.textWidth("a") / 2), Y + textSize);
    }

  /**
   * Function to map keycodes to buttons for the textbox.
   * @param key checks which key was pressed
   * @param keyCode global variables that represents the last key pressed
   * @param w window for the user
   * @return true if the correct keycodes are pressed, 8 for backspace,
   * 10 is for enter key, and 32 for space. Returns false otherwise
   */
  @Override
  public boolean keyPressed(char key, int keyCode, Window w) {
        if (selected) {
            if (keyCode == 8) {
                backspace();
            } else if (keyCode == 32) {
                addText(' ', w);
            } else if (keyCode == 10) {
                return true;
            } else {
                // CHECK IF THE KEY IS A LETTER OR A NUMBER
                boolean isKeyCapitalLetter = (key >= 'A' && key <= 'Z');
                boolean isKeySmallLetter = (key >= 'a' && key <= 'z');
                boolean isKeyNumber = (key >= '0' && key <= '9');

                if (isKeyCapitalLetter || isKeySmallLetter || isKeyNumber) {
                    addText(key, w);
                }
            }
        }
        return false;
    }

  /**
   * Adds text into the TextBox when the user inputs it, increases the size of the
   * TextBox as long as the text is within the limit of the TextBox width.
   * @param text characters entered
   * @param w window for the user
   */
  @Override
  public void addText(char text, Window w) {
        // IF THE TEXT WIDTH IS IN BOUNDARIES OF THE TEXTBOX
        if (w.textWidth(Text + text) < W) {
            Text += text;
            TextLength++;
        }
    }

  /**
   * Used to delete characters in the TextBox.
   */
  private void backspace() {
        if (TextLength - 1 >= 0) {
            Text = Text.substring(0, TextLength - 1);
            TextLength--;
        }
    }

  /**
   * Checks if characters entered exceeds the limit of the TextBox.
   * @param x x-coordinate used for the rectangle of the TextBox
   * @param y y-coordinate used for the rectangle of the TextBox
   * @return true if the text exceeds the TextBox, false otherwise
   */
    private boolean overBox(int x, int y) {
        if (x >= X && x <= X + W) {
            if (y >= Y && y <= Y + H) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void pressed(int x, int y) {
        if (overBox(x, y)) {
            selected = true;
        } else {
            selected = false;
        }
    }
}