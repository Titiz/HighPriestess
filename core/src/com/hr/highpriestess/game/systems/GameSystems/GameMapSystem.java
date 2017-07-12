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
import com.badlogic.gdx.maps.tiled.TiledMap;
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
    AssetSystem assetSystem;
    GameEntitySpawnerSystem gameEntitySpawnerSystem;
    GameEntityClearerSystem gameEntityClearerSystem;






    public GameMapSystem(String startingMap) {
        this.activeMapName = startingMap;
    }





    protected void initialize() {
        setActiveMap(activeMapName);
        Gdx.app.debug(TAG, "Initialized with mapName " + activeMapName);
    }

    private  void resetLastMap() {
        assetSystem.assetManager.unload(this.activeMapName);
        assetSystem.assetManager.load(this.activeMapName, TiledMap.class);
        assetSystem.assetManager.finishLoading();
    }

    public void setActiveMap(String activeMapName) {
        Gdx.app.debug(TAG, "activeMap changing");


        Gdx.app.debug(TAG, "resetting Camera ");
        cameraSystem.reset();

        Gdx.app.debug(TAG, "resetting old map with name: " + this.activeMapName);
        resetLastMap(); // We need to reset the map in order to be able to use it again for spawning entities

        Gdx.app.debug(TAG, "chaging the the activeMapName to: " + activeMapName);
        this.activeMapName = activeMapName;

        Gdx.app.debug(TAG, "retrieving new map with name: " + activeMapName);
        map = assetSystem.assetManager.get(activeMapName);


        Gdx.app.debug(TAG, "going through layers of " + activeMapName);
        layers = new Array<TiledMapTileLayer>();
        for ( MapLayer rawLayer : map.getLayers() )
        {
            if (rawLayer.getClass() == TiledMapImageLayer.class) continue;
            Gdx.app.debug(TAG, rawLayer.getClass().toString());
            layers.add((TiledMapTileLayer) rawLayer);
        }
        Gdx.app.debug(TAG, "done retrieving layers of " + activeMapName);

        Gdx.app.debug(TAG, "retrieving height and width of " + activeMapName);
        width = layers.get(0).getWidth();
        height = layers.get(0).getHeight();


        isSetup = false;
    }








    @Override
    protected void processSystem() {

        if ( !isSetup )
        {
            setup();
            assetSystem.assetManager.finishLoading();
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

        Gdx.app.debug(TAG, "Setting up new map " + activeMapName);

        MapSetupHolder.setup(gameEntitySpawnerSystem,
                layers, width, height, tracker);
    }
}

