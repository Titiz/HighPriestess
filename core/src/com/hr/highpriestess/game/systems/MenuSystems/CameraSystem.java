package com.hr.highpriestess.game.systems.MenuSystems;

import com.artemis.BaseSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;


public class CameraSystem extends BaseSystem{
    /**
     * This class serves to create two camera objects, one for the gui, one for the game itself.
     */
    public final OrthographicCamera camera;
    public final OrthographicCamera guiCamera;


    public static float getZOOM() {
        return ZOOM;
    }

            public static float ZOOM = 1.0f;

    public CameraSystem() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth() * ZOOM, Gdx.graphics.getHeight() * ZOOM);
        camera.setToOrtho(false, Gdx.graphics.getWidth() * ZOOM, Gdx.graphics.getHeight() * ZOOM);
        camera.update();

        guiCamera = new OrthographicCamera(Gdx.graphics.getWidth() * ZOOM, Gdx.graphics.getHeight() * ZOOM);
        guiCamera.setToOrtho(false, Gdx.graphics.getWidth() * ZOOM, Gdx.graphics.getHeight() * ZOOM);
        guiCamera.update();
    }

    @Override
    protected void processSystem() {
        camera.update();
    }

    public void reset() {
        this.camera.position.x = 0;
        this.camera.position.y = 0;
        camera.setToOrtho(false, Gdx.graphics.getWidth() * ZOOM, Gdx.graphics.getHeight() * ZOOM);
    }
}