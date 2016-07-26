package com.hr.highpriestess.game.systems.MenuSystems.HoverSystems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.hr.highpriestess.game.components.Bounds;
import com.hr.highpriestess.game.components.HoverBehavior;
import com.hr.highpriestess.game.components.Layer;
import com.hr.highpriestess.game.systems.MenuSystems.CollisionSystem;
import com.hr.highpriestess.game.systems.MenuSystems.LayerManager;

import java.util.ArrayList;
import java.util.List;


/**
 *  Simply used to tell if the mouse is hovering over something.
 */
public class MouseHoverSystem extends IteratingSystem {

    public MouseHoverSystem() {
        super(Aspect.all(HoverBehavior.class, Bounds.class));
    }
    int activeLayer = 0;

    ComponentMapper<Bounds> boundsCm;
    ComponentMapper<HoverBehavior> hovCm;
    CollisionSystem collisionSystem;
    LayerManager layerManager;
    ComponentMapper<Layer> layerCm;

    protected void process(int e) {
        int activeLayer = layerManager.getActiveLayer();
        if (activeLayer == layerCm.get(e).getLayer()) {
            if (collisionSystem.collidesWithMouse(e)) {
                hovCm.get(e).setHovered(true);
                hovCm.get(e).incrementTicks();
            } else {
                hovCm.get(e).setHovered(false);
                hovCm.get(e).setTicks(0);
            }
        } else {
            hovCm.get(e).setHovered(false);
            hovCm.get(e).setTicks(0);
        }
    }

    public void setActiveLayer(int activeLayer) {
        this.activeLayer = activeLayer;

    }

    public int getActiveLayer() {
        return activeLayer;
    }
}

