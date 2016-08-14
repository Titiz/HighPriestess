package com.hr.highpriestess.game.systems.GameSystems.DefenceSystems;

import com.artemis.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.systems.GameSystems.Abstract.MapSystem;
import com.hr.highpriestess.game.systems.MenuSystems.CameraSystem;

/**
 * Created by Titas on 2016-08-03.
 */
public class WaveMapSystem extends MapSystem {


    String TAG = WaveMapSystem.class.getName();

    Array<Integer> layer_widths;
    float time;


    int current_col = -1;
    int last_col = -1;


    int wave = 0;
    DefenceEntitySpawnerSystem defenceEntitySpawnerSystem;


    @Override
    protected void initialize() {

        map = new TmxMapLoader().load("WaveMap.tmx");
        layers = new Array<TiledMapTileLayer>();
        for (MapLayer rawLayer : map.getLayers()) {
            layers.add((TiledMapTileLayer) rawLayer);
        }
        height = layers.get(0).getHeight();
        width = layers.get(0).getWidth();
        Gdx.app.debug(TAG, "Initialized with width: " + width + " and height: " + height);
    }

    @Override
    protected void processSystem() {
        time += Gdx.graphics.getDeltaTime();
        current_col = (int) time / 2;
        if (current_col != last_col) {
            TiledMapTileLayer layer = layers.get(wave);
            for (int ty = 0; ty < height; ty++) {
                final TiledMapTileLayer.Cell cell = layer.getCell(current_col, ty);
                if (cell != null) {
                    final MapProperties properties = cell.getTile().getProperties();
                    if (properties.containsKey("entity")) {
                        defenceEntitySpawnerSystem.spawnEntity(400, ty * G.CELL_SIZE * 2, properties);
                    }
                }
            }
        }

        last_col = current_col;


    }
}
