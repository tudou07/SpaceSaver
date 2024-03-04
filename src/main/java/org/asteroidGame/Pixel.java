package org.asteroidGame;

public class Pixel{
    float posX;
    float posY;
    float size;
    float direction;
    float speed;
    float life;

  /**
   * Constructor for the Pixel class.
   * @param x x-coordinate position
   * @param y y-coordinate position
   * @param window window for the user
   */
    Pixel(float x, float y, Window window){
        posX = x;
        posY =  y;
        direction = window.random(0, window.TWO_PI);
        speed = window.random(1/10, 1/2);
        life = window.floor(window.random(100, 200));
        size = window.random(2, 5);
    }

  /**
   * Updates the speed and direction of the position of Pixels once an asteroid is destroyed.
   * @param window window for the user
   * @return true if the pixel life is less than 1, false otherwise
   */
  public boolean Update(Window window){
        posX += speed * window.cos(direction);
        posY += speed * window.sin(direction);
        life--;
        if(life <= 0)return true;
        else return false;
    }

  /**
   * Renders pixels on screen.
   * @param window window for the user
   */
  public void Render(Window window){
        window.push();
        if(life > 0){
            window.fill(255, 255, 255, life);
            window.rect(posX, posY, size, size);
        }
        window.pop();
    }
}