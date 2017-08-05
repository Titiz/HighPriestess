package com.hr.highpriestess.game.systems.GameSystems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.hr.highpriestess.game.components.Game.Anim;
import com.hr.highpriestess.game.components.Game.Kinematics;
import com.hr.highpriestess.game.components.Menu.Bounds;

/**
 * Moves the player and other entities with velocity.
 */
public class KinematicsSystem extends IteratingSystem {

    ComponentMapper<Kinematics> kinCm;
    ComponentMapper<Bounds> boundsCm;
    ComponentMapper<Anim> animCm;

    public KinematicsSystem() {
        super(Aspect.all(Kinematics.class, Bounds.class));
    }
    @Override
    protected void process(int e) {
        Bounds ebounds = boundsCm.get(e);
        Kinematics eKin = kinCm.get(e);

        ebounds.changeX(eKin.getVx());
        ebounds.changeY(eKin.getVy());

        if (animCm.has(e)) {
            if (eKin.getVx() < 0) {
                animCm.get(e).flippedX = true;
            } else {
                animCm.get(e).flippedX = false;
            }
        }

    }
}
