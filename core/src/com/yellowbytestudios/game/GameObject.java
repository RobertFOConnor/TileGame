package com.yellowbytestudios.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {

    private int id;
    private String name;
    private Sprite sprite;
    private Rectangle bounds;
    private float x, y;
    private float width;
    private float height;
    private float oldX;
    private float oldY;
    private float scaleX = 1, scaleY = 1;
    private float originX, originY;
    private float opacity = 1f;

    public GameObject(Sprite sprite) {
        this.sprite = sprite;
        bounds = new Rectangle();
        originX = sprite.getOriginX();
        originY = sprite.getOriginY();
    }

    public void update(float delta) {
        setOldX(getX());
        setOldY(getY());
    }

    public void render(SpriteBatch sb) {
        sb.setColor(1, 1, 1, opacity);
        sb.draw(sprite, x, y, originX, originY, width, height, scaleX, scaleY, sprite.getRotation());
        sb.setColor(1, 1, 1, 1f);
    }

    public Rectangle getBounds() {
        bounds.set(x, y, width, height);
        return bounds;
    }

    public void onCollide() {

    }

    public boolean overlapsObject(GameObject gameObject) {
        return getBounds().overlaps(gameObject.getBounds());
    }

    public boolean checkTouch(Vector2 touch) {
        return getBounds().contains(touch);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getX() {
        return x;
    }

    public float getX2() {
        return x + width;
    }

    public float getY() {
        return y;
    }

    public float getY2() {
        return y + height;
    }

    public float getCenterX() {
        return x + getWidth() / 2;
    }


    public float getCenterY() {
        return y + getHeight() / 2;
    }

    public void setPos(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Texture getImage() {
        return sprite.getTexture();
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setOldX(float oldX) {
        this.oldX = oldX;
    }

    public void setOldY(float oldY) {
        this.oldY = oldY;
    }

    public float getOldX() {
        return oldX;
    }

    public float getOldY() {
        return oldY;
    }

    public float getScaleX() {
        return scaleX;
    }

    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
    }

    public float getScaleY() {
        return scaleY;
    }

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
    }

    public void setScale(float scaleX, float scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }

    public float getOpacity() {
        return opacity;
    }
}
