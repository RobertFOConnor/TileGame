package com.yellowbytestudios.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class UIImage extends UIElement {

    public UIImage(Texture image, float x, float y) {
        super(image, x, y);
    }

    @Override
    public void render(SpriteBatch sb, ShapeRenderer sr) {
        getSprite().draw(sb);
    }

    @Override
    public boolean checkTouch(Vector2 touch) {
        return false;
    }
}
