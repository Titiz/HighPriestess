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

        System.out.println(etop.getEllapsedTime());
        if (isHovered(e) &&
                etop.getAnimation().getKeyFrameIndex(etop.getEllapsedTime()) <
                        etop.getAnimation().getKeyFrames().length-1) {
            etop.addEllapsedTime(Gdx.graphics.getDeltaTime());
        } else if (etop.getAnimation().getKeyFrameIndex(etop.getEllapsedTime()) > 0 && !isHovered(e)) {
            etop.addEllapsedTime(-Gdx.graphics.getDeltaTime());
        }









    }


}
