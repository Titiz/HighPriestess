package com.hr.highpriestess.game.systems.GameSystems.Render;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.components.Game.Tracker.ShaderHolder;
import com.hr.highpriestess.game.systems.GameSystems.Abstract.MapSystem;
import com.hr.highpriestess.game.systems.GameSystems.GameMapSystem;
import com.hr.highpriestess.game.systems.MenuSystems.CameraSystem;
import com.hr.highpriestess.game.util.MyMapRendererImpl;

/**
 * Renders all of the tiles shown on the tmx file.
 */
public class TilemapRender extends BaseSystem{

    private CameraSystem cameraSystem;
    OrthogonalTiledMapRenderer renderer;

    public void setMapSystem(MapSystem mapSystem) {
        this.mapSystem = mapSystem;
    }

    private MapSystem mapSystem;

    TagManager tagManager;
    ComponentMapper<ShaderHolder> shaderCm;

    private boolean isRendererMade = false;
    ShaderProgram currentShader;


    private void makeRenderer() {
        if (!isRendererMade) {

            currentShader = shaderCm.get(tagManager.getEntity("tracker")).ShaderMap.get(G.Layer.DEFAULT);

            Vector3 ambientColor = new Vector3(0.3f, 0.3f, 0.7f);

            ShaderProgram.pedantic = false;
            currentShader.begin();
            currentShader.setUniformf("ambientColor", ambientColor.x, ambientColor.y,
                    ambientColor.z, 0.8f);
//            currentShader.end();
            SpriteBatch batch = new SpriteBatch();
            batch.setShader(currentShader);
            renderer = new OrthogonalTiledMapRenderer(mapSystem.map, batch);
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
        renderer.getBatch().begin();
        renderer.renderTileLayer(layer);
        renderer.getBatch().end();
    }
}

