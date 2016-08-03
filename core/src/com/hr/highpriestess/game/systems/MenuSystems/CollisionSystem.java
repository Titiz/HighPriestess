package com.hr.highpriestess.game.systems.MenuSystems;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.hr.highpriestess.game.components.Menu.Bounds;


public class CollisionSystem extends BaseSystem{
    /**
     * System that uses the bounds of an Entity to see if it collides with other things.
     */

    private ComponentMapper<Bounds> bm;
    private CameraSystem cameraSystem;

    public CollisionSystem() {

    }



    public boolean collidesWithMouse(int entity) {
        final Bounds bounds = bm.getSafe(entity);
        Vector3 mousePos = new Vector3();
        mousePos.add(Gdx.input.getX(), Gdx.input.getY(),0);
        cameraSystem.camera.unproject(mousePos);
        float mouseX = mousePos.x;
        float mouseY = mousePos.y;
        boolean bol = false;

        if (mouseX > bounds.x && mouseX < bounds.x + bounds.width) {
            if(mouseY > bounds.y && mouseY < bounds.y + bounds.height) {
                bol = true;
            }
        }
        return bol;
    }

    public boolean twoEntityCollision(int e1, int e2) {
        final Bounds e1bounds = bm.getSafe(e1);
        final Bounds e2bounds = bm.getSafe(e2);
        boolean isCollide = false;

        if (e1bounds.x < e2bounds.x + e2bounds.width && e1bounds.x + e1bounds.width > e2bounds.x) {
            if (e2bounds.y < e1bounds.y + e1bounds.height && e2bounds.y + e2bounds.height > e1bounds.y) {
                isCollide = true;
            }
        }
        return isCollide;
    }

    protected void processSystem(){

    }





}
