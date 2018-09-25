package com.yellowbytestudios.game.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class KeyboardListener implements PlayerController {
    @Override
    public boolean moveUp() {
        return Gdx.input.isKeyJustPressed(Input.Keys.W);
    }

    @Override
    public boolean moveDown() {
        return Gdx.input.isKeyJustPressed(Input.Keys.S);
    }

    @Override
    public boolean moveLeft() {
        return Gdx.input.isKeyJustPressed(Input.Keys.A);
    }

    @Override
    public boolean moveRight() {
        return Gdx.input.isKeyJustPressed(Input.Keys.D);
    }

    @Override
    public void resetMoving() {

    }


}
