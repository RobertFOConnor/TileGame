package com.yellowbytestudios.game.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.media.Assets;

public class BloatEnemy extends Enemy {

    private float timer = 0;
    private static final float inflateInterval = 1500f;
    private boolean bloated = false;
    private static Sprite smallImage = Assets.getSprite(Assets.ENEMY_BLOAT);
    private static Sprite bigImage = Assets.getSprite(Assets.ENEMY_BLOAT_BIG);
    private Rectangle bounds;

    public BloatEnemy(float x, float y) {
        super(x, y, SIZE, SIZE);
        getSprite().setRotation(0);
        getSprite().set(smallImage);
        setBounds();
    }

    @Override
    public void update(float delta) {
        setOldX(getX());
        setOldY(getY());
        setPos(getX(), getY());

        timer += MainGame.ticker * delta;
        if (timer > inflateInterval) {
            if (!bloated) {
                toggleBloatState(bigImage, 3);
                bloated = true;
            } else {
                toggleBloatState(smallImage, 1);
                bloated = false;
            }
        }
    }

    private void toggleBloatState(Sprite image, int scale) {
        getSprite().set(image);
        setScaleX(scale);
        setScaleY(scale);
        setBounds();
        timer = 0;
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    private void setBounds() {
        float scale = getScaleX();
        bounds = new Rectangle(
                getX() - (getWidth() * (scale - 1)) / 2,
                getY() - (getHeight() * (scale - 1)) / 2,
                getWidth() * scale,
                getHeight() * scale
        );
    }
}
