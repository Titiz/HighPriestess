package com.hr.highpriestess.game.systems.MenuSystems.HoverSystems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.hr.highpriestess.game.components.Menu.Bounds;
import com.hr.highpriestess.game.components.Menu.HoverBehavior;
import com.hr.highpriestess.game.components.Menu.Layer;
import com.hr.highpriestess.game.systems.MenuSystems.CollisionUtilSystem;
import com.hr.highpriestess.game.systems.MenuSystems.LayerManager;


/**
 *  Simply used to tell if the mouse is hovering over something.
 *  Has a timer which increments when the entity is hovered, and is 0 otherwise.
 *
 */
public class MouseHoverSystem extends IteratingSystem {

    public MouseHoverSystem() {
        super(Aspect.all(HoverBehavior.class, Bounds.class));
    }
    int activeLayer = 0;

    ComponentMapper<Bounds> boundsCm;
    ComponentMapper<HoverBehavior> hovCm;
    CollisionUtilSystem collisionUtilSystem;
    ComponentMapper<Layer> layerCm;
    LayerManager layerManager;

    protected void process(int e) {
        int activeLayer = layerManager.getActiveLayer();

        if (activeLayer == layerCm.get(e).getLayer() &&
                layerManager.isTransitionOver()) {
            if (collisionUtilSystem.collidesWithMouse(e)) {
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

