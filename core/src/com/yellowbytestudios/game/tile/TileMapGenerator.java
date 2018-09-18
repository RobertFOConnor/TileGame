package com.yellowbytestudios.game.tile;

import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import java.util.ArrayList;

/**
 * Created by Robert on 05/03/16.
 */
public class TileMapGenerator {


    public static int SECTION_COUNT = 30;
    public static int SECTION_WIDTH = 10;
    private static int SECTION_HEIGHT = 15;
    private static int WIDTH = SECTION_WIDTH * SECTION_COUNT;
    private static int HEIGHT = SECTION_HEIGHT;

    private ArrayList<String> sections;

    public TiledMap getGeneratedMap() {

        TiledMap tiledMap = new TiledMap();
        MapLayers layers = tiledMap.getLayers();

        int tileSize = (int) TileManager.getTileSize();
        for (int i = 0; i < TileManager.layerCount; i++) {
            layers.add(new TiledMapTileLayer(WIDTH, HEIGHT, tileSize, tileSize));
        }
        TiledMapTileLayer wallLayer = (TiledMapTileLayer) layers.get(LayerNames.WALL_LAYER);

        sections = new ArrayList<String>();
        sections.add("tilemap/section_3.tmx");
        sections.add("tilemap/section_4.tmx");
        sections.add("tilemap/section_5.tmx");
        sections.add("tilemap/section_6.tmx");

        for (int i = 0; i < SECTION_COUNT; i++) {
            TiledMap sectionTiledMap = new TmxMapLoader().load(sections.get((int) (Math.random() * sections.size())));
            addSection(tiledMap, sectionTiledMap, 0, i * SECTION_WIDTH);
        }
        populateLayer(wallLayer, TileHelper.getCellByName(TileNames.WALL_TILE));

        return tiledMap;
    }

    /*
     * Populates the borders of the tile map layer with the given cell type.
     */
    private void populateLayer(TiledMapTileLayer layer, TiledMapTileLayer.Cell cell) {
        for (int row = 0; row < layer.getHeight(); row++) {
            for (int col = 0; col < layer.getWidth(); col++) {
                if (row == 0 || row == layer.getHeight() - 1 || col == 0 || col == layer.getWidth() - 1) {
                    layer.setCell(col, row, cell);
                }
            }
        }
    }

    /*
     * Adds a smaller tile map to part of a larger one.
     */
    private void addSection(TiledMap map, TiledMap section, int startRow, int startCol) {
        for (int layerIndex = 0; layerIndex < map.getLayers().size(); layerIndex++) {
            TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(layerIndex);
            TiledMapTileLayer sectionLayer = (TiledMapTileLayer) section.getLayers().get(layerIndex);
            for (int row = 0; row < sectionLayer.getHeight(); row++) {
                for (int col = 0; col < sectionLayer.getWidth(); col++) {
                    layer.setCell(startCol + col, startRow + row, sectionLayer.getCell(col, row));
                }
            }
        }
    }
}