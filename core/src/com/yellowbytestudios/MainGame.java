package com.yellowbytestudios;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.yellowbytestudios.animation.PuppetScreen;
import com.yellowbytestudios.media.Assets;
import com.yellowbytestudios.media.Fonts;
import com.yellowbytestudios.media.Sounds;
import com.yellowbytestudios.screens.GameScreen;
import com.yellowbytestudios.screens.ScreenManager;
import com.yellowbytestudios.utils.DeveloperTools;

public class MainGame extends ApplicationAdapter {

    public static String DEVICE;
    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;
    private boolean loaded = false;

    //Renderers
    private SpriteBatch sb;
    private ShapeRenderer sr;

    private DeveloperTools developerTools;
    public static final float ticker = 1 / 60f * 60000f;

    public MainGame(String device) {
        DEVICE = device;
    }

    @Override
    public void create() {
        Assets.load();
        Fonts.load();
        sr = new ShapeRenderer();
        sb = new SpriteBatch();
        developerTools = new DeveloperTools();
    }

    @Override
    public void render() {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor(0, 0, 0, 0);

        if (Assets.update() && !loaded) {
            loaded = true;
            ScreenManager.setScreen(new GameScreen());
            Sounds.setMusic("music/title.mp3");

        }

        if (loaded) {
            ScreenManager.getCurrentScreen().update(Gdx.graphics.getDeltaTime());
            ScreenManager.getCurrentScreen().render(sb, sr);
            //developerTools.renderOverlay(sb);
        }
    }

    @Override
    public void resize(int w, int h) {
        if (ScreenManager.getCurrentScreen() != null) {
            ScreenManager.getCurrentScreen().resize(w, h);
        }
    }

    @Override
    public void dispose() {
        sb.dispose();
        Assets.dispose();
    }
}
