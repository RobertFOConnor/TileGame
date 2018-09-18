package com.yellowbytestudios.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.game.enemy.Enemy;
import com.yellowbytestudios.game.item.Coin;
import com.yellowbytestudios.game.player.Player;
import com.yellowbytestudios.game.tile.LayerNames;
import com.yellowbytestudios.game.tile.TileManager;
import com.yellowbytestudios.media.Sounds;
import com.yellowbytestudios.screens.GameScreen;
import com.yellowbytestudios.screens.ScreenManager;

public class GameManager {

    private GameCamera camera;
    private TileManager tileManager;
    private Player player;
    private GameObjectArray exits, coins, enemies;
    private float maxScore;
    private float score = 0;
    private boolean paused = false;
    private boolean gameOver = false;
    private boolean levelComplete = false;
    private DeathWave deathWave;

    public GameManager() {
        camera = new GameCamera();
        tileManager = new TileManager(camera);
        exits = tileManager.getObjects(LayerNames.EXIT_LAYER);
        coins = tileManager.getObjects(LayerNames.COIN_LAYER);
        enemies = tileManager.getObjects(LayerNames.ENEMY_LAYER);
        Vector2 playerStartPos = tileManager.getPlayerStartPos();
        player = new Player(playerStartPos.x, playerStartPos.y);
        camera.setX(player.getX());
        camera.setY(player.getY());
        camera.update(player, 0);

        maxScore = coins.size();
        deathWave = new DeathWave(0, 0, 0, 15 * 80, 300f);
    }

    public void update(float delta) {
        if (!paused && !gameOver) {
            player.update(delta);
            updateEnemies(delta);
            updateCoins(delta);
            tileManager.update(player, enemies);
            //updateDeathWave(delta);
            camera.update(player, delta);
            checkGameOver();
        }
    }

    private void updateDeathWave(float delta) {
        deathWave.update(delta);
        if (deathWave.getBounds().overlaps(player.getBounds())) {
            gameOver = true;
        }
    }

    private void updateCoins(float delta) {
        for (int i = 0; i < coins.size(); i++) {
            Coin coin = (Coin) coins.get(i);
            coin.update(delta);
            if (player.overlapsObject(coin) && !coin.isCollected()) {
                score++;
                coin.startAnimation(player.isMovingHorizontal());
                Gdx.input.vibrate(20);
                Sounds.play("sound/coin.wav");
            }
            if (coin.isCollected() && !coin.isAnimating()) {
                coins.remove(i);
            }
        }
        coins.doRemovals();
    }

    private void updateEnemies(float delta) {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = (Enemy) enemies.get(i);
            enemy.update(delta);
            if (player.overlapsObject(enemy)) {
                Sounds.play("sound/explode.wav");
                gameOver = true;
            }
        }
    }

    private void checkGameOver() {
        if (exits != null && !levelComplete) {
            for (int i = 0; i < exits.size(); i++) {
                if (player.overlapsObject(exits.get(i))) {
                    Sounds.play("sound/level_success.wav");
                    levelComplete = true;
                    player.disableConroller();
                    camera.startZoomIn();
                }
            }
        }
        if (gameOver) {
            ScreenManager.setScreen(new GameScreen());
        }
    }


    public void render(SpriteBatch sb, ShapeRenderer sr) {
        tileManager.render();

        sb.setProjectionMatrix(camera.getCombined());
        sr.setProjectionMatrix(camera.getCombined());

        sb.begin();
        exits.render(sb);
        coins.render(sb);
        player.render(sb, sr);
        enemies.render(sb);
        sb.end();
        deathWave.render(sr);
    }

    public void togglePause() {
        paused = !paused;
    }

    public int getScore() {
        return (int) (score / maxScore * 100);
    }

    public boolean isLevelComplete() {
        return levelComplete;
    }

    public void dispose() {
        camera = null;
        tileManager.dispose();
        tileManager = null;
        coins = null;
        exits = null;
        player = null;
        gameOver = true;
    }
}
