package com.hr.highpriestess.game.systems.GameSystems.Render;

import com.artemis.*;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hr.highpriestess.game.components.Game.Anim;
import com.hr.highpriestess.game.components.Menu.Bounds;
import com.hr.highpriestess.game.systems.MenuSystems.AssetSystem;
import com.hr.highpriestess.game.systems.MenuSystems.CameraSystem;

import java.util.*;

/**
 * This is supposed to render all entities in the world.
 */
public class EntityRenderSystem extends BaseEntitySystem  {

    String TAG = EntityRenderSystem.class.getName();

    SpriteBatch batch = new SpriteBatch();

    AssetSystem assetSystem;

    ComponentMapper<Anim> animCm;
    ComponentMapper<Bounds> boundsCm;

    GroupManager groupManager;
    CameraSystem cameraSystem;






    public EntityRenderSystem() {
        super(Aspect.all(Anim.class, Bounds.class));
    }

    // list that keeps the entities in the order they need to be rendered. Awesome.
    private List<Integer> sortedEntities = new ArrayList<Integer>();
    // boolean to track if entities need to be reorganized
    boolean entities_mixed = true;

    class layerSortComperator implements Comparator<Integer> {

        @Override
        public int compare(Integer e1, Integer e2) {
            return animCm.get(e1).layer.compareTo(animCm.get(e2).layer);
        }
    }


    protected void inserted (int e) {
        sortedEntities.add(e);
        entities_mixed = true;
        Gdx.app.debug(TAG, e + " was added");
    }

    protected void removed (int e) {
        sortedEntities.remove((Integer) e);
        Gdx.app.debug(TAG, e + " was removed");

    }


    @Override
    protected void processSystem() {

        if (entities_mixed) {
            entities_mixed = false;
            Collections.sort(sortedEntities, new layerSortComperator());
        }
        for (int e : sortedEntities) {
            process(e);
        }
    }




    protected void process(int e) {
        Animation animation = assetSystem.get(animCm.get(e).activeId);
        animation.setPlayMode(Animation.PlayMode.LOOP);
        TextureRegion currentFrame = animation.getKeyFrame(animCm.get(e).age);

        float x = boundsCm.get(e).x;
        float y = boundsCm.get(e).y;
        float width = boundsCm.get(e).width;
        float height = boundsCm.get(e).height;

        batch.setProjectionMatrix(cameraSystem.camera.combined);
        batch.begin();
        batch.draw(currentFrame, x, y, width, height);
        batch.end();

        animCm.get(e).age += Gdx.graphics.getDeltaTime();





    }
}
