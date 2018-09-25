package com.yellowbytestudios.game.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.game.GameObject;
import com.yellowbytestudios.game.input.AndroidListener;
import com.yellowbytestudios.game.input.KeyboardListener;
import com.yellowbytestudios.game.input.PlayerController;
import com.yellowbytestudios.media.Assets;
import com.yellowbytestudios.media.Sounds;
import com.yellowbytestudios.utils.DeviceTypes;

public class Player extends GameObject {

    private static final float SPEED = 2050f;
    private static final float SIZE = 80;
    private static final String MOVING_LEFT = "left";
    private static final String MOVING_RIGHT = "right";
    private static final String MOVING_UP = "up";
    private static final String MOVING_DOWN = "down";
    private String moveDirection;

    private PlayerController playerController;
    private boolean controllerEnabled = true;
    private Sprite idleImage, movingImage;

    private boolean injured = false;
    private float injuredTimer = 0;
    private int blinkRate = 2;
    private int blinks = 0;
    private static final float injuredInterval = 1000f;
    private DashLines dashLines;

    public Player(float x, float y) {
        super(Assets.getSprite(Assets.PLAYER));
        this.setPos(x, y);
        this.setWidth(SIZE);
        this.setHeight(SIZE);
        idleImage = Assets.getSprite(Assets.PLAYER);
        movingImage = Assets.getSprite(Assets.PLAYER_MOVING);
        setupController();
        dashLines = new DashLines(this);
    }

    private void setupController() {
        playerController = MainGame.DEVICE.equals(DeviceTypes.ANDROID) ? new AndroidListener() : new KeyboardListener();
    }

    private void updateController() {
        if (controllerEnabled) {
            if (playerController.moveLeft()) {
                startMove(MOVING_LEFT, -90);
            } else if (playerController.moveRight()) {
                startMove(MOVING_RIGHT, 90);
            } else if (playerController.moveUp()) {
                startMove(MOVING_UP, 180);
            } else if (playerController.moveDown()) {
                startMove(MOVING_DOWN, 0);
            }
            playerController.resetMoving();
        }
    }

    private void startMove(String moveDirection, float rotation) {
        if (!isMoving()) {
            this.moveDirection = moveDirection;
            getSprite().setRotation(rotation);
            setImage(movingImage);
            dashLines.addDashLine();
            Sounds.play("sound/move.wav");
        }
    }

    public void onInjured() {
        if (!injured) {
            injured = true;
            Sounds.play("sound/hurt.wav");
        }
    }

    @Override
    public void onCollide() {
        resetMoving();
    }

    private void resetMoving() {
        moveDirection = null;
        setOldX(getX());
        setOldY(getY());
        setPos((Math.round(getX() / SIZE) * SIZE), (Math.round(getY() / SIZE)) * SIZE);
        setImage(idleImage);
        Sounds.stop("sound/move.wav");
        Sounds.play("sound/wall_hit.wav");
    }

    private void setImage(Sprite sprite) {
        float x = getX();
        float y = getY();
        Sprite currSprite = getSprite();
        float rotation = currSprite.getRotation();
        currSprite.set(sprite);
        currSprite.setX(x);
        currSprite.setY(y);
        currSprite.setRotation(rotation);
    }

    public boolean isMoving() {
        return moveDirection != null;
    }

    public void update(float delta) {
        super.update(delta);
        updateController();
        dashLines.update();
        float velocity = SPEED * delta;

        if (moveDirection != null) {
            if (moveDirection.equals(MOVING_LEFT)) {
                setX(getX() - velocity);
            } else if (moveDirection.equals(MOVING_RIGHT)) {
                setX(getX() + velocity);
            } else if (moveDirection.equals(MOVING_UP)) {
                setY(getY() + velocity);
            } else if (moveDirection.equals(MOVING_DOWN)) {
                setY(getY() - velocity);
            }
        }

        if (injured) {
            injuredTimer += MainGame.ticker * delta;
            if (injuredTimer > injuredInterval) {
                injured = false;
                injuredTimer = 0;
                setOpacity(1f);
            } else {
                if (blinks >= blinkRate) {
                    if (getOpacity() == 1) {
                        setOpacity(0f);
                    } else {
                        setOpacity(1f);
                    }
                    blinks = 0;
                }
                blinks++;
            }
        }
    }

    public void render(SpriteBatch sb, ShapeRenderer sr) {
        sb.end();
        dashLines.render(sr);
        sb.begin();
        super.render(sb);
    }

    public boolean isMovingHorizontal() {
        return moveDirection.equals(MOVING_LEFT) || moveDirection.equals(MOVING_RIGHT);
    }

    public void disableConroller() {
        controllerEnabled = false;
    }
}
