package org.asteroidGame;

import processing.core.PImage;

public class Laser implements ICollidable, IDrawable{
    PImage bot;
    float posX;
    float posY;
    float speed;
    float dir;
    float size;

  /**
   * Constructor for Laser class.
   * @param px position x
   * @param py position y
   * @param pDir position of direction
   * @param window window for the user
   */
  Laser(float px, float py, float pDir, Window window){
        posX = px;
        posY = py;
        speed = 5;
        dir = pDir - window.HALF_PI;
        posX += 25*window.cos(dir);
        posY += 25*window.sin(dir);
        size = 2;
        bot = window.loadImage("C:\\Users\\raisa\\IdeaProjects\\Java-Project\\src\\main\\java\\Resourses\\ShootingLaser.png");
    }

  /** Updates the speed in which the direction of the laser is flying.
   *
   * @param window window for the user
   */
  @Override
  public void Update(Window window){
        posX += speed*window.cos(dir);
        posY += speed*window.sin(dir);
    }

  /**
   * Render the image of the laser that the player fires.
   * @param window window for the user
   */
  @Override
  public void Render(Window window){
        window.push();
        window.noFill();
        window.stroke(255);
        window.strokeWeight(1);
        window.image(bot, posX-7, posY-7, 20, 20);
        window.pop();
    }

  /** Checks collision between an asteroid object and the laser,
   *  Hit box is made larger to make it easier for impact.
   *
   * @param a asteroid object
   * @param window window for the user
   * @return true if the asteroid is hit in the hit box (center of the asteroid)
   */
    @Override
    public boolean CheckCollision(Asteroid a, Window window){
        float d = window.dist(posX, posY, a.posX, a.posY);

        if(d < (a.size+size)*3/5){
            return true;
        }
        else {
            return false;
        }
    }
}