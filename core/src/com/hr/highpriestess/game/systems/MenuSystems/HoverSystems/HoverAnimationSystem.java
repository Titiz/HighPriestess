package com.hr.highpriestess.game.systems.MenuSystems.HoverSystems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hr.highpriestess.game.components.AnimationBehind;
import com.hr.highpriestess.game.components.HoverBehavior;


/**
 * Created by Titas on 2016-07-22.
 */
public class HoverAnimationSystem extends HoverEntitySystem {

    ComponentMapper<AnimationBehind> animationBehind;



    public HoverAnimationSystem() {
        super(Aspect.all(HoverBehavior.class, AnimationBehind.class));

    }

    @Override
    protected void process(int e) {
        AnimationBehind etop = animationBehind.get(e);

        int currentFrame = etop.getActiveAnimation().getKeyFrameIndex(etop.getEllapsedTime());


        if (etop.getActiveAnimation() == etop.getAnimationBefore()) {
            if (!isHovered(e)) {
                etop.addEllapsedTime(Gdx.graphics.getDeltaTime());
            } else {
                etop.setActiveAnimation(etop.getAnimation());
                etop.setEllapsedTime(0);
            }

        }
        if (etop.getActiveAnimation() == etop.getAnimation()) {
            System.out.println(etop.getEllapsedTime());
            if (isHovered(e) &&
                    currentFrame < etop.getAnimation().getKeyFrames().length - 1) {
                etop.addEllapsedTime(Gdx.graphics.getDeltaTime());
            } else if (isHovered(e)) {
                etop.setActiveAnimation(etop.getAnimationAfter());
                etop.setMaxFrameTime(etop.getEllapsedTime());
                etop.setEllapsedTime(0);

            }


            if (currentFrame > 0 && !isHovered(e)) {
                etop.addEllapsedTime(-Gdx.graphics.getDeltaTime());
            } else if (!isHovered(e)) {
                etop.setActiveAnimation(etop.getAnimationBefore());
                etop.setEllapsedTime(0);
            }
        }


            if (etop.getActiveAnimation() == etop.getAnimationAfter()) {
                if (isHovered(e) &&
                        currentFrame < etop.getActiveAnimation().getKeyFrames().length - 1) {
                    etop.addEllapsedTime(Gdx.graphics.getDeltaTime());
                } else if (isHovered(e)) {
                    etop.setEllapsedTime(0);
                }

                if (currentFrame > 0 && !isHovered(e)) {
                    etop.addEllapsedTime(-Gdx.graphics.getDeltaTime());
                } else if (!isHovered(e)) {
                    etop.setActiveAnimation(etop.getAnimation());
                    etop.setEllapsedTime(etop.getMaxFrameTime());
                }
            }
        }
    }



