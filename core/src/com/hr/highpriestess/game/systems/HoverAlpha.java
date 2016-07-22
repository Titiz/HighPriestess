package com.hr.highpriestess.game.systems;

import com.artemis.*;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hr.highpriestess.game.components.*;
import com.hr.highpriestess.game.components.Text;
import com.badlogic.gdx.scenes.scene2d.ui.Label;


public class HoverAlpha extends IteratingSystem{

    private ComponentMapper<HoverableText>  hoverableTextcm;
    private ComponentMapper<Text> textCm;
    private ComponentMapper<HoverBehavior> hoverCm;
    private ComponentMapper<Bounds> boundsCm;
    private CameraSystem cameraSystem;
    //passive systems.
    private AssetSystem assetSystem;
    private CollisionSystem collisionSystem;

    private SpriteBatch batch = new SpriteBatch();

    ComponentMapper<HoverableText> hovCm;
    ComponentMapper<ChangingAlpha> changeAlphaCm;
    ComponentMapper<Alpha> alphaCm;
    ComponentMapper<AnimationBehind> animationBehind;

    int ticksEntered = 0;




    public HoverAlpha() {
        super(Aspect.all(Text.class, HoverBehavior.class, Alpha.class, Bounds.class));


    }




    protected final void process(int e) {
        /**
         * Experimental class that tells if the mouse is hovering over some entity
         * This should be separated into more classes.
         */
        BitmapFont font = assetSystem.font;

        if (changeAlphaCm.has(e) && alphaCm.get(e).getAlpha() < 1.0f) {
            alphaCm.get(e).changeAlpha(0.06f);
        }else if (alphaCm.get(e).getAlpha() > 0.0f) {
                alphaCm.get(e).changeAlpha(-0.01f);
            }


        if (collisionSystem.collidesWithMouse(e)) {
            // behavior to create only one label.
            ticksEntered += 1;

        } else {
            ticksEntered = 0;
            changeAlphaCm.remove(e);
            if (animationBehind.has(e))
            animationBehind.get(e).setForward(false);
        }
        if (ticksEntered == 1) {
            changeAlphaCm.create(e);
            animationBehind.create(e);
            Animation anim = assetSystem.sprites.get("menuAnim1");
            anim.setPlayMode(Animation.PlayMode.NORMAL);
            animationBehind.get(e).setAnimation(anim);
            animationBehind.get(e).setForward(true);
            font.setColor(255, 255, 255, 1);
            Label.LabelStyle style = new Label.LabelStyle(font, font.getColor());
            Label label = new Label(textCm.get(e).getText(), style);
            label.setPosition(boundsCm.get(e).x, boundsCm.get(e).y);
            boundsCm.get(e).width = label.getPrefWidth();
            boundsCm.get(e).height = label.getPrefHeight();
            hovCm.create(e);
            hovCm.get(e).setLabel(label);

        }




    }

}

