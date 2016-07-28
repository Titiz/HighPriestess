package com.hr.highpriestess.game.systems.GameSystems;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.hr.highpriestess.game.components.Game.Controller;
import com.hr.highpriestess.game.components.Game.Kinematics;
import com.hr.highpriestess.game.components.Menu.Bounds;
import com.hr.highpriestess.game.systems.MenuSystems.CameraSystem;

/**
 * Created by Titas on 2016-07-28.
 */
public class ControllerSystem extends IteratingSystem {

    ComponentMapper<Kinematics> kinCm;
    CameraSystem cameraSystem;
    MapSystem mapSystem;


    public ControllerSystem(){
        super(Aspect.all(Controller.class));
    }

    @Override
    protected void process(int e) {
        Kinematics entity = kinCm.getSafe(e);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            entity.setVx(50 * Gdx.graphics.getDeltaTime());

        }
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            entity.setVx(-50 * Gdx.graphics.getDeltaTime());

        }
        else
            entity.setVx(0*Gdx.graphics.getDeltaTime());

        cameraSystem.camera.translate(-entity.getVx(), -entity.getVy());
    }
}
