package com.hr.highpriestess.game.systems.MenuSystems.HoverSystems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.badlogic.gdx.Gdx;
import com.hr.highpriestess.game.components.Menu.AnimationBehind;
import com.hr.highpriestess.game.components.Menu.HoverBehavior;


/**
 * class that plays animation on hover on the entities
 * that have the animationbehind component
 *
 * This requires that an an entity have 3 animations:
 * 1. loop that plays while there is no hover.
 * 2. a transition animation that plays once
 * 3. looping animation that begins playing after the transition and plays while the mouse still hovers over the entity.
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

        // here we check what the active animation and do actions accordingly.
        // generally we just increment the ellapsed time if the mouse hovers the entity
        // and decrease it otherwise.
        // we also have to change the animation once it plays through once and the mouse is still hovering.

        if (etop.getActiveAnimation() == etop.getAnimationBefore()) {
            if (!isHovered(e)) {
                etop.addEllapsedTime(Gdx.graphics.getDeltaTime());
            } else {
                etop.setActiveAnimation(etop.getAnimation());
                etop.setEllapsedTime(0);
            }

        }
        if (etop.getActiveAnimation() == etop.getAnimation()) {
            if (isHovered(e) &&
                    currentFrame < etop.getAnimation().getKeyFrames().length - 1) {
                etop.addEllapsedTime(Gdx.graphics.getDeltaTime());
            } else if (isHovered(e)) {
                etop.setActiveAnimation(etop.getAnimationAfter());
                etop.setMaxFrameTime(etop.getEllapsedTime());
                etop.setEllapsedTime(0);

            }



            // behavior for the inverse movement of the animation.


            if (currentFrame > 0 && !isHovered(e)) {
                etop.addEllapsedTime(-Gdx.graphics.getDeltaTime());
            } else if (!isHovered(e)) {
                etop.setActiveAnimation(etop.getAnimationBefore());
                etop.setEllapsedTime(0);
            }
        }


            //

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



