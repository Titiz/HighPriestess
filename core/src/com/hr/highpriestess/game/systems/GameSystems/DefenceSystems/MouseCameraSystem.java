package com.hr.highpriestess.game.systems.GameSystems.DefenceSystems;

import com.artemis.BaseSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.hr.highpriestess.game.systems.MenuSystems.CameraSystem;

/**
 * Created by Titas on 2016-08-03.
 */
public class MouseCameraSystem extends BaseSystem {

    CameraSystem cameraSystem;

    @Override
    protected void processSystem() {

            if (Gdx.input.getX() > cameraSystem.camera.viewportWidth * 0.85f) {
                cameraSystem.camera.translate(200 * Gdx.graphics.getDeltaTime(), 0);
            } else if (Gdx.input.getX() < cameraSystem.camera.viewportWidth * 0.15f &&
                    cameraSystem.camera.position.x >= cameraSystem.camera.viewportWidth / 2) {
                cameraSystem.camera.translate(-200 * Gdx.graphics.getDeltaTime(), 0);
            }
        }

}
