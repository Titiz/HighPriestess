package com.hr.highpriestess.game.systems.GameSystems;

import com.artemis.BaseSystem;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Titas on 2016-07-27.
 */
public class MapSystem extends BaseSystem {

    public TiledMap map;
    private int width;
    private int height;
    private Array<TiledMapTileLayer> layers;
    private boolean isSetup;

    @Override
    protected void processSystem() {
      if (!isSetup) {
          map = new TmxMapLoader().load("map1.tmx");

          layers = new Array<TiledMapTileLayer>();
          for ( MapLayer rawLayer : map.getLayers() )
          {
              layers.add((TiledMapTileLayer) rawLayer);
          }
          width = layers.get(0).getWidth();
          height = layers.get(0).getHeight();


      }
    }
}
