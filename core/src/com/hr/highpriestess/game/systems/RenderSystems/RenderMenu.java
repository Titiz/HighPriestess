package com.hr.highpriestess.game.systems.RenderSystems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.hr.highpriestess.game.components.*;
import com.hr.highpriestess.game.systems.CameraSystem;
import com.hr.highpriestess.game.systems.AssetSystem;

import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP;

/**
 * Created by Titas on 2016-07-20.
 */
public class RenderMenu extends IteratingSystem{

    ComponentMapper<HoverableText>  hovCm;
    ComponentMapper<Alpha> alphaCm;
    ComponentMapper<AnimationBehind> animateBehindCm;
    ComponentMapper<Bounds> boundsCm;

    SpriteBatch batch = new SpriteBatch();
    CameraSystem cameraSystem;
    AssetSystem assetSystem;
    private float elapsedTime = 0;

    public RenderMenu() {
        super(Aspect.all(HoverableText.class));
    }


    @Override
    public void process(int e) {
        Label label = hovCm.get(e).getLabel();
        batch.setProjectionMatrix(cameraSystem.camera.combined);
        batch.begin();
        draw_animation_behind(e);
        label.draw(batch, alphaCm.get(e).getAlpha());

        batch.end();
    }


    public void draw_animation_behind(int e) {
        if (animateBehindCm.has(e)) {
            AnimationBehind etop = animateBehindCm.get(e);
            Animation anim = etop.getAnimation();
            anim.setPlayMode(Animation.PlayMode.NORMAL);
            anim.setFrameDuration(0.1f);
            // we check whether the animation is a repeating one



            if (etop.getForward()) {
                etop.addEllapsedTime(Gdx.graphics.getDeltaTime());
            } else {
                etop.addEllapsedTime(-Gdx.graphics.getDeltaTime());
            }

            Bounds bounds = boundsCm.get(e);
            batch.draw(anim.getKeyFrame(etop.getEllapsedTime(), true), bounds.x, bounds.y, bounds.width, bounds.height);

            if (etop.getEllapsedTime() < 0){
                animateBehindCm.remove(e);
            }
            if (!etop.getAnimationRepeating()) {
                // we check if the current frame is the last frame of the animation
            if (etop.getAnimation().isAnimationFinished(etop.getEllapsedTime())) {
                animateBehindCm.remove(e);
            }}
        }
    }
}
