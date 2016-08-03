package com.hr.highpriestess.game.systems.GameSystems;

/**
 * Created by Titas on 2016-07-27.
 */

import com.artemis.BaseSystem;

import com.artemis.managers.GroupManager;
import com.badlogic.gdx.Gdx;
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

    String TAG = MapSystem.class.getName();

    public TiledMap map;
    private int width;
    private int height;
    private Array<TiledMapTileLayer> layers;
    private boolean isSetup = false;
    private HashMap<String, String> maps = new HashMap<String, String>();
    CameraSystem cameraSystem;
    GroupManager groupManager;
    String activeMapName;
    EntitySpawnerSystem entitySpawnerSystem;
    EntityClearerSystem entityClearerSystem;


    public MapSystem(String startingMap) {
        this.activeMapName = startingMap;
    }


    public void addMap(String mapString, String name) {
        maps.put(name, mapString);
    }


    protected void initialize() {
        addMap("map1.tmx", "Monastery");
        addMap("map2.tmx", "Outside");
        setActiveMap(activeMapName);
        Gdx.app.debug(TAG, "Initialized with mapName " + activeMapName);
    }

    public void setActiveMap(String activeMapName) {


        Gdx.app.debug(TAG, "activeMap changed");

        cameraSystem.reset();
        String fileName = maps.get(activeMapName);
        map = new TmxMapLoader().load(fileName);

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

        entityClearerSystem.clearEntities();
        for (TiledMapTileLayer layer : layers) {

            for (int ty = 0; ty < height; ty++) {
                for (int tx = 0; tx < width; tx++) {
                    final TiledMapTileLayer.Cell cell = layer.getCell(tx, ty);
                    if (cell != null) {
                        final MapProperties properties = cell.getTile().getProperties();
                        if ( properties.containsKey("entity")) {
                            entitySpawnerSystem.spawnEntity(tx*G.CELL_SIZE, ty*G.CELL_SIZE, properties);
                            layer.setCell(tx, ty, null);
                        }
                        }
                    }
                }
            }
        Gdx.app.debug(TAG, "Setup Finished");
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

