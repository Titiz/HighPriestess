package com.hr.highpriestess.game.systems.GameSystems;

/**
 * Created by Titas on 2016-07-27.
 */

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapImageLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Array;
import com.hr.highpriestess.game.components.Game.Tracker.NeighborMapTracker;
import com.hr.highpriestess.game.systems.GameSystems.Abstract.MapSystem;
import com.hr.highpriestess.game.systems.MenuSystems.AssetSystem;
import com.hr.highpriestess.game.systems.MenuSystems.CameraSystem;
import com.hr.highpriestess.game.util.MapSetupUtils;

import java.util.HashMap;
import java.util.HashSet;


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
    ComponentMapper<NeighborMapTracker> neighCm;
    String activeMapName;
    AssetSystem assetSystem;
    GameEntitySpawnerSystem gameEntitySpawnerSystem;
    GameEntityClearerSystem gameEntityClearerSystem;

    BackgroundAssetSystem backgroundAssetSystem;
    AnimationTrackingSystem animationTrackingSystem;






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

        Gdx.app.debug(TAG, "changing activeMapName of GameMapSytem to: " + activeMapName);
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

        backgroundAssetSystem.isSetup = false;
        animationTrackingSystem.isSetup = false;
        isSetup = false;
    }


    private void finishLoadingMapAssets(String mapName) {
        /** Used to make sure that assets necessary for the next level are loaded**/
        Gdx.app.debug(TAG, "finishing loading assets for map with name: " + mapName);
        NeighborMapTracker mapTracker = neighCm.get(tagManager.getEntity("tracker"));
          // We make sure that the map's assets were not already loaded.
        for (String resourceName : mapTracker.neighborMapAssets.get(mapName)) {
            if (!mapTracker.lastMapNeighborNames.contains(mapName)) {
                Gdx.app.debug(TAG, "Adding resource " + resourceName + " to queue");
                assetSystem.load(resourceName);}
            if (!assetSystem.assetManager.isLoaded(resourceName)) {
                Gdx.app.debug(TAG, "Loading resource " + resourceName);
                assetSystem.assetManager.finishLoadingAsset(resourceName);
                Gdx.app.debug(TAG, "Resource loaded " + resourceName);
            } else {
                Gdx.app.debug(TAG, "Resource already loaded " + resourceName);
            }
            assetSystem.assetManager.get(resourceName);
        }
        Gdx.app.debug(TAG, "Loading assets finished for map :" + mapName);
    }

    @Override
    protected void processSystem() {

        if ( !isSetup )
        {
            setup();
            finishLoadingMapAssets(activeMapName);
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


        Gdx.app.debug(TAG, "changing the activeMapName of the NeighborMapTracker to " + activeMapName);
        neighCm.get(tracker).activeMapName = activeMapName;


        Gdx.app.debug(TAG, "Setting up new map " + activeMapName);

        Gdx.app.log(TAG, "saving current neighbors to lastMapNeighbors");
        neighCm.get(tracker).lastMapNeighborNames =
                new HashSet<String>(neighCm.get(tracker).currentNeighborNames); //save the previous neighbors
        Gdx.app.log(TAG, "clearing current neighbors");
        neighCm.get(tracker).currentNeighborNames.clear();                      // and clear for new ones to be added

        Gdx.app.log(TAG, "adding active map to current neighbors");
        neighCm.get(tracker).currentNeighborNames.add(activeMapName);
        neighCm.get(tracker).neighborMapAssets.put(activeMapName, new Array<String>());

        MapProperties activeMapProperties = assetSystem.assetManager.get(activeMapName, TiledMap.class).getProperties();

        MapSetupUtils.getAssetsFromMap(activeMapName, activeMapProperties, tracker);

        Gdx.app.log(TAG, "setup of the map begins");
        MapSetupUtils.setup(gameEntitySpawnerSystem,
                layers, width, height, tracker, activeMapName);
    }
}

