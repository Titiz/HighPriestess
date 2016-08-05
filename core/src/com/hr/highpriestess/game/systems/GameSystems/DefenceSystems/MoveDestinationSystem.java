package com.hr.highpriestess.game.systems.GameSystems.DefenceSystems;

import com.artemis.Aspect;
import com.artemis.ComponentManager;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.hr.highpriestess.game.components.Game.Defence.MoveToDestination;
import com.hr.highpriestess.game.components.Game.Kinematics;
import com.hr.highpriestess.game.components.Menu.Bounds;
import com.hr.highpriestess.game.util.Utilities;
import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

/**
 * Created by Titas on 2016-08-05.
 */
public class MoveDestinationSystem extends IteratingSystem {

    ComponentMapper<Bounds> boundsCm;
    ComponentMapper<Kinematics> kinematicsCm;
    ComponentMapper<MoveToDestination> moveToDestinationCm;



    public MoveDestinationSystem(){
        super(Aspect.all(MoveToDestination.class));
    }

    private void setVelocityUsingPoints(int e,float x2, float y2) {
        Bounds bounds = boundsCm.get(e);
        Kinematics kin = kinematicsCm.get(e);

        float x1 = bounds.x;
        float y1 = bounds.y;

        float dx = x2-x1;
        float dy = y2-y1;
        float distance = (float) Math.sqrt(dy*dy + dx*dx);

        kin.setVx(dx/distance * kin.getMax_vx());
        kin.setVy(dy/distance * kin.getMax_vy());
    }


    @Override
    protected void removed(int e) {
        Kinematics kinematics = kinematicsCm.getSafe(e);
        kinematics.setVx(0);
        kinematics.setVy(0);
    }

    @Override
    protected void process(int e) {
        Bounds bounds = boundsCm.getSafe(e);
        MoveToDestination move = moveToDestinationCm.getSafe(e);
        Kinematics kinematics = kinematicsCm.getSafe(e);

        if(move.changedFinalPos) {
            move.changedFinalPos = false;
            setVelocityUsingPoints(e, move.getFinalX(), move.getFinalY());
        }

        if (Utilities.distance(bounds.x, bounds.y, move.getFinalX(), move.getFinalY()) < kinematics.getMax_vx()*2) {
            bounds.x = move.getFinalX();
            bounds.y = move.getFinalY();
            moveToDestinationCm.remove(e);
        }
    }

}
