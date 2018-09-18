package com.yellowbytestudios.game.item;

import com.yellowbytestudios.game.GameObject;
import com.yellowbytestudios.media.Assets;

public class Coin extends GameObject {

    public static int SIZE = 30;
    private boolean isCollected = false;
    private boolean isAnimating = false;
    private boolean animateHorizontal = true;

    public Coin(float x, float y) {
        super(Assets.getSprite(Assets.COIN));
        setPos(x, y);
        this.setWidth(SIZE);
        this.setHeight(SIZE);
    }

    @Override
    public void update(float delta) {
        if (isAnimating) {
            float scaleX = getScaleX();
            float scaleY = getScaleY();
            float wideSpeed = 40f * delta;
            float shortSpeed = 10f * delta;
            if (animateHorizontal) {
                setScale(scaleX - shortSpeed, scaleY + wideSpeed);
            } else {
                setScale(scaleX + wideSpeed, scaleY - shortSpeed);
            }
            if (getScaleY() < 0 || getScaleX() < 0) {
                isAnimating = false;
            }
        }
    }

    public void startAnimation(boolean animateHorizontal) {
        this.animateHorizontal = animateHorizontal;
        isCollected = true;
        isAnimating = true;
    }

    public boolean isCollected() {
        return isCollected;
    }

    public boolean isAnimating() {
        return isAnimating;
    }
}