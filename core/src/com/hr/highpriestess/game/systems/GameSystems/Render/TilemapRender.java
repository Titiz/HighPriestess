package com.hr.highpriestess.game.systems.GameSystems.Render;

import com.artemis.BaseSystem;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.hr.highpriestess.game.systems.GameSystems.MapSystem;
import com.hr.highpriestess.game.systems.MenuSystems.CameraSystem;
import com.hr.highpriestess.game.util.MyMapRendererImpl;

/**
 * Created by Titas on 2016-07-27.
 */
public class TilemapRender extends BaseSystem{

    public MyMapRendererImpl renderer;
    private CameraSystem cameraSystem;



    MapSystem mapSystem;
    @Override
    protected void processSystem() {
        for (MapLayer layer : mapSystem.map.getLayers()) {
            if (layer.isVisible()) {
                if (!layer.getName().equals("infront")) {
                    renderLayer((TiledMapTileLayer) layer);
                }
            }
        }
    }

    private void renderLayer(final TiledMapTileLayer layer) {
        renderer.setView(cameraSystem.camera);
        renderer.renderLayer(layer);
    }
}

