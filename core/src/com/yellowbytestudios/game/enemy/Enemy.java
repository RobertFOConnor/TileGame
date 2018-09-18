package com.yellowbytestudios.game.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.yellowbytestudios.game.GameObject;
import com.yellowbytestudios.media.Assets;

public class Enemy extends GameObject {

    public static int SIZE = 80;
    public static int VERTICAL = 2;
    public static int HORIZONTAL = 1;
    private int direction;

    private int health = 3;
    private float speed = 280f;
    private boolean movingUp = false;
    private boolean movingLeft = false;

    public Enemy(float x, float y, int direction) {
        super(Assets.getSprite(Assets.ENEMY_BULLET));
        setPos(x, y);
        this.setWidth(SIZE);
        this.setHeight(SIZE);
        this.direction = direction;
        if (direction != HORIZONTAL) {
            getSprite().setRotation(90);
        }
    }

    public void update(float delta) {
        super.update(delta);
        if (direction == HORIZONTAL) {
            setPos(getX() + (movingLeft ? speed : -speed) * delta, getY());
        } else {
            setPos(getX(), getY() + (movingUp ? speed : -speed) * delta);
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void onCollide() {
        movingLeft = !movingLeft;
        movingUp = !movingUp;
        Sprite sprite = getSprite();
        sprite.rotate(180);
        float rotation = sprite.getRotation();
        if (rotation > 270) {
            sprite.setRotation(rotation - 360);
        }
    }
}
