package org.asteroidGame;

import processing.core.PVector;

public class Star{

    public static final int max_weight = 7;
    PVector pos;
    float size;
    float colour;
    int margin = 50;
    float speed;
    int TOP_SPEED = 1;

  /**
   * Constructor for the Star class.
   * @param window window for the user
   */
  Star(Window window){
        this.pos = new PVector(window.random(window.width), window.random(window.height));
        this.size = window.random(max_weight);
        this.speed = window.map(this.size, 0, max_weight, 1,TOP_SPEED);
        this.colour = (255 / max_weight) * this.size;
    }

  /**
   * Displays the stars in the background when the game is in play and when the game is over once the
   * player has collided.
   * @param window window for the user
   */
  public void display(Window window){
        window.stroke(this.colour, this.colour, this.colour, this.colour);
        window.strokeWeight(this.size);
        window.point(this.pos.x, this.pos.y);
    }

  /**
   * Moves the stars on the screen in the background as the game is in effect.
   * @param direction direction for the stars to move
   * @param window window for the user
   */
  void move(PVector direction, Window window){
        direction.mult(this.speed);
        this.pos.add(direction);

        if (this.pos.x > window.width + this.margin){
            this.pos.x = -this.margin;
        } else if (this.pos.x < 0 - this.margin){
            this.pos.x = window.width + this.margin;
        }

        if (this.pos.y > window.height + this.margin){
            this.pos.y = -this.margin;
        } else if (this.pos.y < 0 - this.margin){
            this.pos.y = window.height + this.margin;
        }
    }
}