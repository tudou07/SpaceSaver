package org.asteroidGame;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.data.IntList;

import java.util.ArrayList;

public class Window extends PApplet{

    PImage bot;
    PImage gameOver;
    Player player;
    ArrayList<TextBox> textboxes;
    ArrayList<Asteroid> asteroids;
    ArrayList<Star> stars;
    ArrayList<Pixel> pixels;
    ArrayList<Laser> lasers;
    StarsStraght[] menuStars = new StarsStraght[800];
    IntList astRemove;
    IntList laserRemove;
    IntList pixelRemove;
    int gameState; // 0 is start menu, 1 is the game, 2 is game over
    int score;
    int result = 0;
    int round; //Levels
    int roundTitleCounter;
    boolean notRoundOne;
    boolean send = false;
    float speed;
    int lowAsteroids = 5;
    int highAsteroids = 8;
    String msg = "";
    String name = "";

    /**
     * Setup is called everytime we open the game, gamestate is set to 0
     * so our game stays in the start menu.
     */
    public void setup() {
        background(0);
        frameRate(60);
        InstantiateVariables();//This gets call whenever we restart the game...
        gameState = 0;
        bot = loadImage("C:\\Users\\raisa\\IdeaProjects\\Java-Project\\src\\main\\java\\Resourses\\MenuSpaceShip.png");
        gameOver = loadImage("C:\\Users\\raisa\\IdeaProjects\\Java-Project\\src\\main\\java\\Resourses\\Game-Over-PNG-Image.png");
    }

    /**
     * Used for setup of our game when we run it. Instantiates all objects
     * in the setup whenever the game is restarted, or when the player
     * loses their lives.
     */
    public void InstantiateVariables() {
        player = Player.getInstance(this);
        asteroids = new ArrayList<Asteroid>();
        stars = new ArrayList<Star>();
        textboxes = new ArrayList<TextBox>();

        for (int i = 0; i < floor(random(6, 10)); i++) {
            asteroids.add(new Asteroid(this));
        }

        for (int i = 0; i < 100; i++) {
            stars.add(new Star(this));
        }

        for (int i = 0; i < menuStars.length; i++) {
            menuStars[i] = new StarsStraght(this);
        }

        TextBox message = new TextBox(0, 110, width, height);
        textboxes.add(message);

        lasers = new ArrayList<Laser>();
        pixels = new ArrayList<>();
        astRemove = new IntList();
        laserRemove = new IntList();
        pixelRemove = new IntList();

        score = 0;
        round = 1;
        notRoundOne = false;
        roundTitleCounter = 180;
    }


    public void settings() {
        size(800, 800);
    }

    /**
     * Draws objects needed for each gameState. gameState 0 draws in the stars flying in the background along with
     * a TextBox to enter the users name and a spaceship image. gameState 1 draws in objects that need to be rendered
     * like the player and asteroids amongst stars in the background with the score printed. gameState 2 draws in
     * stars for the background again a TextBox, and a game over image.
     */
    public void draw() {
        switch (gameState) {
            case 0 -> {
                push();
                speed = map(mouseX, 0, width, 0, 20);
                background(0);
                translate(width / 2, height / 2);
                for (int i = 0; i < menuStars.length; i++) {
                    menuStars[i].update(this);
                    menuStars[i].show(this);
                }
                for (TextBox t : textboxes) {
                    t.draw(this);
                }

                if (send) {
                    text(msg, (width - textWidth(msg)) / 2, 260);
                }

                textSize(32);
                fill(255);
                image(bot, -width / 3, -height / 3);
                text("Enter Your Name: ", width * -.30f, height * .177f);
                text("Press Enter", width * -.095f, height * .30f);
                pop();
                break;
            }
            case 1 -> {
                RoundUpdate();
                Update();
                Render();
                for (Star s : stars) {
                    s.display(this);
                    PVector dir = new PVector(2, 0);
                    s.move(dir, this);
                }
                result = score;
                break;
            }
            case 2 -> {
                push();
                background(0);
                for (Star s : stars) {
                    s.display(this);
                    PVector dir = new PVector(2, 0);
                    s.move(dir, this);
                }
                textSize(32);
                image(gameOver, width * .38f, height * .1f, 200, 200);
                text("You Scored: " + result, width * .35f, height * .5f);
                text("Press Enter to Play Again", width * .24f, height * .8f);
                pop();
                break;
            }
        }
    }

    /**
     * Once the first round is about to finish, the window is repopulated with 5-8 asteroids
     * into the window for round2.
     */
    public void RoundUpdate() {
        if (roundTitleCounter > 0) roundTitleCounter--;//If there is any timer subtract the timer
        else {
            roundTitleCounter = 0;
            notRoundOne = true;
        }

        //ABout to finish a round but not in first round...
        if (roundTitleCounter == 1 && notRoundOne) {
            for (int i = 0; i < floor(random(lowAsteroids, highAsteroids)); i++) {
                asteroids.add(new Asteroid(this));
            }
        }

        if (roundTitleCounter == 0 && asteroids.size() == 0) {
            roundTitleCounter = 180;
            round++;
        }
    }

    /**
     * Render for objects player, asteroid, pixels, lasers, and round/score counter and shows
     * the Score and name of the player.
     */
    public void Render() {
        background(0);

        if (roundTitleCounter > 0) {
            push();
            textSize(32);
            text("Round " + round, width * .4f, height * .4f);
            pop();
        }

        push();
        textSize(32);
        text("Score: " + score, width * .05f, height * .05f);
        text("Name: " + name, width * .75f, height * .05f);
        pop();
        player.Render(this);
        for (Asteroid asteroid : asteroids) {
            asteroid.Render(this);
        }

        for (Laser laser : lasers) {
            laser.Render(this);
        }

        for (Pixel pixel : pixels) {
            pixel.Render(this);
        }
    }


    /**
     * Updates each object as needed.
     * Player updates once a collision with asteroid takes place, updates gamestate to 2 (game over).
     * Checks collision between an asteroid object and the laser,
     * Hit box is made larger to make it easier for laser impact using the CheckCollision function in Laser class.
     * Laser gets removed and so does the asteroid. Upon collision a pixel takes the place of the
     * x and y position of where the asteroid was by getting the position of x and y.
     */
    public void Update() {
        player.Update();
        for (Asteroid asteroid : asteroids) {
            asteroid.Update(this);
            if (player.CheckCollision(asteroid, this)) {
                gameState = 2;
            }
        }

        for (Laser laser : lasers) {
            laser.Update(this);
        }

        for (int l = 0; l < lasers.size(); l++) {
            for (int a = 0; a < asteroids.size(); a++) {
                if (lasers.get(l).CheckCollision(asteroids.get(a), this)) {
                    laserRemove.append(l);
                    astRemove.append(a);
                    score += 100;
                    for (int n = 0; n < 15; n++) {
                        pixels.add(new Pixel(asteroids.get(a).posX, asteroids.get(a).posY, this));
                    }
                    break;
                }
            }
        }
        for (int i = 0; i < pixels.size(); i++) {
            if (pixels.get(i).Update(this)) {
                pixelRemove.append(i);
            }
        }

        /**
         * The clear() method is used to remove all the elements from the List container.
         *
         * Container isn't deleted, just emptied. Once Update() is called
         * gameState 1 (new game starts) , we can start a fresh game
         */
        for (int i = 0; i < laserRemove.size(); i++) {
            if (laserRemove.get(i) < lasers.size()) lasers.remove(laserRemove.get(i));
        }
        laserRemove.clear();
        for (int i = 0; i < astRemove.size(); i++) {
            if (astRemove.get(i) < asteroids.size()) asteroids.remove(astRemove.get(i));
        }
        astRemove.clear();
        for (int i = 0; i < pixelRemove.size(); i++) {
            if (pixelRemove.get(i) < pixels.size()) pixels.remove(pixelRemove.get(i));
        }
        pixelRemove.clear();
    }

    /**
     * Checks the keycodes pressed for each game state, in gamestate 0 in the menu screen it checks for
     * the TextBox, in gamestate 1, it checks for player rotation and laser fire from the player, in
     * gamestate 3, in gamestate 2, the end of the game, if the user presses "enter" (keycode 10) the game
     * resets and instantiates all variables needed for the setup.
     */
    public void keyPressed() {
        switch (gameState) {
            case 0 -> {
                for (TextBox t : textboxes) {
                    if (t.keyPressed(key, keyCode, this)) {
                        send = true;
                        msg = "Message is: " + textboxes.get(0).Text;
                        name = textboxes.get(0).Text;
                    }
                }
                if (keyCode == 10) gameState = 1;
                break;
            }
            case 1 -> {
                if (keyCode == 39) {//RIGHT
                    player.isRotating = -1;
                } else if (keyCode == 37) {//LEFT
                    player.isRotating = 1;
                }
                if (keyCode == 32) {
                    if (!player.shotFired) {
                        if (round <= 4) {
                            lasers.add(player.Fire(this, player.posX, player.posY, player.rotation));
                        }
                        else {
                            lasers.add(player.Fire(this, player.posX - 15, player.posY - 15, player.rotation));
                            lasers.add(player.Fire2(this, player.posX + 15, player.posY + 15, player.rotation));
                            lowAsteroids = 8;
                            highAsteroids = 11;
                            player.MAXSPEED = PI/40;
                        }
                        player.shotFired = true;
                    }
                }
                break;
            }
            case 2 -> {
                if (keyCode == 10) gameState = 1;
                InstantiateVariables();
                break;
            }
        }
    }

    /**
     * Checks once the key is no longer being pressed, if left or right key is released,
     * rotation for the player is stopped.
     */
    public void keyReleased() {
        switch (gameState) {
            case 0 -> {

                break;
            }
            case 1 -> {
                if (keyCode == 39) {
                    player.isRotating = 0;
                } else if (keyCode == 37) {
                    player.isRotating = 0;
                }
                if (keyCode == 32) {
                    if (player.shotFired) player.shotFired = false;
                }
                break;
            }
            case 2 -> {

                break;
            }
        }
    }

    /**
     * Allows you to click your mouse within the TextBox.
     */
    public void mousePressed() {
        for (TextBox t : textboxes) {
            t.pressed(mouseX, mouseY);
        }
    }

    /**
     * Test method for checking the window size.
     *
     * @param window window for the user
     * @return true if width and height match in test class
     */
    public boolean checkWindowSize(Window window) {
        if ((window.height == 360)
                && (window.width == 640)) {
            return true;
        } else {
            return false;
        }
    }
}

