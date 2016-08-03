package com.hr.highpriestess.game.systems.MenuSystems.Render;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.hr.highpriestess.game.components.Menu.*;
import com.hr.highpriestess.game.systems.MenuSystems.CameraSystem;
import com.hr.highpriestess.game.systems.MenuSystems.AssetSystem;
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
    ComponentMapper<ImageComponent> imageComponentCm;

    SpriteBatch batch = new SpriteBatch();
    CameraSystem cameraSystem;
    AssetSystem assetSystem;
    private float elapsedTime = 0;
    private LayerManager layerManager;



    List<Integer> renderLayers = new ArrayList<Integer>();

    public RenderMenu() {
        super(Aspect.all(Bounds.class).one(AnimationBehind.class, Text.class, Transition.class, ImageComponent.class));
    }


    @Override
    public void process(int e) {
        if (layerManager.getActiveLayer() == layerCm.get(e).getLayer() || transCm.has(e) || imageComponentCm.has(e) ) {
            batch.setProjectionMatrix(cameraSystem.camera.combined);
            batch.begin();
            draw_background(e);
            draw_animation_behind(e);
            draw_label(e);
            draw_transition(e);
            batch.end();
        }
    }




    public void draw_background(int e) {
        if (imageComponentCm.has(e)) {
            ImageComponent entity = imageComponentCm.get(e);
            batch.draw(entity.getImg(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        }
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
            anim.setFrameDuration(0.1f);
            // ellapsed time is added to those who are not managed by hoverSystem.
            if (!hoverBehaviorCm.has(e))
                etop.addEllapsedTime(Gdx.graphics.getDeltaTime());
            batch.draw(etop.getActiveAnimation().getKeyFrame(etop.getEllapsedTime()),
                    ebound.x, ebound.y, ebound.width, ebound.height);

            }

        }
    }

