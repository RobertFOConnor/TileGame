package com.yellowbytestudios.game.tile;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.yellowbytestudios.game.GameObjectArray;
import com.yellowbytestudios.game.enemy.BloatEnemy;
import com.yellowbytestudios.game.enemy.Enemy;
import com.yellowbytestudios.game.enemy.LeftRightEnemy;
import com.yellowbytestudios.game.item.Coin;
import com.yellowbytestudios.game.item.Exit;

public class TileObjects {

    private static int tileSize = (int) TileManager.getTileSize();

    private static float getPos(int orientation, int size) {
        return orientation * tileSize + tileSize / 2 - size / 2;
    }

    public static GameObjectArray createObjectArray(TileManager tm, int layerIndex) {
        GameObjectArray array = new GameObjectArray();
        if (layerIndex < tm.getLayerCount()) {
            TiledMapTileLayer layer = tm.getLayer(layerIndex);

            for (int col = 0; col < layer.getWidth(); col++) {
                for (int row = 0; row < layer.getHeight(); row++) {
                    TiledMapTileLayer.Cell cell = layer.getCell(col, row);
                    if (cell != null) {
                        switch (layerIndex) {
                            case LayerNames.COIN_LAYER:
                                array.add(new Coin(getPos(col, Coin.SIZE), getPos(row, Coin.SIZE)));
                                break;
                            case LayerNames.ENEMY_LAYER:
                                if ((int) (Math.random() * 2) == 0) {
                                    switch (cell.getTile().getId()) {
                                        case TileNames.ENEMY_BULLET:
                                            array.add(new LeftRightEnemy(getPos(col, Enemy.SIZE), getPos(row, Enemy.SIZE)));
                                            break;
                                        case TileNames.ENEMY_BLOAT:
                                            array.add(new BloatEnemy(getPos(col, Enemy.SIZE), getPos(row, Enemy.SIZE)));
                                            break;
                                    }
                                }
                                break;
                            case LayerNames.EXIT_LAYER:
                                switch (cell.getTile().getId()) {
                                    case TileNames.EXIT:
                                        array = new GameObjectArray();
                                        array.add(new Exit(getPos(col, tileSize), getPos(row, tileSize)));
                                        break;
                                    case TileNames.START_POS:
                                        if (tm.getPlayerStartPos().x == 0) {
                                            tm.setPlayerStartPos(getPos(col, tileSize), getPos(row, tileSize));
                                        }
                                        break;
                                }
                        }
                    }
                }
            }
        }
        return array;
    }
}
