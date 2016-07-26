package com.hr.highpriestess.game.systems.MenuSystems;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.BaseSystem;
import com.artemis.EntitySubscription;
import com.artemis.systems.IteratingSystem;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.components.Layer;

/**
 * Created by Titas on 2016-07-23.
 */
public class LayerManager extends BaseEntitySystem {


    int activeLayer;

    public int getActiveLayer() {
        return activeLayer;
    }

    public void setActiveLayer(int activeLayer) {
        this.activeLayer = activeLayer;
    }

    public LayerManager() {
        super(Aspect.all(Layer.class));
        activeLayer = 0;
    }



    @Override
    protected void processSystem() {
        if (this.activeLayer == -1)
            G.game.goGame();

    }


}
