package com.yellowbytestudios.game.input;

import com.badlogic.gdx.Gdx;

public class AndroidListener implements PlayerController {

    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;

    public AndroidListener() {
        Gdx.input.setInputProcessor(new SwipeGesture(new SwipeGesture.DirectionListener() {

            @Override
            public void onUp() {
                resetMoving();
                movingUp = true;
            }

            @Override
            public void onRight() {
                resetMoving();
                movingRight = true;
            }

            @Override
            public void onLeft() {
                resetMoving();
                movingLeft = true;
            }

            @Override
            public void onDown() {
                resetMoving();
                movingDown = true;
            }
        }));
    }

    public void resetMoving() {
        movingUp = false;
        movingDown = false;
        movingLeft = false;
        movingRight = false;
    }

    @Override
    public boolean moveUp() {
        return movingUp;
    }

    @Override
    public boolean moveDown() {
        return movingDown;
    }

    @Override
    public boolean moveLeft() {
        return movingLeft;
    }

    @Override
    public boolean moveRight() {
        return movingRight;
    }
}
