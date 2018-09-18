package com.yellowbytestudios.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class DeathWave {

    private Rectangle bounds;
    private float speed;

    public DeathWave(float x, float y, float width, float height, float speed) {
        bounds = new Rectangle(x, y, width, height);
        this.speed = speed;
    }

    public void update(float delta) {
        bounds.setWidth(bounds.getWidth() + speed * delta);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void render(ShapeRenderer sr) {
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.rect(bounds.x, bounds.y, bounds.getWidth(), bounds.getHeight());
        sr.end();
    }
}
