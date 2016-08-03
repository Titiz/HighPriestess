package com.hr.highpriestess.game.systems.GameSystems.DefenceSystems;

import com.artemis.BaseSystem;
import com.artemis.Component;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.hr.highpriestess.game.components.Menu.Bounds;
import com.hr.highpriestess.game.systems.GameSystems.EntityClearerSystem;
import com.hr.highpriestess.game.systems.MenuSystems.CameraSystem;
import com.hr.highpriestess.game.util.EntityMakerDefence;

/**
 * Created by Titas on 2016-08-02.
 */
public class MakeSelectionSquare extends BaseSystem {

    ComponentMapper<Bounds> boundsCm;

    CameraSystem cameraSystem;
    TagManager tagManager;



    Vector3 inMousePos;

    @Override
    protected void processSystem() {
        Vector3 mousePos = new Vector3();
        mousePos.add(Gdx.input.getX(), Gdx.input.getY(),0);
        cameraSystem.camera.unproject(mousePos);
        if (Gdx.input.isButtonPressed(0)) {
            if (!tagManager.isRegistered("selectionSquare")) {
                inMousePos = mousePos;

                Entity selectionSquare = EntityMakerDefence.createSelectionSquare(this.getWorld(), mousePos.x, mousePos.y);
                tagManager.register("selectionSquare", selectionSquare);
                Bounds bounds = boundsCm.getSafe(selectionSquare);
                // set the initial coordinate of the square.
                bounds.x = inMousePos.x;
                bounds.y = inMousePos.y;
            }
            else {
                int e = tagManager.getEntity("selectionSquare").getId();
                Bounds bounds = boundsCm.getSafe(e);
                float width = mousePos.x - inMousePos.x;
                float height = mousePos.y - inMousePos.y;
                if (width > 0 && height > 0) {
                    bounds.width = width;
                    bounds.height = height;
                } else
                    if (width < 0 && height <0) {
                        bounds.width = -width;
                        bounds.height = -height;
                        bounds.x = mousePos.x;
                        bounds.y = mousePos.y;
                    } else
                    if (width < 0 && height >0) {
                        bounds.width = -width;
                        bounds.height = height;
                        bounds.x = mousePos.x;
                    } else
                    if (width > 0 && height <0) {
                        bounds.height = -height;
                        bounds.width = width;
                        bounds.y = mousePos.y;

                }
            }
        } else {
            if (tagManager.isRegistered("selectionSquare")){
                int e = tagManager.getEntity("selectionSquare").getId();
                this.getWorld().delete(e);
            }

        }
        if (Gdx.input.isButtonPressed(1)) {
            EntityMakerDefence.create(this.getWorld(), mousePos.x, mousePos.y);
        }
    }
}
