package com.hr.highpriestess.game.systems.GameSystems.DefenceSystems.Render;

import com.artemis.Aspect;
import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.managers.TagManager;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hr.highpriestess.game.components.Menu.Bounds;
import com.hr.highpriestess.game.systems.MenuSystems.CameraSystem;


import static com.badlogic.gdx.graphics.g2d.ParticleEmitter.SpawnShape.square;

/**
 * Created by Titas on 2016-08-02.
 */
public class RenderSelectionSquare extends BaseSystem {

    String TAG = RenderSelectionSquare.class.getName();

    TagManager tagManager;
    CameraSystem cameraSystem;
    ShapeRenderer shapeRenderer = new ShapeRenderer();
    ComponentMapper<Bounds> boundsCm;

    @Override
    protected void processSystem() {
        if (tagManager.isRegistered("selectionSquare")) {
            int square  = tagManager.getEntity("selectionSquare").getId();
            Bounds bounds = boundsCm.getSafe(square);
            shapeRenderer.setProjectionMatrix(cameraSystem.camera.combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            shapeRenderer.setColor(0, 1, 0, 0.5f);
            shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
            shapeRenderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
        }


    }



}
