package com.hr.highpriestess.game.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * Created by Titas on 2016-07-26.
 */
public class Transition extends Component {

    Animation transition;
    Boolean isActive = false;
    float ellapsedTime = 0;

    public Transition() {
    }

    public Animation getTransition() {
        return transition;
    }

    public void setTransition(Animation transition) {
        this.transition = transition;
        if (transition != null)
        this.transition.setPlayMode(Animation.PlayMode.NORMAL);
    }

    public float getEllapsedTime() {
        return ellapsedTime;
    }


    public void setEllapsedTime(float ellapsedTime) {
        this.ellapsedTime = ellapsedTime;
    }

    public void addEllapsedTime(float time){
        ellapsedTime += time;
    }


}
