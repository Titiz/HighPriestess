package com.hr.highpriestess.game.util;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * A holder for the rendering of the map. used to make the rendering code a little cleaner.
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

