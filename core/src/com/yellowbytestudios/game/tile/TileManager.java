package com.yellowbytestudios.game.tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.game.enemy.Enemy;
import com.yellowbytestudios.game.GameCamera;
import com.yellowbytestudios.game.GameObjectArray;
import com.yellowbytestudios.game.player.Player;

import java.util.ArrayList;

import static com.yellowbytestudios.game.tile.LayerNames.DECORATION_LAYER;
import static com.yellowbytestudios.game.tile.LayerNames.FLOOR_LAYER;
import static com.yellowbytestudios.game.tile.LayerNames.WALL_LAYER;


public class TileManager {

    private float playerStartX;
    private float playerStartY;
    private static final boolean DEBUG = false;
    private static final boolean ARCADE_MODE = false;
    private static final String MAP_FILE_NAME = "tilemap/level_1.tmx";
    private static int tileSize = 80;
    public static int layerCount = 5;

    private GameCamera gameCamera;
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;

    //Map props
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String TILE_SIZE = "tilewidth";

    private int mapWidth;
    private int mapHeight;

    private ArrayList<Vector2[]> lines;
    private ShapeRenderer sr;

    private int currentRoom = 0;
    private int roomWidth = TileMapGenerator.SECTION_WIDTH;

    private int[] renderLayers = new int[]{FLOOR_LAYER, WALL_LAYER, DECORATION_LAYER};

    public TileManager(GameCamera camera) {
        this.gameCamera = camera;
        tiledMap = ARCADE_MODE ? generateTiledMap() : loadTiledMap(MAP_FILE_NAME);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        sr = new ShapeRenderer();
        makeWalls(currentRoom, ARCADE_MODE ? roomWidth * 2 : 0);
    }

    public void makeWalls(int startCol, int roomWidth) {
        TileWallMaker tc = new TileWallMaker(startCol, roomWidth);
        lines = tc.createWalls(getLayer(WALL_LAYER));
    }

    private TiledMap loadTiledMap(String fileName) {
        return new TmxMapLoader().load(fileName);
    }

    private TiledMap generateTiledMap() {
        return new TileMapGenerator().getGeneratedMap();
    }

    public GameObjectArray getObjects(int layer) {
        return TileObjects.createObjectArray(this, layer);
    }

    public void update(Player player, GameObjectArray enemies) {
        if (player.isMoving()) {
            TileCollision.checkCollision(player, lines);

            if (ARCADE_MODE) {
                int currRoom = (int) (player.getX() / (tileSize * roomWidth));
                if (currentRoom != currRoom) { //room changed
                    makeWalls(currRoom * roomWidth, roomWidth * 2);
                    currentRoom = currRoom;
                }
            }
        }
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = (Enemy) enemies.get(i);
            TileCollision.checkCollision(enemy, lines);
        }
    }

    public Vector2 getPlayerStartPos() {
        return new Vector2(playerStartX, playerStartY);
    }

    public void setPlayerStartPos(float x, float y) {
        playerStartX = x;
        playerStartY = y;
    }

    public void render() {
        tiledMapRenderer.setView(gameCamera.getCamera());
        tiledMapRenderer.render(renderLayers);
        renderWallLines();
    }

    private void renderWallLines() {
        if (DEBUG) {
            sr.setProjectionMatrix(gameCamera.getCombined());
            sr.begin(ShapeRenderer.ShapeType.Filled);
            sr.setColor(Color.WHITE);
            for (Vector2[] v : lines) {
                sr.rectLine(v[0], v[1], 3); //DEBUG
            }
            sr.end();
        }
    }

    private void setupMapWidthHeight() {
        mapWidth = getMapProperty(WIDTH);
        mapHeight = getMapProperty(HEIGHT);
        tileSize = getMapProperty(TILE_SIZE);
    }

    private Integer getMapProperty(String property) {
        return tiledMap.getProperties().get(property, Integer.class);
    }

    public float getMapWidth() {
        return mapWidth * tileSize;
    }

    public float getMapHeight() {
        return mapHeight * tileSize;
    }

    public static float getTileSize() {
        return tileSize;
    }

    public int getLayerCount() {
        return tiledMap.getLayers().size();
    }

    public TiledMapTileLayer getLayer(int index) {
        return (TiledMapTileLayer) tiledMap.getLayers().get(index);
    }

    public void dispose() {
        tiledMap.dispose();
        lines.clear();
        sr.dispose();
        tiledMap = null;
        tiledMapRenderer = null;
        lines = null;
        sr = null;
    }
}
