package com.hr.highpriestess.game.systems.MenuSystems;

import com.artemis.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.components.Menu.Layer;
import com.hr.highpriestess.game.components.Menu.Transition;
import com.hr.highpriestess.game.util.EntityMakerMenu;

/**
 * Layer manager deals with what layers can be interacted with in the menu.
 * This allows to make multiple separate interactable menus
 * say go from menu -> settings means changing the active layer.
 */


// TODO: make the layer be a ENUM rather than an int.
public class LayerManager extends BaseEntitySystem {


    ComponentMapper<Transition> transCm;

    int activeLayer;
    boolean layer_changed = false;
    Animation animation;
    private boolean transitionOver;

    public boolean isTransitionOver() {
        return transitionOver;
    }

    AssetSystem assetSystem;
    int transitionEntity = 0;

    public int getActiveLayer() {
        return activeLayer;
    }

    public void setActiveLayer(int activeLayer) {
        // change active layer
        this.activeLayer = activeLayer;
        // set the transition method that will be played
        if (assetSystem.transitions.containsKey(activeLayer)) {
            transitionEntity = EntityMakerMenu.createTransition(G.menuWorld).getId();
            transitionOver = false;
            transCm.create(transitionEntity);
            transCm.get(transitionEntity).setTransition(assetSystem.transitions.get(activeLayer));
        }

    }



    public LayerManager() {
        super(Aspect.all(Layer.class));
        activeLayer = 0;
        transitionOver = true;
    }




    @Override
    protected void processSystem() {

        if (transCm.has(transitionEntity)) {
            Transition trans = transCm.get(transitionEntity);


            if (!trans.getTransition().isAnimationFinished(trans.getEllapsedTime())) {
                trans.addEllapsedTime(Gdx.graphics.getDeltaTime());
            } else {

                G.menuWorld.getEntity(transitionEntity).deleteFromWorld();
                transitionOver = true;
                if (this.activeLayer == -1)
                    G.game.goGame();
            }
        }



    }


}
