package com.hr.highpriestess.game.components.Menu;

import com.artemis.Component;
import com.artemis.Entity;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.List;

import java.util.ArrayList;


/**
 * Created by Titas on 2016-07-22.
 */
public class AnimationSequence extends Component {

    public List<Animation> getAnimationSequence() {
        return animationSequence;
    }

    private final List<Animation> animationSequence;

    public AnimationSequence(List<Animation> animSeq) {
        animationSequence = animSeq;
    }




}
