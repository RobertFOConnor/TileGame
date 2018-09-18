package com.yellowbytestudios.game.item;

import com.yellowbytestudios.game.GameObject;
import com.yellowbytestudios.game.tile.TileManager;
import com.yellowbytestudios.media.Assets;

public class Exit extends GameObject {

    public Exit(float x, float y) {
        super(Assets.getSprite(Assets.EXIT));
        setPos(x, y);
        this.setWidth(TileManager.getTileSize());
        this.setHeight(TileManager.getTileSize());
    }
}
