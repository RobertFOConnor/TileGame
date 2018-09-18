package com.yellowbytestudios.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.game.player.Player;

import static com.yellowbytestudios.game.tile.TileMapGenerator.SECTION_COUNT;

public class GameCamera {

    private OrthographicCamera camera;
    private boolean isCameraShaking = false;
    private int shakeAmount = 3;
    private int cameraShaking = 0;
    private static final float zoomInLimit = 0.6f;
    private static final float zoomSpeed = 10f;
    private boolean isZoomingIn = false;

    // TODO set bounds according to tilemap dimensions.
    private boolean useBounds = false;
    private int LEFT_BOUND = MainGame.WIDTH / 2;
    private int RIGHT_BOUND = 80 * SECTION_COUNT * 10 - MainGame.WIDTH / 2;
    private int TOP_BOUND = 80 * 15 - MainGame.HEIGHT / 2;
    private int BOTTOM_BOUND = MainGame.HEIGHT / 2;

    public GameCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, MainGame.WIDTH, MainGame.HEIGHT);
    }

    public void update(Player player, float delta) {

        followPlayer(player, delta);
        checkBounds();

        if (isCameraShaking && cameraShaking < shakeAmount) {
            //camera.position.set(cameraX - 8 + (int) (Math.random() * 16), cameraY - 8 + (int) (Math.random() * 16), 0);
            cameraShaking++;
            if (cameraShaking > 3) {
                cameraShaking = 0;
                isCameraShaking = false;
            }
        }

        if (isZoomingIn && camera.zoom > zoomInLimit) {
            camera.zoom -= zoomSpeed * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.Y)) {
            if (camera.zoom < 4f) {
                camera.zoom += zoomSpeed * delta;
            }
        } else {
            if (camera.zoom > 1f) {
                camera.zoom -= zoomSpeed * delta;
                if (camera.zoom < 1f) {
                    camera.zoom = 1f;
                }
            }
        }
        camera.update();
    }

    private void followPlayer(Player player, float delta) {
        float targetX = player.getX();
        float targetY = player.getY();
        float cameraX = camera.position.x;
        float cameraY = camera.position.y;
        float SPEEDX = delta * (Math.abs(targetX - cameraX) * 3f);
        float SPEEDY = delta * (Math.abs(targetY - cameraY) * 5f);
        float newX = newCamPos(cameraX, targetX, SPEEDX);
        float newY = newCamPos(cameraY, targetY, SPEEDY);
        camera.position.set(newX, newY, 0);
    }

    private void checkBounds() {
        float x = camera.position.x;
        float y = camera.position.y;
        if (useBounds) {
            if (x < LEFT_BOUND) {
                setX(LEFT_BOUND);
            } else if (x > RIGHT_BOUND) {
                setX(RIGHT_BOUND);
            }

            if (y < BOTTOM_BOUND) {
                setY(BOTTOM_BOUND);
            } else if (y > TOP_BOUND) {
                setY(TOP_BOUND);
            }
        }
    }

    public void setX(float x) {
        camera.position.set(x, camera.position.y, 0);
    }

    public void setY(float y) {
        camera.position.set(camera.position.x, y, 0);
    }

    private float newCamPos(float camPos, float target, float speed) {
        if (camPos < target - speed) {
            return camPos + speed;
        } else if (camPos > target + speed) {
            return camPos - speed;
        } else {
            return target;
        }
    }

    public void startCameraShake() {
        isCameraShaking = true;
        cameraShaking = 0;
    }

    public void startZoomIn() {
        isZoomingIn = true;
    }

    public Matrix4 getCombined() {
        return camera.combined;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
