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
import com.hr.highpriestess.game.systems.MenuSystems.HoverSystems.MouseHoverSystem;
import com.hr.highpriestess.game.systems.MenuSystems.LayerManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Titas on 2016-07-20.
 */
public class RenderMenu extends IteratingSystem{

    ComponentMapper<HoverableText>  hovCm;
    ComponentMapper<Alpha> alphaCm;
    ComponentMapper<AnimationBehind> animateBehindCm;
    ComponentMapper<Bounds> boundsCm;
    ComponentMapper<Layer> layerCm;
    ComponentMapper<Text> textCm;
    ComponentMapper<HoverBehavior> hoverBehaviorCm;
    ComponentMapper<Transition> transCm;

    SpriteBatch batch = new SpriteBatch();
    CameraSystem cameraSystem;
    AssetSystem assetSystem;
    private float elapsedTime = 0;
    private LayerManager layerManager;

    List<Integer> renderLayers = new ArrayList<Integer>();

    public RenderMenu() {
        super(Aspect.all(Bounds.class).one(AnimationBehind.class, Text.class, Transition.class));
    }


    @Override
    public void process(int e) {
        if (layerManager.getActiveLayer() == layerCm.get(e).getLayer()) {
            batch.setProjectionMatrix(cameraSystem.camera.combined);
            batch.begin();
            draw_animation_behind(e);
            draw_label(e);
            batch.end();
        }
        batch.begin();
        draw_transition(e);
        batch.end();
    }


    public void draw_transition(int e){
        if (transCm.has(e)) {

            Animation trans = transCm.get(e).getTransition();
            float time = transCm.get(e).getEllapsedTime();
            if (transCm.get(e).getTransition() != null)
                batch.draw(trans.getKeyFrame(time), 0, 0, (float)Gdx.graphics.getWidth(), (float)Gdx.graphics.getHeight());
        }
    }


    public void draw_label(int e) {
        if (textCm.has(e)){
            Label label = textCm.get(e).getLabel();
            if (alphaCm.has(e)) {
                label.draw(batch, alphaCm.get(e).getAlpha());
            }
            else
                label.draw(batch, 1);
        }
    }

    public void draw_animation_behind(int e) {
        if (animateBehindCm.has(e)) {
            Bounds ebound = boundsCm.get(e);
            AnimationBehind etop = animateBehindCm.get(e);
            Animation anim = etop.getAnimation();
            anim.setPlayMode(Animation.PlayMode.NORMAL);
            anim.setFrameDuration(0.1f);
            if (!hoverBehaviorCm.has(e))
                etop.addEllapsedTime(Gdx.graphics.getDeltaTime());
            batch.draw(etop.getActiveAnimation().getKeyFrame(etop.getEllapsedTime()),
                    ebound.x, ebound.y, ebound.width, ebound.height);

            }

        }
    }

