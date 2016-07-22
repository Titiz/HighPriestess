package com.hr.highpriestess.game.systems.MenuSystems.Render;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.hr.highpriestess.game.components.*;
import com.hr.highpriestess.game.systems.MenuSystems.CameraSystem;
import com.hr.highpriestess.game.systems.MenuSystems.AssetSystem;

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
            Bounds ebound = boundsCm.get(e);
            AnimationBehind etop = animateBehindCm.get(e);
            Animation anim = etop.getAnimation();
            System.out.println(etop.getAnimation());
            anim.setPlayMode(Animation.PlayMode.NORMAL);
            anim.setFrameDuration(0.1f);
            if (!etop.getAnimationRepeating()) {
                TextureRegion[] frames = etop.getAnimation().getKeyFrames();
                TextureRegion currentFrame = frames[etop.getCurrentFrame()];
                batch.draw(etop.getAnimation().getKeyFrame(Gdx.graphics.getDeltaTime() * etop.getCurrentFrame()),
                        ebound.x, ebound.y, ebound.width, ebound.height);
            }

        }
    }
}
