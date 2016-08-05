package com.hr.highpriestess.game.systems.GameSystems.Render;

import com.artemis.BaseSystem;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.hr.highpriestess.game.systems.GameSystems.Abstract.MapSystem;
import com.hr.highpriestess.game.systems.GameSystems.GameMapSystem;
import com.hr.highpriestess.game.systems.MenuSystems.CameraSystem;
import com.hr.highpriestess.game.util.MyMapRendererImpl;

/**
 * Created by Titas on 2016-07-27.
 */
public class TilemapRender extends BaseSystem{

    public MyMapRendererImpl renderer;
    private CameraSystem cameraSystem;

    public void setMapSystem(MapSystem mapSystem) {
        this.mapSystem = mapSystem;
    }

    private MapSystem mapSystem;
    private boolean isRendererMade = false;


    private void makeRenderer() {
        if (!isRendererMade) {
            renderer = new MyMapRendererImpl(mapSystem.map);
            isRendererMade = true;
        }
    }




    @Override
    protected void processSystem() {
        makeRenderer();
        for (MapLayer layer : mapSystem.map.getLayers()) {
            if (layer.isVisible()) {
                    renderLayer((TiledMapTileLayer) layer);
                }
            }
    }




    private void renderLayer(final TiledMapTileLayer layer) {
        renderer.setView(cameraSystem.camera);
        renderer.renderLayer(layer);
    }
}

