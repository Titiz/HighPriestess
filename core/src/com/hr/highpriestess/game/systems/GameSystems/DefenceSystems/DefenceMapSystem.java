package com.hr.highpriestess.game.systems.GameSystems.DefenceSystems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;
import com.hr.highpriestess.game.systems.GameSystems.Abstract.MapSystem;
import com.hr.highpriestess.game.systems.MenuSystems.CameraSystem;
import com.hr.highpriestess.game.util.MapSetupUtils;

/**
 * Created by Titas on 2016-08-03.
 * this class is designed to read 2 maps.
 * One is to create the battlefield
 * the other is to create the waves of enemies.
 */




public class DefenceMapSystem extends MapSystem {

    String TAG = DefenceMapSystem.class.getName();


    private TiledMap waveMap;
    DefenceEntitySpawnerSystem defenceEntitySpawnerSystem;
    DefenceEntityClearerSystem defenceEntityClearerSystem;


    boolean isSetup;

    CameraSystem cameraSystem;

    @Override
    protected void initialize() {

        map = new TmxMapLoader().load("Defend.tmx");
        waveMap = new TmxMapLoader().load("WaveMap.tmx");
        resetMap();
    }

    public void resetMap() {

        Gdx.app.debug(TAG, "activeMap changed");

        cameraSystem.reset();

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
            MapSetupUtils.setup(defenceEntityClearerSystem,
                    defenceEntitySpawnerSystem,
                    layers, width, height);
        }
    }
}

