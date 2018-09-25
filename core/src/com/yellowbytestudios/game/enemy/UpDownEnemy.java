package com.yellowbytestudios.game.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class UpDownEnemy extends Enemy {

    public static int SIZE = 80;
    private float speed = 400f;
    private boolean movingUp = false;

    public UpDownEnemy(float x, float y) {
        super(x, y, SIZE, SIZE);
    }

    public void update(float delta) {
        super.update(delta);
        setPos(getX(), getY() + (movingUp ? speed : -speed) * delta);
    }

    @Override
    public void onCollide() {
        movingUp = !movingUp;
        Sprite sprite = getSprite();
        sprite.rotate(180);
        float rotation = sprite.getRotation();
        if (rotation > 270) {
            sprite.setRotation(rotation - 360);
        }
    }
}