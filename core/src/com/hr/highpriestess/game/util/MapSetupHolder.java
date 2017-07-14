package com.hr.highpriestess.game.util;


import com.artemis.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Array;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.components.Game.Tracker.*;
import com.hr.highpriestess.game.systems.GameSystems.Abstract.EntityClearerSystem;
import com.hr.highpriestess.game.systems.GameSystems.Abstract.EntitySpawnerSystem;

/**
 * Class that hold the function to setup the map.
 */
public class MapSetupHolder {

    private static String TAG = MapSetupHolder.class.getName();

    public static void setup(EntityClearerSystem entityClearerSystem,
                             EntitySpawnerSystem entitySpawnerSystem,
                             Array<TiledMapTileLayer> layers,
                             float width, float height) {

        entityClearerSystem.clearEntities();
        for (TiledMapTileLayer layer : layers) {
            for (int ty = 0; ty < height; ty++) {
                for (int tx = 0; tx < width; tx++) {
                    final TiledMapTileLayer.Cell cell = layer.getCell(tx, ty);
                    if (cell != null) {
                        final MapProperties properties = cell.getTile().getProperties();
                        // we use tiles having the key entity to create the entities in the game.
                        if ( properties.containsKey("entity")) {
                            G.Layer layerName = G.Layer.valueOf(layer.getProperties().get("Name", String.class));
                            entitySpawnerSystem.spawnEntity(tx* G.CELL_SIZE, ty*G.CELL_SIZE, properties, layerName);
                            layer.setCell(tx, ty, null);
                        }
                    }
                }
            }
        }
    }

    public static void setup(EntitySpawnerSystem entitySpawnerSystem,
                             Array<TiledMapTileLayer> layers,
                             float width, float height, Entity tracker,
                             String mapName) {

        for (TiledMapTileLayer layer : layers) {
            Gdx.app.log("LAYER NAME", layer.getName());
            if (layer.getProperties().containsKey("PixelShader")) {

                Gdx.app.log("VERTEX SHADER NAME", layer.getProperties().get("VertexShader").toString());
                Gdx.app.log("PIXEL SHADER NAME", layer.getProperties().get("PixelShader").toString());
                tracker.getComponent(ShaderHolder.class).ShaderMap.put(G.Layer.valueOf(layer.getName()),
                        new ShaderProgram(Gdx.files.internal(layer.getProperties().get("VertexShader").toString()),
                                Gdx.files.internal(layer.getProperties().get("PixelShader").toString())));
            } else {
                Gdx.app.log("SHADERS", "LAYER HAS NO SHADERS");
            }

            NeighborMapTracker neigh = tracker.getComponent(NeighborMapTracker.class);
            Boolean lastMapContains = new Boolean(neigh.lastMapNeighborNames.contains(mapName));
            for (int ty = 0; ty < height; ty++) {
                for (int tx = 0; tx < width; tx++) {
                    final TiledMapTileLayer.Cell cell = layer.getCell(tx, ty);
                    if (cell != null) {
                        final MapProperties properties = cell.getTile().getProperties();
                        // we use tiles having the key entity to create the entities in the game.
                        if ( properties.containsKey("entity")) {
                            Gdx.app.debug(TAG, "entity found with value "  + properties.get("entity"));
                            G.Layer layerName = G.Layer.valueOf(layer.getName().toUpperCase());
                            entitySpawnerSystem.spawnEntity(tx* G.CELL_SIZE, ty*G.CELL_SIZE, properties, layerName);
                            layer.setCell(tx, ty, null);
                            trackNeighboringMaps(properties, tracker);                  // tracker entity has to track both the
                            trackResourceUseCount(properties, tracker, mapName, lastMapContains);  // resource use of the main map
                            layer.setCell(tx, ty, null);
                        }
                    }
                }
            }
        }
    }

    public static void getAssetsFromMap(Array<TiledMapTileLayer> layers,
                             float width, float height, Entity tracker,
                                        String mapName) {
        Gdx.app.debug(TAG, "getAssetsFromMap called with mapName " + mapName);
        Gdx.app.debug(TAG, "Processing layers of the map: " + mapName);
        NeighborMapTracker neigh = tracker.getComponent(NeighborMapTracker.class);
        Boolean lastMapContains = new Boolean(neigh.lastMapNeighborNames.contains(mapName));
        Gdx.app.debug(TAG, "did lastMapNeighborNames contain " + mapName + "? " + lastMapContains.toString());
        for (TiledMapTileLayer layer : layers) {
            for (int ty = 0; ty < height; ty++) {
                for (int tx = 0; tx < width; tx++) {
                    final TiledMapTileLayer.Cell cell = layer.getCell(tx, ty);
                    if (cell != null) {
                        final MapProperties properties = cell.getTile().getProperties();
                        if (properties.containsKey("entity")) {
                                trackResourceUseCount(properties, tracker, mapName, lastMapContains);
                            }
                        }
                    }
                }
            }
        }



    private static void trackNeighboringMaps(MapProperties properties, Entity tracker) {
        if (properties.containsKey("gate")) {
            Gdx.app.debug(TAG, "gate found with value " + properties.get("gate"));
            NeighborMapTracker neigh = tracker.getComponent(NeighborMapTracker.class);
            String nameOfNeighbor = (String) properties.get("gate");
            if (!neigh.neighborMapAssets.containsKey(nameOfNeighbor)) {
                Gdx.app.debug(TAG, "found new neighbor with name: " + nameOfNeighbor);
                neigh.neighborMapAssets.put(nameOfNeighbor, new Array<String>());
            }
            Gdx.app.debug(TAG, "adding " + nameOfNeighbor + " to current neighbors");
            neigh.currentNeighborNames.add(nameOfNeighbor);
        }
    }



    private static void trackResourceUseCount(MapProperties properties, Entity tracker, String mapName, Boolean lastMapContains) {
        Gdx.app.debug(TAG, "trackResourcesUseCount Called");
        Gdx.app.debug(TAG, "Found entity in cell, checking if it has resources");
        NeighborMapTracker neigh = tracker.getComponent(NeighborMapTracker.class);
        for (String name : G.resourceIndentifier) {
            if (properties.containsKey(name)){
                String resourceName = (String) properties.get(name);
                Gdx.app.debug(TAG, "resource found with name: " + resourceName);
                if (!neigh.assetUseCounter.containsKey(resourceName)) {
                    neigh.assetUseCounter.put(resourceName, 0);
                    Gdx.app.debug(TAG, "resource was found to be new");
                }
                if (!lastMapContains) {
                    neigh.assetUseCounter.put(resourceName, neigh.assetUseCounter.get(resourceName) + 1);
                    Gdx.app.debug(TAG, "resource use counter is now: " + neigh.assetUseCounter.get(resourceName));
                } else {
                    Gdx.app.debug(TAG, "resource already used by this map, resource name: " + resourceName);
                    Gdx.app.debug(TAG, "resource is in use by " + neigh.assetUseCounter.get(resourceName) + " maps");
                }

                Gdx.app.debug(TAG, "adding resource to Asset list of: " + mapName);

                neigh.neighborMapAssets.get(mapName).add(resourceName);
            }
        }
    }
}
