package com.yellowbytestudios.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.game.GameManager;
import com.yellowbytestudios.media.Fonts;

public class GameScreen extends Screen {

    private GameManager gameManager;
    private BitmapFont UIFont;

    @Override
    public void create() {
        super.create();
        gameManager = new GameManager();
        UIFont = Fonts.getFont(Fonts.FUTURA);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        gameManager.update(delta);
        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            ScreenManager.setScreen(new GameScreen());
        }
    }

    public void renderUI(SpriteBatch sb, ShapeRenderer sr) {
        sb.end();
        sr.setProjectionMatrix(camera.combined);
        sr.setColor(Color.BLACK);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.rect(0, 0, 350, 100);
        sr.end();
        sb.begin();
        UIFont.draw(sb, gameManager.getScore() + "", 20, 70);
        if (gameManager.isLevelComplete()) {
            UIFont.draw(sb, "Victory!", MainGame.WIDTH / 2, MainGame.HEIGHT / 2);
        }
    }

    @Override
    public void render(SpriteBatch sb, ShapeRenderer sr) {
        gameManager.render(sb, sr); // 1. render the game
        super.render(sb, sr); // 2. render the GUI
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        renderUI(sb, sr);
        sb.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        gameManager.dispose();
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
