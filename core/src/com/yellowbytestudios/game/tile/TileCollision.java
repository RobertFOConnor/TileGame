package com.yellowbytestudios.game.tile;

import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.game.GameObject;

import java.util.ArrayList;

public class TileCollision {

    public static void checkCollision(GameObject gameObject, ArrayList<Vector2[]> lines) {

        for (Vector2[] line : lines) {
            if (gameObject.getY() > line[1].y - gameObject.getHeight() && gameObject.getY() < line[0].y) {
                if (gameObject.getOldX() < gameObject.getX()) { //moving right
                    if (gameObject.getOldX() + gameObject.getWidth() <= line[0].x && gameObject.getX2() >= line[0].x) {
                        gameObject.setPos(line[0].x - gameObject.getWidth(), gameObject.getY());
                        gameObject.onCollide();
                    }
                } else if (gameObject.getOldX() > gameObject.getX()) {
                    if (gameObject.getOldX() >= line[0].x && gameObject.getX() <= line[0].x) {
                        gameObject.setPos(line[0].x, gameObject.getY());
                        gameObject.onCollide();
                    }
                }
            }

            if (gameObject.getX2() > line[0].x && gameObject.getX() < line[1].x) {
                if (gameObject.getOldY() < gameObject.getY()) { //moving up
                    if (gameObject.getY2() >= line[0].y && gameObject.getOldY() + gameObject.getHeight() <= line[0].y) {
                        gameObject.setPos(gameObject.getX(), line[0].y - gameObject.getHeight());
                        gameObject.onCollide();
                    }
                } else if (gameObject.getOldY() > gameObject.getY()) {
                    if (gameObject.getY() <= line[0].y && gameObject.getOldY() >= line[0].y) {
                        gameObject.setPos(gameObject.getX(), line[0].y);
                        gameObject.onCollide();
                    }
                }
            }
        }
    }
}


