package com.hr.highpriestess.game.util;


import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Array;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.systems.GameSystems.Abstract.EntityClearerSystem;
import com.hr.highpriestess.game.systems.GameSystems.Abstract.EntitySpawnerSystem;

/**
 * Class that hold the function to setup the map.
 */
public class MapSetupHolder {



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
}
