package com.hr.highpriestess.game.systems.GameSystems;

/**
 * Created by Titas on 2016-07-27.
 */

import com.artemis.BaseSystem;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapImageLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.components.Game.Tracker.SpawnGateTracker;
import com.hr.highpriestess.game.systems.GameSystems.Abstract.EntityClearerSystem;
import com.hr.highpriestess.game.systems.GameSystems.Abstract.EntitySpawnerSystem;
import com.hr.highpriestess.game.systems.GameSystems.Abstract.MapSystem;
import com.hr.highpriestess.game.systems.MenuSystems.AssetSystem;
import com.hr.highpriestess.game.systems.MenuSystems.CameraSystem;
import com.hr.highpriestess.game.util.MapSetupHolder;

import java.util.HashMap;


/**
 * Handles map loading.
 *
 * Based on implementation of Daan van Yperen
 */

public class GameMapSystem extends MapSystem {

    String TAG = GameMapSystem.class.getName();


    private Array<TiledMapTileLayer> layers;
    private boolean isSetup = false;
    private HashMap<String, String> maps = new HashMap<String, String>();
    CameraSystem cameraSystem;
    TagManager tagManager;
    String activeMapName;
    GameEntitySpawnerSystem gameEntitySpawnerSystem;
    GameEntityClearerSystem gameEntityClearerSystem;






    public GameMapSystem(String startingMap) {
        this.activeMapName = startingMap;
    }





    protected void initialize() {
        setActiveMap(activeMapName);
        Gdx.app.debug(TAG, "Initialized with mapName " + activeMapName);
    }

    public void setActiveMap(String activeMapName) {
        Gdx.app.debug(TAG, "activeMap changed");
        Gdx.app.debug(TAG, "activeMap is now " + activeMapName);

        cameraSystem.reset();

        map = G.assetSystem.assetManager.get(activeMapName);

        layers = new Array<TiledMapTileLayer>();
        for ( MapLayer rawLayer : map.getLayers() )
        {
            if (rawLayer.getClass() == TiledMapImageLayer.class) continue;
            Gdx.app.debug(TAG, rawLayer.getClass().toString());
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
            setup();
            isSetup = true;
        }
    }

    private void setup() {

        if (tagManager.getEntity("tracker") == null) {              // If tracker is null, map was never created
            Gdx.app.debug(TAG, "tracker Entity is about to spawn"); // So NO need to clear entities.
            gameEntitySpawnerSystem.spawnEntity("tracker");
        } else {
            Gdx.app.debug(TAG, "Entities about to be cleared");
            gameEntityClearerSystem.clearEntities(); // Otherwise we just need to clear entities
        }

        Entity tracker = tagManager.getEntity("tracker");

        MapSetupHolder.setup(gameEntitySpawnerSystem,
                layers, width, height, tracker);
    }
}

