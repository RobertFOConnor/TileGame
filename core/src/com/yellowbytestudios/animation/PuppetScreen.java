package com.yellowbytestudios.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.media.Assets;
import com.yellowbytestudios.screens.Screen;

public class PuppetScreen extends Screen {

    private Texture background;
    private Sprite puppet;

    @Override
    public void create() {
        super.create();
        puppet = new Sprite(Assets.getTexture("puppet/puppet.png"));
        background = Assets.getTexture("puppet/shop_bg.jpg");
        Cursor customCursor = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("puppet/cursor.png")), 0, 0);
        Gdx.graphics.setCursor(customCursor);
    }

    @Override
    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            puppet.setTexture(Assets.getTexture("puppet/puppet_2.png"));
        } else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            puppet.setTexture(Assets.getTexture("puppet/puppet_3.png"));
        } else {
            puppet.setTexture(Assets.getTexture("puppet/puppet.png"));
        }

        Vector2 cursorPos = getTouchPos();
        puppet.setX(cursorPos.x - puppet.getWidth() / 2);
        puppet.setY(cursorPos.y - puppet.getHeight() / 2);
        if (cursorPos.y > puppet.getHeight() / 2) {
            puppet.setY(0);
        }
    }

    @Override
    public void render(SpriteBatch sb, ShapeRenderer sr) {
        Vector2 cursorPos = getTouchPos();
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0, 0, MainGame.WIDTH, MainGame.HEIGHT);
        puppet.draw(sb);
        sb.end();
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void goBack() {

    }
}
