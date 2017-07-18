package com.hr.highpriestess.game.systems.GameSystems;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapImageLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Array;
import com.hr.highpriestess.game.components.Game.Tracker.NeighborMapTracker;
import com.hr.highpriestess.game.systems.MenuSystems.AssetSystem;
import com.hr.highpriestess.game.util.MapSetupUtils;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Titas on 2017-07-12.
 */
public class BackgroundAssetSystem extends BaseSystem {


    ComponentMapper<NeighborMapTracker> neighborCm;

    TagManager tagManager;

    AssetSystem assetSystem;


    String TAG = BackgroundAssetSystem.class.getName();

    public boolean isSetup = false;
    private boolean assetsLoaded = false;



    private void removeUnusedResources() {
        Gdx.app.debug(TAG, "Removing unused resources");
        HashMap<String, Array<String >> map = neighborCm.get(tagManager.getEntity("tracker")).neighborMapAssets;
        HashSet<String> neighbors = neighborCm.get(tagManager.getEntity("tracker")).currentNeighborNames;
        HashMap<String, Integer> assetCount = neighborCm.get(tagManager.getEntity("tracker")).assetUseCounter;

        for (String mapName : map.keySet()) {
            Gdx.app.debug(TAG, "Checking map with name: " + mapName);
            if (!neighbors.contains(mapName)) {
                Gdx.app.debug(TAG, mapName + " is not in neighbors");
                for (String assetName : map.get(mapName)) {
                    Gdx.app.debug(TAG, "decrementing by one asset with name " + assetName);
                    assetCount.put(assetName, assetCount.get(assetName) - 1);
                    Gdx.app.debug(TAG, "assetCount for " + assetName + " is " + assetCount.get(assetName));
                    if (assetCount.get(assetName) == 0) {
                        Gdx.app.debug(TAG, "Removing asset from assetUseCounter with name: " + assetName);
                        assetCount.remove(assetName);
                        Gdx.app.debug(TAG, String.valueOf(assetSystem.assetManager.isLoaded(assetName)));
                        Gdx.app.debug(TAG, "Unloading resource with name: " + assetName);
                        assetSystem.assetManager.unload(assetName);
                        Gdx.app.debug(TAG, String.valueOf(assetSystem.assetManager.isLoaded(assetName)));
                        if (assetSystem.assetManager.isLoaded(assetName))
                            Gdx.app.debug(TAG, "references " + assetSystem.assetManager.getReferenceCount(assetName));
                    }
                }
                map.put(mapName, new Array<String>());
            } else {
                Gdx.app.debug(TAG, mapName + " is in neighbors");
            }
        }
    }


//    private void getNeighborAssets() {
//        Gdx.app.debug(TAG, "getting ready to rerieve assets of all neighboring maps");
//        Entity tracker = tagManager.getEntity("tracker");
//        Gdx.app.debug(TAG, "neighboring map count is " + neighborCm.get(tracker).currentNeighborNames.size());
//        Array<TiledMapTileLayer> layers = new Array<TiledMapTileLayer>();
//        for (String mapName : neighborCm.get(tracker).currentNeighborNames) {
//            if (neighborCm.get(tracker).lastMapNeighborNames.contains(mapName) ||
//                    neighborCm.get(tracker).activeMapName.equals(mapName))
//                        continue;
//            layers.clear();
//            TiledMap map = assetSystem.assetManager.get(mapName);
//            Gdx.app.debug(TAG, "going through layers of " +mapName);
//            for (MapLayer rawLayer : map.getLayers()) {
//                if (rawLayer.getClass() == TiledMapImageLayer.class) continue;
//                Gdx.app.debug(TAG, rawLayer.getClass().toString());
//                layers.add((TiledMapTileLayer) rawLayer);
//            }
//            Gdx.app.debug(TAG, "done retrieving layers of " + mapName);
//
//            Gdx.app.debug(TAG, "retrieving height and width of " + mapName);
//            float width = layers.get(0).getWidth();
//            float height = layers.get(0).getHeight();
//
//            Gdx.app.debug(TAG, "getting assets from map " + mapName);
//            MapSetupUtils.getAssetsFromMap(layers, width, height, tracker, mapName);
//            Gdx.app.debug(TAG, "getting assets from map finished " + mapName);
//        }
//    }

    private void getNeighborAssets() {
        Gdx.app.debug(TAG, "getting ready to rerieve assets of all neighboring maps");
        Entity tracker = tagManager.getEntity("tracker");
        Gdx.app.debug(TAG, "neighboring map count is " + neighborCm.get(tracker).currentNeighborNames.size());
        for (String mapName : neighborCm.get(tracker).currentNeighborNames) {
            if (neighborCm.get(tracker).lastMapNeighborNames.contains(mapName) ||
                    neighborCm.get(tracker).activeMapName.equals(mapName))
                continue;
            TiledMap map = assetSystem.assetManager.get(mapName);
            Gdx.app.debug(TAG, "about to get assets from neighboring map" + mapName);
            MapSetupUtils.getAssetsFromMap(mapName, map.getProperties(), tracker);
        }
    }



    private void addAssetsToQueue() {
        Gdx.app.debug(TAG, "Adding assets to BackgroundAssetSystem Queue");
        NeighborMapTracker neighTracker = neighborCm.get(tagManager.getEntity("tracker"));
        HashMap<String, Array<String>> map = neighTracker.neighborMapAssets;
        for (String key : map.keySet()) {
            if (neighTracker.lastMapNeighborNames.contains(key)) continue; // map was in lastNeighbors => resources already in queue
            if (neighTracker.activeMapName.equals(key)) continue; // map is active => resources were loaded prior
            Gdx.app.debug(TAG, "adding assets of the map " + key + " to the queue");
            for (String value : map.get(key)) {
                if (!assetSystem.assetManager.isLoaded(value)) {
                    Gdx.app.debug(TAG, "adding asset " + value + " to the loading queue");
                    assetSystem.load(value);
                }
            }
        }
    }

    private void debugPrint() {
        if (Gdx.app.getLogLevel() != Gdx.app.LOG_DEBUG)
            return;

        NeighborMapTracker neigh = neighborCm.get(tagManager.getEntity("tracker"));
        for (String key : neigh.assetUseCounter.keySet()) {
            Gdx.app.debug("Use of resources", key + " : " +neigh.assetUseCounter.get(key));
        }
        Gdx.app.debug(TAG, "LastMapNeighbors");
        for (String mapName : neigh.lastMapNeighborNames) {
            Gdx.app.debug("", mapName);
        }

        Gdx.app.debug(TAG, "currentNeighbors");
        for (String mapName : neigh.currentNeighborNames) {
            Gdx.app.debug("", mapName);
        }

        Gdx.app.debug(TAG, "assetLists");
        String mapResources;
        for (String mapName : neigh.neighborMapAssets.keySet()) {
            mapResources = "";
            for (String resourceName : neigh.neighborMapAssets.get(mapName)) {
                mapResources += resourceName + ", ";
            }
            Gdx.app.debug(mapName, mapResources);
        }
        Gdx.app.debug(TAG, "current amount of resources loaded is: " +assetSystem.assetManager.getLoadedAssets());

    }

    @Override
    protected void processSystem() {

        if (!isSetup) {
            getNeighborAssets();
            removeUnusedResources();
            addAssetsToQueue();
            debugPrint();

            isSetup = true;
            Gdx.app.debug(TAG, "Setup Complete");
        }


        if (assetSystem.assetManager.getProgress() != 1) {
            Gdx.app.debug(TAG, "assetManager backgroundAssetLoading progress: " + assetSystem.assetManager.getProgress());
        }

        assetSystem.assetManager.update(10); //update the assetSystem for 10 milliseconds.


    }
}
