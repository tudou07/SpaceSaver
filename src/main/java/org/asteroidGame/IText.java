package org.asteroidGame;

public interface IText {
    void draw(Window w);

    boolean keyPressed(char key, int keyCode, Window w);

    void addText(char text, Window w);

    void pressed(int x, int y);
}
