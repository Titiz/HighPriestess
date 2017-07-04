package com.hr.highpriestess.game.systems.GameSystems.AnimationSystems;

/**
 * Created by Titas on 2017-07-04.
 */

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.hr.highpriestess.game.components.Game.Anim;
import com.hr.highpriestess.game.components.Game.Kinematics;
import com.hr.highpriestess.game.components.Menu.Bounds;



import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.hr.highpriestess.game.components.Game.Anim;
import com.hr.highpriestess.game.components.Game.Kinematics;
import com.hr.highpriestess.game.components.Menu.Bounds;

/**
 * This System's job is to see what state the all the entities that have animations are in,
 * and change the animation accordingly
 */
public class StateSelectSystem extends IteratingSystem {

    ComponentMapper<Kinematics> kinCm;
    ComponentMapper<Anim> animCm;


    public StateSelectSystem() {
        super(Aspect.all(Anim.class));
    }
    @Override
    protected void process(int e) {
        Anim eAnim = animCm.get(e);
        String lastActiveId = eAnim.activeId;
        if (kinCm.has(e)) {
            Kinematics eKin = kinCm.get(e);
            if (eKin.getVx() != 0) {
                eAnim.activeId = eAnim.ids[1];
            } else {
                eAnim.activeId = eAnim.ids[0];
            }
        }

        if (lastActiveId != eAnim.activeId) { // Resetting the animation to the beginning frame.
            eAnim.age = 0;
        }




    }
}
