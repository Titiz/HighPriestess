package com.hr.highpriestess.game.systems.GameSystems;

/**
 * Created by Titas on 2016-07-27.
 */

import com.artemis.BaseSystem;

import com.artemis.managers.GroupManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;
import com.hr.highpriestess.game.systems.GameSystems.Abstract.MapSystem;
import com.hr.highpriestess.game.systems.MenuSystems.CameraSystem;
import com.hr.highpriestess.game.util.MapSetupHolder;

import java.util.HashMap;


/**
 * Handles map loading.
 *
 * @author Daan van Yperen
 */

public class GameMapSystem extends MapSystem {

    String TAG = GameMapSystem.class.getName();


    private Array<TiledMapTileLayer> layers;
    private boolean isSetup = false;
    private HashMap<String, String> maps = new HashMap<String, String>();
    CameraSystem cameraSystem;
    GroupManager groupManager;
    String activeMapName;
    GameEntitySpawnerSystem gameEntitySpawnerSystem;
    GameEntityClearerSystem gameEntityClearerSystem;


    public GameMapSystem(String startingMap) {
        this.activeMapName = startingMap;
    }


    public void addMap(String mapString, String name) {
        maps.put(name, mapString);
    }


    protected void initialize() {
        // add all of the maps and their reference names here
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


    @Override
    protected void processSystem() {

        if ( !isSetup )
        {
            isSetup = true;
            MapSetupHolder.setup(gameEntityClearerSystem,
                    gameEntitySpawnerSystem,
                    layers, width, height);
        }
    }

}

