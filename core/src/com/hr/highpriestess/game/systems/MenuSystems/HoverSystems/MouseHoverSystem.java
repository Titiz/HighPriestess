package com.hr.highpriestess.game.systems.MenuSystems.HoverSystems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.hr.highpriestess.game.components.Bounds;
import com.hr.highpriestess.game.components.HoverBehavior;
import com.hr.highpriestess.game.systems.MenuSystems.CollisionSystem;


/**
 *  Simply used to tell if the mouse is hovering over something.
 */
public class MouseHoverSystem extends IteratingSystem {

    public MouseHoverSystem() {
        super(Aspect.all(HoverBehavior.class, Bounds.class));
    }

    ComponentMapper<Bounds> boundsCm;
    ComponentMapper<HoverBehavior> hovCm;
    CollisionSystem collisionSystem;

    protected void process(int e) {
        if (collisionSystem.collidesWithMouse(e)){
            hovCm.get(e).setHovered(true);
            hovCm.get(e).incrementTicks();
        } else {
            hovCm.get(e).setHovered(false);
            hovCm.get(e).setTicks(0);
        }
    }

}

