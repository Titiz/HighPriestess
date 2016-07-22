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





        if (etop.getForward()) {
            etop.addEllapsedTime(Gdx.graphics.getDeltaTime());
        } else {
            etop.addEllapsedTime(-Gdx.graphics.getDeltaTime());
        }

        TextureRegion[] frames = etop.getAnimation().getKeyFrames();
        if (tickAt(e,0) && etop.getCurrentFrame() > 0 ) {
            etop.changeCurrentFrame(-1);
        } else if (hoverBehaviorCm.get(e).isHovered() &&
                etop.getAnimation().getKeyFrames().length-1 > etop.getCurrentFrame()){
            etop.changeCurrentFrame(1);
            System.out.println(etop.getCurrentFrame());
        }
    }


}
