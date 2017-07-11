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
                            entitySpawnerSystem.spawnEntity(tx* G.CELL_SIZE, ty*G.CELL_SIZE, properties);
                            layer.setCell(tx, ty, null);
                        }
                    }
                }
            }
        }
    }

    public static void setup(EntitySpawnerSystem entitySpawnerSystem,
                             Array<TiledMapTileLayer> layers,
                             float width, float height, Entity tracker) {

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

            for (int ty = 0; ty < height; ty++) {
                for (int tx = 0; tx < width; tx++) {
                    final TiledMapTileLayer.Cell cell = layer.getCell(tx, ty);
                    if (cell != null) {

                        final MapProperties properties = cell.getTile().getProperties();
                        // we use tiles having the key entity to create the entities in the game.
                        if ( properties.containsKey("entity")) {
                            Gdx.app.debug(TAG, "entity found with value "  + properties.get("entity"));
                            entitySpawnerSystem.spawnEntity(tx* G.CELL_SIZE, ty*G.CELL_SIZE, properties);
                            layer.setCell(tx, ty, null);
                        }
                    }
                }
            }
        }
    }
}
