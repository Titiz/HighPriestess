package com.hr.highpriestess.game.systems.GameSystems;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.hr.highpriestess.game.components.Game.*;
import com.hr.highpriestess.game.components.Game.Interactibles.Door;
import com.hr.highpriestess.game.components.Menu.Bounds;
import com.hr.highpriestess.game.systems.MenuSystems.CameraSystem;

/**
 * Created by Titas on 2016-07-28.
 */
public class ControllerSystem extends BaseSystem {

    String TAG = ControllerSystem.class.getName();

    ComponentMapper<Kinematics> kinCm;
    ComponentMapper<Anim> animCm;
    CameraSystem cameraSystem;
    GameMapSystem gameMapSystem;
    ComponentMapper<Bounds> boundsCm;
    ComponentMapper<Player> playerCm;
    ComponentMapper<Door> doorCm;
    ComponentMapper<ChangeMap> changeMapcm;
    ComponentMapper<Tween> tweenCm;

    TagManager tagManager;


    @Override
    protected void processSystem() {
        /** Controls player input **/
        int player = tagManager.getEntity("player").getId();

        playerCm.get(player).isActiveButtonClicked = false;


        Kinematics entity = kinCm.getSafe(player);

        if (playerCm.get(player).currentState == Player.States.DEFAULT) {

            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                entity.setVx(-200 * Gdx.graphics.getDeltaTime());

            } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                entity.setVx(200 * Gdx.graphics.getDeltaTime());


            } else
                entity.setVx(0 * Gdx.graphics.getDeltaTime());

        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            Gdx.app.log(TAG, "E was pressed");
            playerCm.get(player).isActiveButtonClicked = true;
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            Tween tween = tweenCm.create(player);

        }




        cameraSystem.camera.position.x = boundsCm.get(player).x;
        if (cameraSystem.camera.position.x <= cameraSystem.camera.viewportWidth/2)
            cameraSystem.camera.position.x = cameraSystem.camera.viewportWidth/2;



}
}

