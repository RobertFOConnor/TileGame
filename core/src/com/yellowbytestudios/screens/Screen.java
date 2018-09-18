package com.yellowbytestudios.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.ui.UIElement;

import java.util.ArrayList;

public abstract class Screen {

    protected OrthographicCamera camera;
    private FitViewport viewport;
    protected ArrayList<UIElement> UIElements;

    public void create() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(MainGame.WIDTH, MainGame.HEIGHT, camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();

        UIElements = new ArrayList<UIElement>();
    }

    public void update(float delta) {
        if (Gdx.input.justTouched()) {
            Vector2 touch = getTouchPos();
            for (UIElement uiElement : UIElements) {
                if (uiElement.checkTouch(touch)) {
                    break;
                }
            }
        }
    }

    public Vector2 getTouchPos() {
        Vector3 rawtouch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(rawtouch);
        return new Vector2(rawtouch.x, rawtouch.y);
    }

    public void render(SpriteBatch sb, ShapeRenderer sr) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        for (UIElement uiElement : UIElements) {
            uiElement.render(sb, sr);
        }
        sb.end();
    }

    public void resize(int w, int h) {
        viewport.update(w, h);
        camera.update();
    }

    public void dispose() {
        UIElements.clear();
        UIElements = null;
        camera = null;
        viewport = null;
    }

    public abstract void show();

    public abstract void hide();

    public abstract void pause();

    public abstract void resume();

    public abstract void goBack();

    public OrthographicCamera getCamera() {
        return camera;
    }
}
