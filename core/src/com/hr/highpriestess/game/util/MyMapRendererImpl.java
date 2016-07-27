package com.hr.highpriestess.game.util;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Created by Titas on 2016-07-27.
 */
public class MyMapRendererImpl extends OrthogonalTiledMapRenderer {
    public MyMapRendererImpl(TiledMap map) {
        super(map);
    }

    public void renderLayer(TiledMapTileLayer layer) {
        beginRender();
        super.renderTileLayer(layer);
        endRender();
    }
}

