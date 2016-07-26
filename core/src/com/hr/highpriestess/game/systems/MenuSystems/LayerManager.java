package com.hr.highpriestess.game.systems.MenuSystems;

import com.artemis.*;
import com.artemis.systems.IteratingSystem;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.components.AnimationBehind;
import com.hr.highpriestess.game.components.Layer;
import com.hr.highpriestess.game.components.Transition;

/**
 * Created by Titas on 2016-07-23.
 */
public class LayerManager extends BaseEntitySystem {


    ComponentMapper<Transition> transCm;

    int activeLayer;
    boolean layer_changed = false;
    Animation animation;
    AssetSystem assetSystem;
    int transitionEntity = -1;

    public int getActiveLayer() {
        return activeLayer;
    }

    public void setActiveLayer(int activeLayer) {
        // change active layer
        this.activeLayer = activeLayer;
        // set the transition method that will be played
        transCm.get(transitionEntity).setTransition(assetSystem.transitions.get(activeLayer));
        transCm.get(transitionEntity).setEllapsedTime(0);

    }

    public LayerManager() {
        super(Aspect.all(Layer.class));
        activeLayer = 0;

    }



    private void getTransitionEntityOnce() {
        if (transitionEntity == -1) {
            IntBag bag = subscription.getEntities();
            int[] ids = bag.getData();
            for (int i = 0; i < bag.getCapacity(); i++) {
                if (transCm.has(i)) {
                    this.transitionEntity = i;
                }
            }
        }
    }

    @Override
    protected void processSystem() {
        getTransitionEntityOnce();
        Transition trans = transCm.get(transitionEntity);
        if (trans.getTransition() != null) {
            int currentFrame = trans.getTransition().getKeyFrameIndex(trans.getEllapsedTime());

            if (!trans.getTransition().isAnimationFinished(trans.getEllapsedTime())) {
                trans.addEllapsedTime(Gdx.graphics.getDeltaTime());
            } else {
                trans.setTransition(null);
            }
        }


        if (this.activeLayer == -1)
            G.game.goGame();
    }


}
