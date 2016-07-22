package com.hr.highpriestess.game.components;

import com.artemis.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hr.highpriestess.game.systems.MenuSystems.AssetSystem;

/**
 * Created by Titas on 2016-07-21.
 */
public class AnimationBehind extends Component {

    private Animation animation;
    private Boolean framesGoForward = true;
    private Boolean animationRepeating = false;
    private Animation animationAfter = null;
    private int currentFrame = 0;

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public void changeCurrentFrame(int delta) {
        currentFrame += delta;
    }


    public Boolean isStopped() {
        return isStopped;
    }

    public void setStopped(Boolean stopped) {
        isStopped = stopped;
    }

    private Boolean isStopped = false;


    public AnimationBehind(Animation anim) {
        this.animation = anim;
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
