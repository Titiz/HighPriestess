package com.hr.highpriestess.game.systems.GameSystems;

/**
 * Created by Titas on 2016-07-27.
 */

import com.artemis.BaseSystem;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.systems.MenuSystems.CameraSystem;
import com.hr.highpriestess.game.util.EntityMakerGame;

import java.util.HashMap;


/**
 * Handles map loading.
 *
 * @author Daan van Yperen
 */

public class MapSystem extends BaseSystem {

    public TiledMap map;
    private int width;
    private int height;
    private Array<TiledMapTileLayer> layers;
    private boolean isSetup = false;
    private HashMap<String, TiledMap> maps = new HashMap<String, TiledMap>();
    CameraSystem cameraSystem;


    public MapSystem() {
    }


    public void addMap(String mapString, String name) {
        map = new TmxMapLoader().load(mapString);
        maps.put(name, map);
    }


    protected void initialize() {
        addMap("map1.tmx", "Monastery");
        addMap("map2.tmx", "Outside");
        String activeMapName = "Monastery";
        setActiveMap(activeMapName);
    }

    public void setActiveMap(String activeMapName) {
        cameraSystem.reset();
        map = maps.get(activeMapName);

        layers = new Array<TiledMapTileLayer>();
        for ( MapLayer rawLayer : map.getLayers() )
        {
            layers.add((TiledMapTileLayer) rawLayer);
        }
        width = layers.get(0).getWidth();
        height = layers.get(0).getHeight();


        isSetup = false;
    }

    /**
     * Spawn map entities.
     */


    protected void setup() {
        for (TiledMapTileLayer layer : layers) {

//            private HashMap<String, TiledMapTileLayer> layerIndex = new HashMap<String, TiledMapTileLayer>();
//            layerIndex.put(layer.getName(), layer);

            for (int ty = 0; ty < height; ty++) {
                for (int tx = 0; tx < width; tx++) {
                    final TiledMapTileLayer.Cell cell = layer.getCell(tx, ty);
                    if (cell != null) {
                        final MapProperties properties = cell.getTile().getProperties();

                        if ( properties.containsKey("entity")) {
                            EntityMakerGame.createPlayer(G.gameWorld, tx*G.CELL_SIZE, ty*G.CELL_SIZE);
                            layer.setCell(tx, ty, null);
                        }
                    }
                }
            }
        }
    }



    @Override
    protected void processSystem() {

        if ( !isSetup )
        {
            isSetup = true;
            setup();
        }
    }

}

