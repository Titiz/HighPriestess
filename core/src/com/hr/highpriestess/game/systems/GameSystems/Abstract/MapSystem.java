package com.hr.highpriestess.game.systems.GameSystems.Abstract;

import com.artemis.BaseSystem;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Titas on 2016-08-03.
 */
public abstract class MapSystem extends BaseSystem {


    // map that will be rendered
    public TiledMap map;
    // width and height of the map that will be defined for later use
    public int width;
    public int height;
    protected Array<TiledMapTileLayer> layers;



    @Override
    protected void processSystem() {

    }
}
