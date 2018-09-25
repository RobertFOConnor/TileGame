package com.yellowbytestudios.game.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class LeftRightEnemy extends Enemy {

    public static int SIZE = 80;
    private float speed = 400f;
    private boolean movingLeft = false;

    public LeftRightEnemy(float x, float y) {
        super(x, y, SIZE, SIZE);
    }

    public void update(float delta) {
        super.update(delta);
        setPos(getX() + (movingLeft ? speed : -speed) * delta, getY());
    }

    @Override
    public void onCollide() {
        movingLeft = !movingLeft;
        Sprite sprite = getSprite();
        sprite.rotate(180);
        float rotation = sprite.getRotation();
        if (rotation > 270) {
            sprite.setRotation(rotation - 360);
        }
    }
}
