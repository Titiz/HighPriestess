package com.hr.highpriestess.game.systems.GameSystems;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.components.Game.Anim;
import com.hr.highpriestess.game.components.Game.Kinematics;
import com.hr.highpriestess.game.components.Menu.Bounds;
import com.hr.highpriestess.game.systems.MenuSystems.CameraSystem;

/**
 * Created by Titas on 2016-07-28.
 */
public class ControllerSystem extends BaseSystem {



    ComponentMapper<Kinematics> kinCm;
    ComponentMapper<Anim> animCm;
    CameraSystem cameraSystem;
    GameMapSystem gameMapSystem;
    ComponentMapper<Bounds> boundsCm;

    TagManager tagManager;


    @Override
    protected void processSystem() {

        int player = tagManager.getEntity("player").getId();
        Kinematics entity = kinCm.getSafe(player);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            entity.setVx(-200 * Gdx.graphics.getDeltaTime());

        }
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            entity.setVx(200 * Gdx.graphics.getDeltaTime());

        } else
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            G.game.goDefence();
        }

        else

            entity.setVx(0*Gdx.graphics.getDeltaTime());

        cameraSystem.camera.position.x = boundsCm.get(player).x;
        if (cameraSystem.camera.position.x <= cameraSystem.camera.viewportWidth/2)
            cameraSystem.camera.position.x = cameraSystem.camera.viewportWidth/2;



}
}

