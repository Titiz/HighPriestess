package com.hr.highpriestess.game.components;

import com.artemis.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * Created by Titas on 2016-07-21.
 */
public class AnimationBehind extends Component {

    private Animation animation;
    private Boolean framesGoForward = true;
    private Boolean animationRepeating = false;
    private Animation animationAfter = null;


    public AnimationBehind() {



    }

    public Boolean getAnimationRepeating() {
        return animationRepeating;
    }

    public Animation getAnimationAfter() {
        return animationAfter;
    }




    public Boolean getForward() {
        return framesGoForward;
    }

    public void setForward(Boolean framesGoForward) {
        this.framesGoForward = framesGoForward;
    }

    public float getEllapsedTime() {
        return ellapsedTime;
    }


    public void setEllapsedTime(float ellapsedTime) {
        this.ellapsedTime = ellapsedTime;
    }

    private float ellapsedTime = 0;

    public Animation getAnimation() {
        return animation;
    }


    public void addEllapsedTime(float time){
        ellapsedTime += time;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }








}
