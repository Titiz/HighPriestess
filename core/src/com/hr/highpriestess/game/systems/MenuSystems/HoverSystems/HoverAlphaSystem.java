package com.hr.highpriestess.game.systems.MenuSystems.HoverSystems;

import com.artemis.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hr.highpriestess.game.components.Menu.*;
import com.hr.highpriestess.game.systems.MenuSystems.AssetSystem;
import com.hr.highpriestess.game.systems.MenuSystems.CameraSystem;
import com.hr.highpriestess.game.systems.MenuSystems.CollisionSystem;
import com.hr.highpriestess.game.systems.MenuSystems.LabelSystem;


public class HoverAlphaSystem extends HoverEntitySystem{

    private ComponentMapper<HoverableText>  hoverableTextcm;
    private ComponentMapper<Text> textCm;
    private ComponentMapper<HoverBehavior> hoverCm;
    private ComponentMapper<Bounds> boundsCm;
    private CameraSystem cameraSystem;
    //passive systems.
    private AssetSystem assetSystem;
    private CollisionSystem collisionSystem;
    private LabelSystem hoverLabelSystem;

    private SpriteBatch batch = new SpriteBatch();

    ComponentMapper<HoverableText> hovCm;
    ComponentMapper<HoverBehavior> hoverBehaviorCm;
    ComponentMapper<ChangingAlpha> changeAlphaCm;
    ComponentMapper<Alpha> alphaCm;
    ComponentMapper<AnimationBehind> animationBehind;





    public HoverAlphaSystem() {
        super(Aspect.all(Text.class, HoverBehavior.class, Alpha.class, Bounds.class));


    }




    protected final void process(int e) {
        /**
         * Experimental class that tells if the mouse is hovering over some entity
         * This should be separated into more classes.
         */

        if (changeAlphaCm.has(e) && alphaCm.get(e).getAlpha() < 1.0f) {
            alphaCm.get(e).changeAlpha(0.2f* Gdx.graphics.getDeltaTime());
        }else if (alphaCm.get(e).getAlpha() > 0.0f) {
                alphaCm.get(e).changeAlpha(-0.01f);
            }


        if (tickAt(e, 0))
            changeAlphaCm.remove(e);

        if (tickAt(e, 1)) {
            changeAlphaCm.create(e);
        }




    }

}

