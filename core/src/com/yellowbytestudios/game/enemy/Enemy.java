package com.yellowbytestudios.game.enemy;

import com.yellowbytestudios.game.GameObject;
import com.yellowbytestudios.media.Assets;

public abstract class Enemy extends GameObject {

    public static int SIZE = 80;

    private int health = 3;

    public Enemy(float x, float y, float w, float h) {
        super(Assets.getSprite(Assets.ENEMY_BULLET));
        setPos(x, y);
        this.setWidth(w);
        this.setHeight(h);
    }

    public void update(float delta) {
        super.update(delta);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
