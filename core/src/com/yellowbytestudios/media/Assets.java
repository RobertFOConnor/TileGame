package com.yellowbytestudios.media;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Assets {

    public static AssetManager manager = new AssetManager();
    private static HashMap<String, Class> assetMap = new HashMap<String, Class>();

    private static String ATLAS = "pack.atlas";

    public static final String ENEMY_BULLET = "bullet_guy";
    public static final String ENEMY_BLOAT = "bloat";
    public static final String ENEMY_BLOAT_BIG = "bloat_big";

    public static final String PLAYER = "player";
    public static final String PLAYER_MOVING = "player_move";

    public static final String COIN = "coin";
    public static final String EXIT = "exit";

    // Loads Assets
    public static void load() {

        assetMap.put(ATLAS, TextureAtlas.class);

        assetMap.put("tilemap/tilesheet.png", Texture.class);

        //Animation
        assetMap.put("puppet/puppet.png", Texture.class);
        assetMap.put("puppet/puppet_2.png", Texture.class);
        assetMap.put("puppet/puppet_3.png", Texture.class);
        assetMap.put("puppet/shop_bg.jpg", Texture.class);

        //Sound
        assetMap.put("sound/click.wav", Sound.class);
        assetMap.put("sound/laser.wav", Sound.class);
        assetMap.put("sound/explode.wav", Sound.class);
        assetMap.put("sound/move.wav", Sound.class);
        assetMap.put("sound/wall_hit.wav", Sound.class);
        assetMap.put("sound/level_success.wav", Sound.class);
        assetMap.put("sound/coin.wav", Sound.class);
        assetMap.put("sound/hurt.wav", Sound.class);

        //Music
        assetMap.put("music/grassland_643432.mp3", Music.class);

        Iterator it = assetMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            manager.load((String) pair.getKey(), (Class) pair.getValue());
            it.remove();
        }
    }

    public static Texture getTexture(String name) {
        return manager.get(name, Texture.class);
    }

    public static Sprite getSprite(String name) {
        return manager.get(ATLAS, TextureAtlas.class).createSprite(name);
    }

    public static void dispose() {
        manager.dispose();
    }

    public static boolean update() {
        return manager.update();
    }
}
